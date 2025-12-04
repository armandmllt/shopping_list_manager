package com.application.shoppinglistmanager.shopping_list_recipes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingListRecipesService {

    private final ShoppingListRecipesRepository shoppingListRecipesRepository;

    @Autowired
    private ShoppingListRecipesService (ShoppingListRecipesRepository repository) {
        this.shoppingListRecipesRepository = repository;
    }
    
    public List<ShoppingListRecipes> getAllShoppingListRecipes () {
        return shoppingListRecipesRepository.findAll();
    }

}
