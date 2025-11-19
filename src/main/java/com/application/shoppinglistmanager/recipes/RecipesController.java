package com.application.shoppinglistmanager.recipes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/recipes")
public class RecipesController {
    
    private final RecipesService recipesService;

    @Autowired
    private RecipesController (RecipesService recipesService) {
        this.recipesService = recipesService;
    }

    @GetMapping
    public List<Recipes> getAllRecipes () {
        return recipesService.getAllRecipes();
    }

}
