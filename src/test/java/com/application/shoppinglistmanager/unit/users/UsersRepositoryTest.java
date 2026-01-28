package com.application.shoppinglistmanager.unit.users;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.application.shoppinglistmanager.users.Users;
import com.application.shoppinglistmanager.users.UsersRepository;

@DataJpaTest
class UsersRepositoryTest {

    @Autowired
    private UsersRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldCheckIfUserIsFoundByEmail() {
        //given
        String email = "gideon@gmail.com";
        Users user = new Users(
            "gideon", 
            email,
            "password"
        );
        underTest.save(user);

        //when
        Optional<Users> foundUser = underTest.findByEmail(email);

        //then
        assertThat(foundUser)
            .isPresent()
            .get()
            .isEqualTo(user);
    }

    @Test
    void itShouldCheckIfUserIsNotFoundByEmail() {
        //given
        String email = "gideon@gmail.com";

        //when
        Optional<Users> foundUser = underTest.findByEmail(email);

        //then
        assertThat(foundUser)
            .isNotPresent();
    }
}
