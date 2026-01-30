package com.application.shoppinglistmanager.exception;

public class IngredientNotFoundException extends RuntimeException {
    public IngredientNotFoundException(Integer ingredientId) {
        super("L'ingrédient d'id " + ingredientId + " n'existe pas en BDD.");
    }
}

