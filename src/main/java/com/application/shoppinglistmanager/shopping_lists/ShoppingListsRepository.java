package com.application.shoppinglistmanager.shopping_lists;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingListsRepository extends JpaRepository <ShoppingLists, Integer> {

}