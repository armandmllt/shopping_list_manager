package com.application.shoppinglistmanager.shopping_lists;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.shoppinglistmanager.shopping_list_recipes.ShoppingListRecipesRepository;

@RestController
@RequestMapping(path="/lists")
public class ShoppingListsController {
    
    private final ShoppingListsService shoppingListsService;

    @Autowired
    private ShoppingListsController (ShoppingListsService shoppingListsService, ShoppingListRecipesRepository repo) {
        this.shoppingListsService = shoppingListsService;
    }

    @GetMapping
    public List<ShoppingListsDto> getAllShoppingLists () {
        return shoppingListsService.getAllShoppingLists();
    }

    @GetMapping("/{userId}")
    public ShoppingListsDto getShoppingListByUserId (@PathVariable Integer userId) {
        return shoppingListsService.getShoppingListByUserId(userId);
    }
}
