package com.application.shoppinglistmanager.recipes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.shoppinglistmanager.exception.IngredientNotFoundException;
import com.application.shoppinglistmanager.ingredients.Ingredients;
import com.application.shoppinglistmanager.ingredients.IngredientsDto;
import com.application.shoppinglistmanager.ingredients.IngredientsRepository;
import com.application.shoppinglistmanager.recipe_ingredients.RecipeIngredients;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RecipesService {
    
    private final RecipesRepository recipesRepository;
    private final RecipesMapper recipesMapper;
    private final IngredientsRepository ingredientsRepository;

    @Autowired
    public RecipesService (RecipesRepository recipesRepository, 
                           RecipesMapper recipesMapper,
                           IngredientsRepository ingredientsRepository) {
        this.recipesRepository = recipesRepository;
        this.recipesMapper = recipesMapper;
        this.ingredientsRepository = ingredientsRepository;
    }

    public List<RecipesDto> getAllRecipes () {
        List<Recipes> allRecipes = recipesRepository.findAll();
        return recipesMapper.toDtos(allRecipes);
    }

    public RecipesDto getRecipeById (Integer recipeId) {
        Recipes recipe = recipesRepository.findById(recipeId).orElseThrow(
            () -> new EntityNotFoundException("Pas de recette d'id " + recipeId + " trouvée en BDD ?")
        );
        return recipesMapper.toDto(recipe);
    }

    public RecipesDto createRecipe (RecipesDto recipeDto) {
        Recipes recipeToSave = recipesMapper.toRecipe(recipeDto);
        
        for (IngredientsDto ingredientDto : recipeDto.getIngredients()) {
            Ingredients ingredient = ingredientsRepository.findById(ingredientDto.getId())
            .orElseThrow(
                () -> new IngredientNotFoundException(ingredientDto.getId()) //sends UNPROCESSABLE ENTITY status
            );

            RecipeIngredients ri = new RecipeIngredients(
                //recipeToSave,
                ingredient,
                ingredientDto.getQuantity(),
                ingredientDto.getUnit()
            );

            //when reaching here for the 1st time, already 3 ingredients 
            //with only quantity and unit with value
            recipeToSave.addIngredient(ri);
        }

        Recipes savedRecipe = recipesRepository.save(recipeToSave);
        return recipesMapper.toDto(savedRecipe);
    }

    public void deleteRecipeById(Integer recipeId) {
        //TODO check that works also for recipes linked with a shopping list (not the case just yet, must be a mapping error)
        Recipes recipe = recipesRepository.findById(recipeId).orElseThrow(
            () -> new EntityNotFoundException("Pas de recette d'id " + recipeId + " trouvée en BDD ?")
        );
        recipesRepository.delete(recipe);
    }

}
