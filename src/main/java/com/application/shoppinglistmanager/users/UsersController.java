package com.application.shoppinglistmanager.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public List <UsersDto> getAllUsers () {
        return usersService.getAllUsers();
    }

    //Works temporarily but will have to be changed whenever authentication will be implemented
    @GetMapping(path = "/{email}")
    public UsersDto getUserByEmail (@PathVariable String email) {
        return usersService.getUserByEmail(email);
    }

    @PostMapping
    public Users createUser (@RequestBody UsersDto user) {
        return usersService.createUser(user);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteUserById (@PathVariable Integer id) {
        usersService.deleteUserById(id);
    }

}
