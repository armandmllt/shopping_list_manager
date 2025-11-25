package com.application.shoppinglistmanager.shoppingLists;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.application.shoppinglistmanager.recipes.RecipesMapper;
import com.application.shoppinglistmanager.users.UsersMapper;

@Mapper(componentModel = "spring", uses = {UsersMapper.class, RecipesMapper.class})
public interface ShoppingListsMapper {
    
    // @Mapping(source = "idList", target = "id")
    // @Mapping(source = "user", target = "user")
    // @Mapping(target = "recipes",
    //          expression = "java(entity.getShoppingListRecipes().stream.map(entity -> entity.getRecipe()).toList())"
    // )
    // ShoppingListsDto fromShoppingListToDto (ShoppingLists entity);
}
