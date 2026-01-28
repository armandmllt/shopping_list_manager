package com.application.shoppinglistmanager.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.application.shoppinglistmanager.users.Users;
import com.application.shoppinglistmanager.users.UsersRepository;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class UsersIntegrationTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UsersRepository usersRepository;

    @Test
    void shouldReturnUserById() throws Exception {

        //given 
        Users user = new Users(
            null,
            "Mohg",
            "mohg@lovemiquella.com",
            "password"
        );
        usersRepository.save(user);

        //when + then 
        mockMvc.perform(get("/users/" + user.getEmail()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value(user.getName()))
            .andExpect(jsonPath("$.email").value(user.getEmail()));
    }
}
