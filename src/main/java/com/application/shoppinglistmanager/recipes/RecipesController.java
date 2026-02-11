package com.application.shoppinglistmanager.recipes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.shoppinglistmanager.users.Users;
import com.application.shoppinglistmanager.users.UsersDto;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping(path="/recipes")
public class RecipesController {
    
    private final RecipesService recipesService;

    @Autowired
    private RecipesController (RecipesService recipesService) {
        this.recipesService = recipesService;
    }

    @GetMapping
    public ResponseEntity<List<RecipesDto>> getAllRecipes () {
        List<RecipesDto> allRecipes = recipesService.getAllRecipes();
        return ResponseEntity.ok(allRecipes);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<RecipesDto> getRecipeById (@PathVariable Integer id) {
        RecipesDto recipe = recipesService.getRecipeById(id);
        return ResponseEntity.ok(recipe);
    }

    /*
    Note : it is possible, in the POST request's body, to only send each ingredient's id and not the ingredient's name
    Because in the service layer, each ingredient is fetched from the DB by its id 
    IMPORTANT : the quantity and unit of each ingredients is obviously still required
    */
    @PostMapping
    public ResponseEntity<RecipesDto> createRecipe (@RequestBody RecipesDto recipe) {
        RecipesDto createdRecipe = recipesService.createRecipe(recipe);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(createdRecipe);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteRecipeById (@PathVariable Integer id) {
        recipesService.deleteRecipeById(id);
        //returns httpStatus 204 NO CONTENT
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<RecipesDto> updateRecipeById (@RequestBody RecipesDto recipe, @PathVariable Integer id) {
        RecipesDto savedRecipe = recipesService.updateRecipeById(id, recipe);
        return ResponseEntity.ok(savedRecipe);
    }

}
