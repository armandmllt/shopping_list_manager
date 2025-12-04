package com.application.shoppinglistmanager.shopping_lists;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.shoppinglistmanager.shopping_list_recipes.ShoppingListRecipesRepository;

@Service
public class ShoppingListsService {

    private final ShoppingListsRepository shoppingListsRepository;
    private final ShoppingListsMapper shoppingListsMapper;

    @Autowired
    public ShoppingListsService (ShoppingListsRepository shoppingListsRepository, ShoppingListsMapper shoppingListsMapper, ShoppingListRecipesRepository repo) {
        this.shoppingListsRepository = shoppingListsRepository;
        this.shoppingListsMapper = shoppingListsMapper;
    }

    public List<ShoppingLists> getAllShoppingLists () {
        return shoppingListsRepository.findAll();
    }

    //Rename to getAllShoppingLists when fixed
    public List<ShoppingListsDto> getAllShoppingListsDtos () {
        List<ShoppingLists> allLists = shoppingListsRepository.findAll();
        return shoppingListsMapper.fromShoppingListsToDtos(allLists);
    }
}
