package com.application.shoppinglistmanager.users;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsersMapper {
    
    @Mapping(source = "id", target = "id")
    UsersDto fromUserToDto (Users entity);

    @Mapping(source = "id", target = "id")
    Users fromDtoToUser (UsersDto dto);

    List<UsersDto> fromUsersToDtos(List<Users> entities);
    List<Users> fromDtosToUsers(List<UsersDto> dtos);
}