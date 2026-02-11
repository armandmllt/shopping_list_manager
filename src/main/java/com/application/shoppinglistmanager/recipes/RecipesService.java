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

    public RecipesDto updateRecipeById(Integer recipeId, RecipesDto recipe) {
        Recipes existingRecipe = recipesRepository.findById(recipeId)
            .orElseThrow(() -> new EntityNotFoundException("Pas de recette d'id " + recipeId + " trouvée en BDD."));
        
        /*
        Set the all the RecipeIngredients in the OneToMany towards Ingredients

        This must be done manually because of Recipes's custom mapping (fields unit and quantity directly into the
        Recipe's DTO, and not in a sub-DTO corresponding to the DB's tables)

        Additionally, we must use the addIngredient method. Otherwise, if we just set ingredients with a new collection,
        a "A collection with orphan deletion was no longer referenced by the owning entity instance [ingredients]" error
        appears, because the current collection is not tracked anymore. 
        This is also why when clear the ingredient collection beforehand.
        */
        existingRecipe.getIngredients().clear();

        //For each of the RecipesDto's ingredients
        recipe.getIngredients().forEach(ingredientDto -> {
            //Create the corresponding RecipeIngredients
            RecipeIngredients recipeIngredient = new RecipeIngredients(
                existingRecipe,
                ingredientsRepository.findById(ingredientDto.getId())
                    .orElseThrow(
                        () -> new IngredientNotFoundException(ingredientDto.getId()) //sends UNPROCESSABLE ENTITY status
                    ),
                ingredientDto.getQuantity(),
                ingredientDto.getUnit()
            );
            //add the RecipeIngredient to the recipe
            existingRecipe.addIngredient(recipeIngredient);
        });

        existingRecipe.setInstructions(recipe.getInstructions());
        existingRecipe.setName(recipe.getName());
        //servings is not set here because defined in relation to shoppingList

        recipesRepository.save(existingRecipe);
        return recipesMapper.toDto(existingRecipe);
    }

}
