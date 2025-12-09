package com.application.shoppinglistmanager.shopping_lists;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.shoppinglistmanager.shopping_list_recipes.ShoppingListRecipesRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ShoppingListsService {

    private final ShoppingListsRepository shoppingListsRepository;
    private final ShoppingListsMapper shoppingListsMapper;

    @Autowired
    public ShoppingListsService (ShoppingListsRepository shoppingListsRepository, ShoppingListsMapper shoppingListsMapper, ShoppingListRecipesRepository repo) {
        this.shoppingListsRepository = shoppingListsRepository;
        this.shoppingListsMapper = shoppingListsMapper;
    }

    public List<ShoppingListsDto> getAllShoppingLists () {
        List<ShoppingLists> allLists = shoppingListsRepository.findAll();
        return shoppingListsMapper.fromShoppingListsToDtos(allLists);
    }

    /**
     * Returns the DTO of the user's ShoppingList (user <-> shoppingList is a one-to-one relationship)
     * @param userId the id of the User 
     * @throws
     * @return the shoppingList's DTO
     */
    public ShoppingListsDto getShoppingListByUserId (Integer userId) {
        ShoppingLists shoppingList = shoppingListsRepository.findByUserId(userId).orElseThrow(
            () -> new EntityNotFoundException("Pas d'utilisateur d'id " + userId + " trouvé dans la BDD. \u200D"));
        return shoppingListsMapper.fromShoppingListToDto(shoppingList);
        //l'exception est récupérée, mais pas de message -> voir GlobalExceptionHandler
    }
}
