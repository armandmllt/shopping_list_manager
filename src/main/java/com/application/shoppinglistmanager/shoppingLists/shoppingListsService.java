package com.application.shoppinglistmanager.shoppingLists;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.shoppinglistmanager.shoppingListRecipes.ShoppingListRecipes;
import com.application.shoppinglistmanager.shoppingListRecipes.ShoppingListRecipesRepository;

import jakarta.transaction.Transactional;

@Service
public class ShoppingListsService {

    private final ShoppingListsRepository shoppingListsRepository;
    private final ShoppingListsMapper shoppingListsMapper;
    private final ShoppingListRecipesRepository repo;

    @Autowired
    public ShoppingListsService (ShoppingListsRepository shoppingListsRepository, ShoppingListsMapper shoppingListsMapper, ShoppingListRecipesRepository repo) {
        this.shoppingListsRepository = shoppingListsRepository;
        this.shoppingListsMapper = shoppingListsMapper;
        this.repo = repo;
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
