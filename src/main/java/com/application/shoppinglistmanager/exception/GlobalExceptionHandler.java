package com.application.shoppinglistmanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public CustomError handleEntityNotFoundException (EntityNotFoundException exception) {
        return new CustomError(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RecipeNotFoundException.class)
    public CustomError handleRecipeNotFoundException (RecipeNotFoundException exception) {
        return new CustomError(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(IngredientNotFoundException.class)
    public CustomError handleIngredientNotFoundException (IngredientNotFoundException exception) {
        return new CustomError(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(IncorrectIngredientException.class)
    public CustomError handleIngredientNotFoundException (IncorrectIngredientException exception) {
        return new CustomError(HttpStatus.UNPROCESSABLE_ENTITY, exception.getMessage());
    }

}
