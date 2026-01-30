package com.application.shoppinglistmanager.recipes;

import java.util.ArrayList;
import java.util.List;

import com.application.shoppinglistmanager.recipe_ingredients.RecipeIngredients;
import com.application.shoppinglistmanager.shopping_list_recipes.ShoppingListRecipes;

import jakarta.persistence.CascadeType;
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

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true) //chatgpt says CascadeType.ALL and orphanRemoval = true, try maybe later
    private List<RecipeIngredients> ingredients = new ArrayList<>();
    /*Note : new ArrayList<>() initializes the array. It allows that when doing ingredients.add() (in addIngredient method)
    there isn't a null pointer exception when trying to add an element to an inexisting list*/

    //id-free creator
    public Recipes (String name, String instructions) {
        this.name = name;
        this.instructions = instructions;
    }

    /**
     * Explains explicitely to JPA the bidirectional relationship between Recipes and Ingredients
     * @param ri the linking table RecipeIngredients
     */
    public void addIngredient(RecipeIngredients ri) {
        ingredients.add(ri); //adds the ingredient
        ri.setRecipe(this); //to this specific recipe
    }
}
