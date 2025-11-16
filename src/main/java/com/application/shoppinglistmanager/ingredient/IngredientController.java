package com.application.shoppinglistmanager.ingredient;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/ingredients")
public class IngredientController {
    
    private final IngredientService ingredientService;

    @Autowired
    private IngredientController (IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    
    @GetMapping
    public List<Ingredient> getAllIngredients() {

        return ingredientService.getAllIngredients();

    }

}
