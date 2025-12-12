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
        return usersMapper.fromUsersToDtos(allUsers);
    }

    public UsersDto getUserById (Integer userId) {
        Users user = usersRepository.findById(userId).orElseThrow(
            () -> new EntityNotFoundException("Pas d'utilisateur d'id " + userId + " trouvé dans la BDD.")
        );
        return usersMapper.fromUserToDto(user);
    }

    public UsersDto getUserByEmail (String email) {
        Users user = usersRepository.findByEmail(email).orElseThrow(
            () -> new EntityNotFoundException("Pas d'utilisateur avec l'adresse " + email + " trouvé dans la BDD.")
        );
        return usersMapper.fromUserToDto(user);
    }

    //ADD EXCEPTION MANAGEMENT
    public UsersDto createUser (UsersDto user) {
        Users userToSave = usersMapper.fromDtoToUser(user);
        Users savedUser =  usersRepository.save(userToSave);

        //Create a shoppingList associated to the new user
        ShoppingLists userShoppingList = new ShoppingLists();
        userShoppingList.setUser(savedUser);
        shoppingListsRepository.save(userShoppingList);

        return usersMapper.fromUserToDto(savedUser);
    }

    //ADD EXCEPTION MANAGEMENT
    public void deleteUserById (Integer userId) {
        usersRepository.deleteById(userId);
    }

    public UsersDto updateUserById(Integer id, UsersDto user) {

        //We have to get the existing user, otherwise the password information will be lost on the repository save
        //Acquiring the existing user
        Users existingUser = usersRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Pas d'utilisateur d'id " + id + " trouvé en BDD"));
        //Updating the fields
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());

        usersRepository.save(existingUser);
        return usersMapper.fromUserToDto(existingUser);
    }
}
