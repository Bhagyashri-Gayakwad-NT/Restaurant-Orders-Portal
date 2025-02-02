package com.nt.restaurant.microservice.dtoconvertion;

import com.nt.restaurant.microservice.dto.FoodCategoryInDTO;
import com.nt.restaurant.microservice.dto.FoodCategoryOutDTO;
import com.nt.restaurant.microservice.entities.FoodCategory;


/**
 * Utility class to convert between {@link FoodCategoryInDTO} and {@link FoodCategory} entities,
 * and between {@link FoodCategory} entities and {@link FoodCategoryOutDTO}.
 */
public class FoodCategoryDtoConverter {

  /**
   * Converts a {@link FoodCategoryInDTO} to a {@link FoodCategory} entity.
   *
   * @param foodCategoryInDTO the DTO containing food category input data.
   * @return the {@link FoodCategory} entity populated with input data.
   */
  public static FoodCategory convertToEntity(final FoodCategoryInDTO foodCategoryInDTO) {
    return new FoodCategory(
      foodCategoryInDTO.getRestaurantId(),
      foodCategoryInDTO.getFoodCategoryName().toUpperCase()
    );
  }

  /**
   * Converts a {@link FoodCategory} entity to a {@link FoodCategoryOutDTO}.
   *
   * @param foodCategory the entity representing the food category.
   * @return a {@link FoodCategoryOutDTO} populated with food category data.
   */
  public static FoodCategoryOutDTO convertToOutDTO(final FoodCategory foodCategory) {
    return new FoodCategoryOutDTO(
      foodCategory.getFoodCategoryId(),
      foodCategory.getRestaurantId(),
      foodCategory.getFoodCategoryName()
    );
  }
}
