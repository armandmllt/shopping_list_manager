package com.application.shoppinglistmanager.ingredients;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.shoppinglistmanager.exception.IngredientNotFoundException;

@Service
public class IngredientsService {

    private final IngredientsRepository ingredientsRepository;
    private final IngredientsMapper ingredientsMapper;

    @Autowired
    public IngredientsService (IngredientsRepository ingredientsRepository, IngredientsMapper ingredientsMapper) {
        this.ingredientsRepository = ingredientsRepository;
        this.ingredientsMapper = ingredientsMapper;
    }

    public List<IngredientsDto> getAllIngredients() {
        List<Ingredients> ingredients = ingredientsRepository.findAll();
        return ingredientsMapper.toDtos(ingredients);
    }

    public IngredientsDto getIngredientById(Integer ingredientId) {
        Ingredients ingredient =  ingredientsRepository.findById(ingredientId).orElseThrow(
            () -> new IngredientNotFoundException(ingredientId)
        );
        return ingredientsMapper.toDto(ingredient);
    }

    public IngredientsDto updateIngredientById(Integer ingredientId, IngredientsDto ingredient) {
        Ingredients existingIngredient = ingredientsRepository.findById(ingredientId).orElseThrow(
            () -> new IngredientNotFoundException(ingredientId)
            );
        
        existingIngredient.setName(ingredient.getName());
        ingredientsRepository.save(existingIngredient);

        return ingredientsMapper.toDto(existingIngredient);
    }

    public IngredientsDto createIngredient (IngredientsDto ingredient) {
        Ingredients savedIngredient = ingredientsRepository.save(ingredientsMapper.toIngredient(ingredient));
        return ingredientsMapper.toDto(savedIngredient);
    }

    public void deleteIngredientById(Integer ingredientId) {
        //TODO fix this, it breaks
        if (ingredientsRepository.findById(ingredientId).isEmpty()) {
            throw new IngredientNotFoundException(ingredientId);
        }
        ingredientsRepository.deleteById(ingredientId);
    }
}