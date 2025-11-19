package com.application.shoppinglistmanager.shoppingListRecipes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/list-recipes")
public class ShoppingListRecipesController {
    
    private final ShoppingListRecipesService shoppingListRecipesService;

    @Autowired
    private ShoppingListRecipesController (ShoppingListRecipesService shoppingListRecipesService) {
        this.shoppingListRecipesService = shoppingListRecipesService;
    }

    @GetMapping
    public List<ShoppingListRecipes> getAllShoppingListRecipes () {
        return shoppingListRecipesService.getAllShoppingListRecipes();
    }
}
