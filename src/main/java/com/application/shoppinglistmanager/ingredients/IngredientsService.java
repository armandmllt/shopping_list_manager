package com.application.shoppinglistmanager.ingredients;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientsService {

    private final IngredientsRepository ingredientsRepository;

    @Autowired
    public IngredientsService (IngredientsRepository ingredientsRepository) {
        this.ingredientsRepository = ingredientsRepository;
    }

    public List<Ingredients> getAllIngredients() {
        return ingredientsRepository.findAll();
    }
}