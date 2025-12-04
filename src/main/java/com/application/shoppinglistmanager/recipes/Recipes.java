package com.application.shoppinglistmanager.recipes;

import java.util.List;

import com.application.shoppinglistmanager.recipe_ingredients.RecipeIngredients;
import com.application.shoppinglistmanager.shopping_list_recipes.ShoppingListRecipes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Recipes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_recipe")
    private Integer idRecipe;

    private String name;

    private String instructions;

    @OneToMany(mappedBy = "recipe")
    private List<ShoppingListRecipes> shoppingListRecipes;

    @OneToMany(mappedBy = "recipe")
    private List<RecipeIngredients> ingredients;

    //id-free creator
    public Recipes (String name, String instructions) {
        this.name = name;
        this.instructions = instructions;
    }
}
