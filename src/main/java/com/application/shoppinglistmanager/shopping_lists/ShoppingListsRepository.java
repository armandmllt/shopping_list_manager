package com.application.shoppinglistmanager.shopping_lists;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingListsRepository extends JpaRepository <ShoppingLists, Integer> {

    Optional<ShoppingLists> findByUserId(Integer userId);

}