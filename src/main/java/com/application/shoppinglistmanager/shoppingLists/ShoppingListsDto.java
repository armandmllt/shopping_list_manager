package com.application.shoppinglistmanager.shoppingLists;

import java.util.List;

import com.application.shoppinglistmanager.recipes.RecipesDto;
import com.application.shoppinglistmanager.users.UsersDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingListsDto {
    
    private Integer id;

    private UsersDto user;

    private List<RecipesDto> recipes;
}
