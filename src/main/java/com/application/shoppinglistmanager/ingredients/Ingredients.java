package com.application.shoppinglistmanager.ingredients;

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
public class Ingredients {

    //Constructor without ID (using the GenerationType.IDENTITY to add an id)
    public Ingredients (String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_ingredient")
    private Integer idIngredient;

    private String name;

    // @OneToMany(mappedBy = "ingredient")
    // private List<RecipeIngredients> recipes;
    
}
