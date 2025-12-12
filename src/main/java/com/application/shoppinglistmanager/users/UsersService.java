package com.application.shoppinglistmanager.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UsersService {
    
    private final UsersRepository usersRepository;
    private final UsersMapper usersMapper;

    @Autowired
    public UsersService (UsersRepository usersRepository, UsersMapper userMapper) {
        this.usersRepository = usersRepository;
        this.usersMapper = userMapper;
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
    public Users createUser (UsersDto user) {
        Users userToSave = usersMapper.fromDtoToUser(user);
        return usersRepository.save(userToSave);
    }

    //ADD EXCEPTION MANAGEMENT
    public void deleteUserById (Integer userId) {
        usersRepository.deleteById(userId);
    }
}
