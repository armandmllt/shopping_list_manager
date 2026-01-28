package com.application.shoppinglistmanager.unit.users;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.application.shoppinglistmanager.shopping_lists.ShoppingListsRepository;
import com.application.shoppinglistmanager.users.Users;
import com.application.shoppinglistmanager.users.UsersDto;
import com.application.shoppinglistmanager.users.UsersMapper;
import com.application.shoppinglistmanager.users.UsersRepository;
import com.application.shoppinglistmanager.users.UsersService;

import jakarta.persistence.EntityNotFoundException;

@ExtendWith(MockitoExtension.class)
class UsersServiceTest {

    @Mock private UsersRepository usersRepository;  
    /*
    We use a mock of the repo because we know that the repo is fine (is unit tested). So if the methods from the repo
    are called, we know it's going to work.
    This also allows us to save some time on the tests, bc we don't have to call the DB, repo, etc.
    */
    @Mock private UsersMapper usersMapper;
    @Mock private ShoppingListsRepository shoppingListsRepository;
    private UsersService underTest;


    @BeforeEach
    void setUp() {
        underTest = new UsersService(
            usersRepository,
            usersMapper,
            shoppingListsRepository);
    }





    @Test
    void canGetAllUsers() {
        // when
        underTest.getAllUsers();

        // then
        verify(usersRepository).findAll(); //test that the findAll method from the repo is used 
    }





    @Test
    void canGetUserById() {
        //given 
        Integer id = 1;
        Users user = new Users (
            id, 
            "malenia",
            "malenia@lovemiquella.com",
            "password"
        );

        when(usersRepository.findById(id))
            .thenReturn(Optional.of(user));

        // when
        underTest.getUserById(id);

        // then
        verify(usersRepository).findById(id); //test that the findAll method from the repo is used 
    }

    @Test
    void cannotGetUserByIdIfNoUserMatchesId() {
        //given 
        Integer id = 1;

        // when + then
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, 
            () -> underTest.getUserById(id)
        );
        
        assertThat(exception.getMessage())
            .contains("Pas d'utilisateur d'id " + id + " trouvé dans la BDD.");
    }





    @Test
    void canGetUserByEmail() {
        //given
        String email = "malenia@lovemiquella.com";
        Users user = new Users (
            1, 
            "malenia",
            email,
            "password"
        );

        when(usersRepository.findByEmail(email))
            .thenReturn(Optional.of(user));

        // when
        underTest.getUserByEmail(email);

        // then
        verify(usersRepository).findByEmail(email); //test that the findAll method from the repo is used 
    }

    @Test
    void cannotGetUserByEmailIfNoUserMatchesEmail() {
        //given 
        String email = "malenia@lovemiquella.com";

        // when + then
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, 
            () -> underTest.getUserByEmail(email)
        );
        
        assertThat(exception.getMessage())
            .contains("Pas d'utilisateur avec l'adresse " + email + " trouvé dans la BDD.");
    }



   
    
    @Test
    void canCreateUser() {
        // given
        String email = "gideon@gmail.com";
        UsersDto userDto = new UsersDto(
            1,
            "gideon", 
            email
        );
        
        Users savedUser = new Users(
            1,
            "gideon",
            email,
            "password"
        );

        //Stubbing the mapper's behavior even though it's not called here
        //because it's called within the service
        when(usersMapper.toUser(userDto))
            .thenReturn(savedUser);

        // when
        underTest.createUser(userDto);

        // then
        ArgumentCaptor<Users> userArgumentCaptor = ArgumentCaptor.forClass(Users.class);

        //Verify that the repo's save() is called and capture the user to be saved
        verify(usersRepository).save(userArgumentCaptor.capture());
        Users capturedUser = userArgumentCaptor.getValue();
        
        assertThat(capturedUser).isEqualTo(savedUser);
    }





    @Test
    void canUpdateUser() {
        // given

        Integer id = 1;

        // we have to STUBB the mocked repo's behavior (= dictate how it should act)
        //otherwise, the test will fail because when the service checks if a user of id 1 exists in the DB, an exception is thrown
        when(usersRepository.findById(id))
            .thenReturn(Optional.of(new Users(
                id,
                "gideon",
                "gideon@gmail.com",
                "password"
        )));

        UsersDto userToUpdate = new UsersDto(
            id,
            "godffrey", 
            "godffreythefirsteldenlord@gmail.com"
        );

        //stubbing the mapper's behavior because it's called within the service
        //We give any Users as an argument to decouple the service from the mapper
        when(usersMapper.toDto(any(Users.class)))
            .thenReturn(userToUpdate);

        // when
        underTest.updateUserById(id, userToUpdate);

        // then
        ArgumentCaptor<Users> userArgumentCaptor = ArgumentCaptor.forClass(Users.class);

        verify(usersRepository).save(userArgumentCaptor.capture());
        Users capturedUser = userArgumentCaptor.getValue();

        assertThat(capturedUser)
            .extracting(
                Users::getId,
                Users::getName,
                Users::getEmail
            )
            .containsExactly(
                id,
                "godffrey",
                "godffreythefirsteldenlord@gmail.com"
        );
    }

    @Test
    void cannotUpdateUserIfIdIsUnknown() {

        // given
        Integer id = 1;

        when(usersRepository.findById(id))
            .thenReturn(Optional.empty());
        
        UsersDto updatedUserDto = new UsersDto(
            id,
            "godffrey", 
            "godffreythefirsteldenlord@gmail.com"
        );

        // when + then
        EntityNotFoundException exception = assertThrows(
            EntityNotFoundException.class,                         // the exception thrown
            () -> underTest.updateUserById(id, updatedUserDto)      // the code that throws the exception
        );

        assertThat(exception.getMessage()).contains("Pas d'utilisateur d'id " + id + " trouvé en BDD");
        verify(usersRepository, never()).save(any());
    }





    @Test
    void canDeleteUser () {
        
        //given 
        Integer id = 1;

        when(usersRepository.findById(id))
            .thenReturn(Optional.of(new Users(
                id,
                "radahn",
                "radahn@lovemiquella.com",
                "password"
        )));

        //when
        underTest.deleteUserById(id);

        //then
        verify(usersRepository).deleteById(id);

    }

    @Test
    void cannotDeleteUserIfNoUserMatchesId () {

        //given
        Integer id = 1;

        // when + then
        EntityNotFoundException exception = assertThrows(
            EntityNotFoundException.class,     // the exception thrown
            () -> underTest.deleteUserById(id)              // the code that throws the exception
        );

        assertThat(exception.getMessage()).contains("Pas d'utilisateur d'id " + id + " trouvé en BDD");
        verify(usersRepository, never()).save(any());
    }
}
