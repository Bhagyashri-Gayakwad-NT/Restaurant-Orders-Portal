package com.nt.restaurant.microservice.dtoconvertion;

import com.nt.restaurant.microservice.entities.FoodCategory;
import com.nt.restaurant.microservice.indto.FoodCategoryInDTO;
import com.nt.restaurant.microservice.outdto.FoodCategoryOutDTO;

public class FoodCategoryDtoConverter {
  public static FoodCategory convertToEntity(FoodCategoryInDTO foodCategoryInDTO) {
    return new FoodCategory(
      null,
      foodCategoryInDTO.getRestaurantId(),
      foodCategoryInDTO.getFoodCategoryName().toUpperCase()
    );
  }

  public static FoodCategoryOutDTO convertToOutDTO(FoodCategory foodCategory) {
    return new FoodCategoryOutDTO(
      foodCategory.getFoodCategoryId(),
      foodCategory.getRestaurantId(),
      foodCategory.getFoodCategoryName()
    );
  }
}
