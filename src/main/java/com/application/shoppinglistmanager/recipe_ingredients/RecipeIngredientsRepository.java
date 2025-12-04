package com.application.shoppinglistmanager.recipe_ingredients;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeIngredientsRepository extends JpaRepository <RecipeIngredients, Integer>{
    
}
