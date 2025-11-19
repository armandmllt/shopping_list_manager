package com.application.shoppinglistmanager.recipes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipesService {
    
    private final RecipesRepository recipesRepository;

    @Autowired
    public RecipesService (RecipesRepository recipesRepository) {
        this.recipesRepository = recipesRepository;
    }

    public List<Recipes> getAllRecipes () {
        return recipesRepository.findAll();
    }

}
