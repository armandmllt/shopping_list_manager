package com.application.shoppinglistmanager.shoppingLists;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class shoppingListsService {

    private final ShoppingListsRepository shoppingListsRepository;

    @Autowired
    public shoppingListsService (ShoppingListsRepository shoppingListsRepository) {
        this.shoppingListsRepository = shoppingListsRepository;
    }

    public List<ShoppingLists> getAllShoppingLists () {
        return shoppingListsRepository.findAll();
    }
}
