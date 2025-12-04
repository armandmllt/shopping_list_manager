package com.application.shoppinglistmanager.shopping_list_recipes;

import com.application.shoppinglistmanager.recipes.Recipes;
import com.application.shoppinglistmanager.shopping_lists.ShoppingLists;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
public class ShoppingListRecipes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_shopping_list_recipe")
    private Integer idShoppingListRecipe;

    @ManyToOne //(fetch = FetchType.EAGER) //Add CASCADE-TYPE + FetchType ?
    @JoinColumn(name="fk_shopping_list", referencedColumnName = "id_list") 
    private ShoppingLists shoppingList;

    @ManyToOne //(fetch = FetchType.EAGER) //Add CASCADE-TYPE + FetchType ?
    @JoinColumn(name="fk_recipe", referencedColumnName = "id_recipe")
    private Recipes recipe;

    private Integer servings;
    
    //id-free creator
    public ShoppingListRecipes (ShoppingLists fkShoppingList, Recipes fkRecipe, Integer servings) {
        this.shoppingList = fkShoppingList;
        this.recipe = fkRecipe;
        this.servings = servings;
    }
}
