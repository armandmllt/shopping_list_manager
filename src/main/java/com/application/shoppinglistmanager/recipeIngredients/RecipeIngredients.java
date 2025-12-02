package com.application.shoppinglistmanager.recipeIngredients;

import com.application.shoppinglistmanager.ingredients.Ingredients;
import com.application.shoppinglistmanager.recipes.Recipes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne //(fetch = FetchType.EAGER) //Add CASCADE-TYPE + FetchType ?
    @JoinColumn(name="fk_recipe", referencedColumnName = "id_recipe")
    private Recipes recipe;

    @ManyToOne //(fetch = FetchType.EAGER) //Add CASCADE-TYPE + FetchType ?
    @JoinColumn(name="fk_ingredient", referencedColumnName = "id_ingredient")
    private Ingredients ingredient;

    private Integer quantity;

    private String unit;

    //id-free creator
    public RecipeIngredients (Recipes recipe, Ingredients ingredient, Integer quantity, String unit) {
        this.recipe = recipe;
        this.ingredient = ingredient;
        this.quantity = quantity;
        this.unit = unit;
    }
}
