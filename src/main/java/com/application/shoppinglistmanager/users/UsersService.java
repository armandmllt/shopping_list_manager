package com.application.shoppinglistmanager.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
