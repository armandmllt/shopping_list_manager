package com.application.shoppinglistmanager.ingredients;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IngredientsMapper {
    @Mapping(source = "idIngredient", target = "id")
    IngredientsDto toDto (Ingredients entity); 

    @Mapping(source = "id", target = "idIngredient")
    Ingredients toIngredient (IngredientsDto dto);

    List<IngredientsDto> toDtos (List<Ingredients> ingredients);
    List<Ingredients> toIngredients (List<IngredientsDto> dtos);
}
