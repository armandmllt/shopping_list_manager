package com.application.shoppinglistmanager.exception;

public class RecipeNotFoundException extends RuntimeException{
    public RecipeNotFoundException(Integer recipeId) {
        super("The recipe of Id " + recipeId + " does not exist in the database.");
    }
}
