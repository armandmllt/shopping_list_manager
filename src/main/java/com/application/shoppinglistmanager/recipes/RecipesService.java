package com.application.shoppinglistmanager.recipes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipesService {
    
    private final RecipesRepository recipesRepository;
    private final RecipesMapper recipesMapper;

    @Autowired
    public RecipesService (RecipesRepository recipesRepository, RecipesMapper recipesMapper) {
        this.recipesRepository = recipesRepository;
        this.recipesMapper = recipesMapper;
    }

    public List<Recipes> getAllRecipes () {
        return recipesRepository.findAll();
    }

    public List<RecipesDto> getAllRecipesDtos () {
        List<Recipes> allRecipes = recipesRepository.findAll();
        return recipesMapper.fromRecipesToDtos(allRecipes);
    }

}
