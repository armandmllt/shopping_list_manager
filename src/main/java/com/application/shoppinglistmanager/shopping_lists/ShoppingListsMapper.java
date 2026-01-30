package com.application.shoppinglistmanager.shopping_lists;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.application.shoppinglistmanager.recipes.RecipesDto;
import com.application.shoppinglistmanager.recipes.RecipesMapper;
import com.application.shoppinglistmanager.shopping_list_recipes.ShoppingListRecipes;
import com.application.shoppinglistmanager.users.UsersMapper;

@Mapper(componentModel = "spring", uses = {UsersMapper.class, RecipesMapper.class})
public interface ShoppingListsMapper {

    RecipesMapper recipesMapper = Mappers.getMapper(RecipesMapper.class);
    
    @Mapping(source = "idList", target = "id")
    @Mapping(source = "user", target = "user")
    @Mapping(source = "shoppingListRecipes", target = "recipes")
    ShoppingListsDto fromShoppingListToDto (ShoppingLists entity);

    List<ShoppingListsDto> fromShoppingListsToDtos (List<ShoppingLists> entities);

    default List<RecipesDto> extractRecipes(List<ShoppingListRecipes> list) {
    return list.stream().map(slr -> {
        RecipesDto recipeDto = recipesMapper.toDto(slr.getRecipe());
            recipeDto.setServings(slr.getServings());
            return recipeDto;
        }).toList();
    }

    //  default List<Recipes> extractRecipes(List<ShoppingListRecipes> list) {
    //      return list.stream()
    //            .map(ShoppingListRecipes::getRecipe)
    //            .toList();
    //  }

}
