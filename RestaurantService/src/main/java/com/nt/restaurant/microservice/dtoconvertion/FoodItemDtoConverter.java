package com.nt.restaurant.microservice.dtoconvertion;

import com.nt.restaurant.microservice.dto.FoodItemInDTO;
import com.nt.restaurant.microservice.dto.FoodItemOutDTO;
import com.nt.restaurant.microservice.entities.FoodItem;

import java.io.IOException;

/**
 * Utility class to convert between {@link FoodItemInDTO} and {@link FoodItem} entities,
 * and between {@link FoodItem} entities and {@link FoodItemOutDTO}.
 */
public class FoodItemDtoConverter {

  /**
   * Converts a {@link FoodItemInDTO} to a {@link FoodItem} entity.
   *
   * @param foodItemInDTO the DTO containing food item input data.
   * @return the {@link FoodItem} entity populated with input data.
   */
  public static FoodItem inDtoToEntity(final FoodItemInDTO foodItemInDTO) {
    FoodItem foodItem = new FoodItem();
    foodItem.setCategoryId(foodItemInDTO.getFoodCategoryId());
    foodItem.setRestaurantId(foodItemInDTO.getRestaurantId());
    foodItem.setFoodItemName(foodItemInDTO.getFoodItemName().toUpperCase());
    foodItem.setDescription(foodItemInDTO.getDescription().trim());
    foodItem.setPrice(foodItemInDTO.getPrice());
    foodItem.setAvailable(true);

    try {
      foodItem.setFoodItemImage(foodItemInDTO.getFoodItemImage().getBytes());
    } catch (IOException e) {
      e.printStackTrace();
    }

    return foodItem;
  }

  /**
   * Converts a {@link FoodItem} entity to a {@link FoodItemOutDTO}.
   *
   * @param foodItem the entity representing the food item.
   * @return a {@link FoodItemOutDTO} populated with food item data.
   */
  public static FoodItemOutDTO entityToOutDTO(final FoodItem foodItem) {
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

