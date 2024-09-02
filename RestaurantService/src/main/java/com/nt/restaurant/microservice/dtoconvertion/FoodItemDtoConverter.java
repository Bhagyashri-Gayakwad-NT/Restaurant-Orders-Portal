package com.nt.restaurant.microservice.dtoconvertion;

import com.nt.restaurant.microservice.entities.FoodItem;
import com.nt.restaurant.microservice.indto.FoodItemInDTO;
import com.nt.restaurant.microservice.outdto.FoodItemOutDTO;

import java.io.IOException;

public class FoodItemDtoConverter {
  public static FoodItem inDtoToEntity(FoodItemInDTO foodItemInDTO) {
    FoodItem foodItem = new FoodItem();
    foodItem.setCategoryId(foodItemInDTO.getFoodCategoryId());
    foodItem.setRestaurantId(foodItemInDTO.getRestaurantId());
    foodItem.setFoodItemName(foodItemInDTO.getFoodItemName().toUpperCase());
    foodItem.setDescription(foodItemInDTO.getDescription());
    foodItem.setPrice(foodItemInDTO.getPrice());
    foodItem.setAvailable(true);

    try {
      foodItem.setFoodItemImage(foodItemInDTO.getFoodItemImage().getBytes());
    } catch (IOException e) {
      e.printStackTrace();
    }

    return foodItem;
  }

  public static FoodItemOutDTO entityToOutDTO(FoodItem foodItem) {
    return new FoodItemOutDTO(
      foodItem.getFoodItemId(),
      foodItem.getCategoryId(),
      foodItem.getRestaurantId(),
      foodItem.getFoodItemName(),
      foodItem.getDescription(),
      foodItem.getPrice(),
      foodItem.isAvailable(),
      foodItem.getFoodItemImage()
    );
  }
}
