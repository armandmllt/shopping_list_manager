package com.application.shoppinglistmanager.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public ResponseEntity<List<UsersDto>> getAllUsers () {
        List<UsersDto> allUsers = usersService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }

    //Works temporarily but will have to be changed whenever authentication will be implemented
    @GetMapping(path = "/{email}")
    public ResponseEntity<UsersDto> getUserByEmail (@PathVariable String email) {
        UsersDto user = usersService.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<UsersDto> createUser (@RequestBody UsersDto user) {
        UsersDto createdUser = usersService.createUser(user);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(createdUser);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteUserById (@PathVariable Integer id) {
        usersService.deleteUserById(id);
        //returns httpStatus 204 NO CONTENT
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<UsersDto> updateUserById (@RequestBody UsersDto user, @PathVariable Integer id) {
        UsersDto savedUser = usersService.updateUserById(id, user);
        return ResponseEntity.ok(savedUser);
    }

}
