package com.application.shoppinglistmanager.shoppingListRecipe;

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
public class ShoppingListRecipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_shopping_list_recipe")
    private Integer idShoppingListRecipe;

    @Column(name="fk_shopping_list")
    private Integer fkShoppingList;

    @Column(name="fk_recipe")
    private Integer fkRecipe;

    private Integer servings;
    
}
