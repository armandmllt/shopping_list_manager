package com.application.shoppinglistmanager.shoppingLists;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/lists")
public class ShoppingListsController {
    
    private final shoppingListsService shoppingListsService;

    @Autowired
    private ShoppingListsController (shoppingListsService shoppingListsService) {
        this.shoppingListsService = shoppingListsService;
    }

    @GetMapping
    public List<ShoppingLists> getAllShoppingLists () {
        
        return shoppingListsService.getAllShoppingLists();

    }

}
