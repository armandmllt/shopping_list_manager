package com.application.shoppinglistmanager.recipesIngredients;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipesIngredientsRepository extends JpaRepository <RecipesIngredients, Integer>{
    
}
