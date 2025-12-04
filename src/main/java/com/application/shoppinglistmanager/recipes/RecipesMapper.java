package com.application.shoppinglistmanager.recipes;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.application.shoppinglistmanager.ingredients.Ingredients;
import com.application.shoppinglistmanager.ingredients.IngredientsDto;
import com.application.shoppinglistmanager.ingredients.IngredientsMapper;
import com.application.shoppinglistmanager.recipe_ingredients.RecipeIngredients;
@Mapper(componentModel = "spring", uses = {IngredientsMapper.class})
public interface RecipesMapper {

    IngredientsMapper ingredientMapper = Mappers.getMapper(IngredientsMapper.class);

    @Mapping(source = "idRecipe", target = "id")
    RecipesDto fromRecipeToDto (Recipes entity); 

    List<RecipesDto> fromRecipesToDtos (List<Recipes> entities);

    @Mapping(source = "id", target = "idRecipe")
    Recipes fromDtoToRecipe (RecipesDto dto);

    List<Recipes> fromDtosToRecipes (List<RecipesDto> dtos);

    default List<Ingredients> extractIngredients(List<RecipeIngredients> list) {
    return list.stream()
               .map(RecipeIngredients::getIngredient)
               .toList();
    }

    default List<IngredientsDto> extractIngredient(List<RecipeIngredients> list) {
        return list.stream().map(slr -> {
            IngredientsDto ingredientDto = ingredientMapper.fromIngredientToDto(slr.getIngredient());
                ingredientDto.setQuantity(slr.getQuantity());
                ingredientDto.setUnit(slr.getUnit());
                return ingredientDto;
            }).toList();
    }
}
