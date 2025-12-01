package com.application.shoppinglistmanager.shoppingLists;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.shoppinglistmanager.shoppingListRecipes.ShoppingListRecipes;
import com.application.shoppinglistmanager.shoppingListRecipes.ShoppingListRecipesRepository;

@RestController
@RequestMapping(path="/lists")
public class ShoppingListsController {
    
    private final ShoppingListsService shoppingListsService;
    private final ShoppingListRecipesRepository repo;

    @Autowired
    private ShoppingListsController (ShoppingListsService shoppingListsService, ShoppingListRecipesRepository repo) {
        this.shoppingListsService = shoppingListsService;
        this.repo = repo;
    }

    @GetMapping
    public List<ShoppingListsDto> getAllShoppingLists () {
    // 
        return shoppingListsService.getAllShoppingListsDtos();
    }

    // @GetMapping
    // public List<ShoppingLists> getAllShoppingLists () {
    //     return shoppingListsService.getAllShoppingLists();
    // }
}
