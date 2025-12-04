package com.application.shoppinglistmanager.recipe_ingredients;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/recipe-ingredients")
public class RecipeIngredientsController {
    
    private final RecipeIngredientsService recipeIngredientsService ;

    @Autowired
    private RecipeIngredientsController (RecipeIngredientsService recipeIngredientsService) {
        this.recipeIngredientsService = recipeIngredientsService;
    }

    @GetMapping
    public List<RecipeIngredients> getAllRecipeIngredients () {
        return recipeIngredientsService.getAllRecipeIngredients();
    }

}
