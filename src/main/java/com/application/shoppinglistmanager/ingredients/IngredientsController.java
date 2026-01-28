package com.application.shoppinglistmanager.ingredients;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/ingredients")
public class IngredientsController {
    
    private final IngredientsService ingredientsService;

    @Autowired
    private IngredientsController (IngredientsService ingredientsService) {
        this.ingredientsService = ingredientsService;
    }

    
    @GetMapping
    public ResponseEntity<List<IngredientsDto>> getAllIngredients() {
        return ResponseEntity.ok(ingredientsService.getAllIngredients());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<IngredientsDto> getIngredientById(@PathVariable Integer id) {
        return ResponseEntity.ok(ingredientsService.getIngredientById(id));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<IngredientsDto> updateIngredientById(@PathVariable Integer id, @RequestBody IngredientsDto ingredient) {
        return ResponseEntity.ok(ingredientsService.updateIngredientById(id, ingredient));
    }

    @PostMapping
    public ResponseEntity<IngredientsDto> createIngredient(@RequestBody IngredientsDto ingredient) {
        return ResponseEntity.ok(ingredientsService.createIngredient(ingredient));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteIngredientById(@PathVariable Integer id) {
        ingredientsService.deleteIngredientById(id);

        //returns httpStatus 204 NO CONTENT
        return ResponseEntity.noContent().build();
    }

}
