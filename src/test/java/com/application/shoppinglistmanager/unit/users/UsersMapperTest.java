package com.application.shoppinglistmanager.unit.users;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import com.application.shoppinglistmanager.users.Users;
import com.application.shoppinglistmanager.users.UsersDto;
import com.application.shoppinglistmanager.users.UsersMapper;

public class UsersMapperTest {

    private UsersMapper underTest = Mappers.getMapper(UsersMapper.class);
    
    @Test
    public void canMapFromEntityToDto () {

        //given 
        UsersDto dto = new UsersDto(
           1,
           "radahn",
           "radahn@lovemiquella.com"
        );

        //when
        Users user = underTest.toUser(dto);

        //then
        assertThat(user)
           .extracting(
                Users::getId,
                Users::getName,
                Users::getEmail
            )
            .containsExactly(
                1,
                "radahn",
                "radahn@lovemiquella.com"
        );
    }

}
