package com.application.shoppinglistmanager.exception;

public class IncorrectIngredientException extends RuntimeException {
    public IncorrectIngredientException(Integer ingredientId) {
        super("The recipe uses an ingredient of Id " + ingredientId + " which does not exist in the database.");
    }
}

