package com.application.shoppinglistmanager.recipes;

import java.util.List;

import com.application.shoppinglistmanager.ingredients.IngredientsDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecipesDto {
    private Integer id;

    private List<IngredientsDto> ingredients;

    private String name;
}
