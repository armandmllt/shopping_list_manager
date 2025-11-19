package com.application.shoppinglistmanager.users;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsersMapper {
    
    @Mapping(source = "idUser", target = "id")
    UsersDto fromUserToDto (Users entity);

    @Mapping(source = "id", target = "idUser")
    Users fromDtoToUser (UsersDto dto);

    List<UsersDto> fromUsersToDtos(List<Users> entities);
    List<Users> fromDtosToUsers(List<UsersDto> dtos);
}


    // @Mapping(source = "idUser", target = "idDto")
    // List<UsersDto> fromUsersToDtos (List<Users> usersList);

    // @Mapping(source = "idDto", target = "idUser")
    // List<Users> fromDtosToUsers (List<UsersDto> dtosList);