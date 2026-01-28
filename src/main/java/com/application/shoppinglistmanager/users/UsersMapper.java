package com.application.shoppinglistmanager.users;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsersMapper {
    
    @Mapping(source = "id", target = "id")
    UsersDto toDto (Users entity);

    @Mapping(source = "id", target = "id")
    Users toUser (UsersDto dto);

    List<UsersDto> toDtos(List<Users> entities);
    List<Users> toUsers(List<UsersDto> dtos);
}