package com.application.shoppinglistmanager.shoppingLists;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.application.shoppinglistmanager.recipes.Recipes;
import com.application.shoppinglistmanager.recipes.RecipesMapper;
import com.application.shoppinglistmanager.shoppingListRecipes.ShoppingListRecipes;
import com.application.shoppinglistmanager.users.UsersMapper;

@Mapper(componentModel = "spring", uses = {UsersMapper.class, RecipesMapper.class})
public interface ShoppingListsMapper {
    
    @Mapping(source = "idList", target = "id")
    @Mapping(source = "user", target = "user")
    @Mapping(source = "shoppingListRecipes", target = "recipes")
    ShoppingListsDto fromShoppingListToDto (ShoppingLists entity);

    List<ShoppingListsDto> fromShoppingListsToDtos (List<ShoppingLists> entities);

    default List<Recipes> extractRecipes(List<ShoppingListRecipes> list) {
    return list.stream()
               .map(ShoppingListRecipes::getRecipe)
               .toList();
}

}
