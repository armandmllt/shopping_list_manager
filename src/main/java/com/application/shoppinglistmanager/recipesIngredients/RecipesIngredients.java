package com.application.shoppinglistmanager.recipesIngredients;

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
public class RecipesIngredients {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_recipe_ingredient")
    private Integer idRecipeIngredient;

    @Column(name="fk_recipe")
    private Integer fkRecipe;

    @Column(name="fk_ingredient")
    private Integer fkIngredient;

    private int quantity;

    private String unit;

}
