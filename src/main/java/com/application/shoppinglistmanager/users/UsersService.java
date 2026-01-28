package com.application.shoppinglistmanager.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.shoppinglistmanager.shopping_lists.ShoppingLists;
import com.application.shoppinglistmanager.shopping_lists.ShoppingListsRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UsersService {
    
    private final UsersRepository usersRepository;
    private final ShoppingListsRepository shoppingListsRepository;
    private final UsersMapper usersMapper;

    @Autowired
    public UsersService (UsersRepository usersRepository, UsersMapper userMapper, ShoppingListsRepository shoppingListsRepository) {
        this.usersRepository = usersRepository;
        this.usersMapper = userMapper;
        this.shoppingListsRepository = shoppingListsRepository;
    }

    public List<UsersDto> getAllUsers () {
        List<Users> allUsers = usersRepository.findAll();
        return usersMapper.toDtos(allUsers);
    }

    public UsersDto getUserById (Integer userId) {
        Users user = usersRepository.findById(userId).orElseThrow(
            () -> new EntityNotFoundException("Pas d'utilisateur d'id " + userId + " trouvé dans la BDD.")
        );
        return usersMapper.toDto(user);
    }

    public UsersDto getUserByEmail (String email) {
        Users user = usersRepository.findByEmail(email).orElseThrow(
            () -> new EntityNotFoundException("Pas d'utilisateur avec l'adresse " + email + " trouvé dans la BDD.")
        );
        return usersMapper.toDto(user);
    }

    //ADD EXCEPTION MANAGEMENT
    //aka check if user's email already exists
    public UsersDto createUser (UsersDto user) {
        Users userToSave = usersMapper.toUser(user);
        Users savedUser =  usersRepository.save(userToSave);

        //Create a shoppingList associated to the new user
        ShoppingLists userShoppingList = new ShoppingLists();
        userShoppingList.setUser(savedUser);
        shoppingListsRepository.save(userShoppingList);

        return usersMapper.toDto(savedUser);
    }

    //ADD EXCEPTION MANAGEMENT
    public void deleteUserById (Integer userId) {        
        if (usersRepository.findById(userId).isEmpty()) {
            throw new EntityNotFoundException("Pas d'utilisateur d'id " + userId + " trouvé en BDD");
        }
            
        usersRepository.deleteById(userId);
    }

    public UsersDto updateUserById(Integer id, UsersDto user) {

        //We have to get the existing user, otherwise the password information will be lost on the repository save
        //Acquiring the existing user
        Users existingUser = usersRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Pas d'utilisateur d'id " + id + " trouvé en BDD"));
        //Updating the fields
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());

        usersRepository.save(existingUser);
        return usersMapper.toDto(existingUser);
    }
}
