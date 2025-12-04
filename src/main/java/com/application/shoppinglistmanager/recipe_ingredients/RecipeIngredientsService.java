package com.application.shoppinglistmanager.recipe_ingredients;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeIngredientsService {

    private final RecipeIngredientsRepository recipeIngredientsRepository;

    @Autowired
    public RecipeIngredientsService (RecipeIngredientsRepository repository) {
        this.recipeIngredientsRepository = repository;
    }

    public List<RecipeIngredients> getAllRecipeIngredients () {
        return recipeIngredientsRepository.findAll();
    }
    
}
