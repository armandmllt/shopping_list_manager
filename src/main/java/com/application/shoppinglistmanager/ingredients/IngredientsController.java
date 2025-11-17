package com.application.shoppinglistmanager.ingredients;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
    public List<Ingredients> getAllIngredients() {

        return ingredientsService.getAllIngredients();

    }

}
