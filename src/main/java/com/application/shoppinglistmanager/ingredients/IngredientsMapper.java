package com.application.shoppinglistmanager.ingredients;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IngredientsMapper {
    @Mapping(source = "idIngredient", target = "id")
    IngredientsDto fromIngredientToDto (Ingredients entity); 

    @Mapping(source = "id", target = "idIngredient")
    Ingredients fromDtoToIngredient (IngredientsDto dto);
}
