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
    RecipesDto toDto (Recipes entity); 

    List<RecipesDto> toDtos (List<Recipes> entities);

    @Mapping(source = "id", target = "idRecipe")
    @Mapping(target = "ingredients", ignore = true)
    Recipes toRecipe (RecipesDto dto);

    List<Recipes> toRecipes (List<RecipesDto> dtos);



    default List<IngredientsDto> mapIngredients(List<RecipeIngredients> list) {
        if (list == null) return List.of();

        return list.stream().map(ri -> {
            Ingredients ingredient = ri.getIngredient();

            IngredientsDto dto = new IngredientsDto();
            dto.setId(ingredient.getIdIngredient());
            dto.setName(ingredient.getName());
            dto.setQuantity(ri.getQuantity());
            dto.setUnit(ri.getUnit());

            return dto;
        }).toList();
    }
    


    // default List<Ingredients> extractIngredients(List<RecipeIngredients> list) {
    // return list.stream()
    //            .map(RecipeIngredients::getIngredient)
    //            .toList();
    // }

    /*
    This causes a problem when POSTING a recipe. The service creates new ingredients and manually links
    them to the recipe (through RecipeIngredients). This creates extra links, causing to have DOUBLE the
    number of ingredients in Recipe.

    default List<IngredientsDto> extractIngredient(List<RecipeIngredients> list) {
        return list.stream().map(slr -> {
            IngredientsDto ingredientDto = ingredientMapper.toDto(slr.getIngredient());
                ingredientDto.setQuantity(slr.getQuantity());
                ingredientDto.setUnit(slr.getUnit());
                return ingredientDto;
            }).toList();
    }
    */
}
