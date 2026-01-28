package com.application.shoppinglistmanager.ingredients;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

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
            () -> new EntityNotFoundException("Pas d'ingrédient d'id " + ingredientId + " trouvé en BDD.")
        );
        return ingredientsMapper.toDto(ingredient);
    }

    public IngredientsDto updateIngredientById(Integer ingredientId, IngredientsDto ingredient) {
        Ingredients existingIngredient = ingredientsRepository.findById(ingredientId).orElseThrow(
            () -> new EntityNotFoundException("Pas d'ingrédient d'id " + ingredientId + " trouvé en BDD.")
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
        if (ingredientsRepository.findById(ingredientId).isEmpty()) {
            throw new EntityNotFoundException("Pas d'ingrédient d'id " + ingredientId + " trouvé en BDD.");
        }
        ingredientsRepository.deleteById(ingredientId);
    }
}