package com.nt.restaurant.microservice.service;

import com.nt.restaurant.microservice.dto.CommonResponse;
import com.nt.restaurant.microservice.dto.FoodCategoryInDTO;
import com.nt.restaurant.microservice.dto.FoodCategoryOutDTO;

import java.util.List;

/**
 * Service interface for managing food categories.
 */
public interface FoodCategoryService {

  /**
   * Adds a new food category to the system.
   *
   * @param foodCategoryInDTO the data transfer object containing the details of the food category to be added.
   * @return a {@link CommonResponse} indicating the result of the operation, including status and message.
   */
  CommonResponse addFoodCategory(FoodCategoryInDTO foodCategoryInDTO);

  /**
   * Retrieves a list of food categories for a specific restaurant.
   *
   * @param restaurantId the ID of the restaurant whose food categories are to be retrieved.
   * @return a {@link List} of {@link FoodCategoryOutDTO} objects representing the food categories for the specified restaurant.
   */
  List<FoodCategoryOutDTO> getFoodCategoryByRestaurantId(Integer restaurantId);

  /**
   * Updates the details of an existing food category.
   *
   * @param foodCategoryId    the ID of the food category to be updated.
   * @param foodCategoryInDTO the data transfer object containing the updated details of the food category.
   * @return a {@link CommonResponse} indicating the result of the operation, including status and message.
   */
  CommonResponse updateFoodCategory(Integer foodCategoryId, FoodCategoryInDTO foodCategoryInDTO);
}
