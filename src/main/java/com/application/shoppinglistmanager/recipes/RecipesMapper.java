package com.application.shoppinglistmanager.recipes;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.application.shoppinglistmanager.ingredients.Ingredients;
import com.application.shoppinglistmanager.ingredients.IngredientsMapper;
import com.application.shoppinglistmanager.recipeIngredients.RecipeIngredients;
@Mapper(componentModel = "spring", uses = {IngredientsMapper.class})
public interface RecipesMapper {

    @Mapping(source = "idRecipe", target = "id")
    RecipesDto fromRecipeToDto (Recipes entity); 

    @Mapping(source = "id", target = "idRecipe")
    Recipes fromDtoToRecipe (RecipesDto dto);

    default List<Ingredients> extractIngredients(List<RecipeIngredients> list) {
    return list.stream()
               .map(RecipeIngredients::getIngredient)
               .toList();
    }
}
