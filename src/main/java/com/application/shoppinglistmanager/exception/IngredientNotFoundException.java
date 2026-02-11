package com.application.shoppinglistmanager.exception;

public class IngredientNotFoundException extends RuntimeException {
    public IngredientNotFoundException(Integer ingredientId) {
        super("The ingredient of Id " + ingredientId + " does not exist in the database.");
    }
}
