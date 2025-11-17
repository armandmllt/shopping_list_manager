package com.application.shoppinglistmanager.recipeIngredients;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecipeIngredients {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_recipe_ingredient")
    private Integer idRecipeIngredient;

    @Column(name="fk_recipe")
    private Integer fkRecipe;

    @Column(name="fk_ingredient")
    private Integer fkIngredient;

    private Integer quantity;

    private String unit;

    //id-free creator
    public RecipeIngredients (Integer fkIngredient, Integer quantity, String unit) {
        this.fkIngredient = fkIngredient;
        this.quantity = quantity;
        this.unit = unit;
    }
}
