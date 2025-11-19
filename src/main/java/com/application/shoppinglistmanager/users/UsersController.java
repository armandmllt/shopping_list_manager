package com.application.shoppinglistmanager.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/users")
public class UsersController {
    
    private final UsersService usersService;

    @Autowired
    private UsersController (UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public List <Users> getAllUsers () {
        return usersService.getAllUsers();
    }

}
