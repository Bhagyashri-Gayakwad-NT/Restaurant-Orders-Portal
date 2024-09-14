package com.nt.restaurant.microservice.service;

import com.nt.restaurant.microservice.entities.FoodItem;
import com.nt.restaurant.microservice.dto.FoodItemInDTO;
import com.nt.restaurant.microservice.dto.FoodItemUpdateInDTO;
import com.nt.restaurant.microservice.dto.CommonResponse;
import com.nt.restaurant.microservice.dto.FoodItemOutDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Service interface for managing food items in the restaurant microservice.
 * It provides methods to add, retrieve, and update food items, along with handling images.
 */
public interface FoodItemService {

  /**
   * Adds a new food item to the system along with an image.
   *
   * @param foodItemInDTO DTO containing details about the food item.
   * @param image         The image file associated with the food item.
   * @return A {@link CommonResponse} indicating the result of the add operation.
   */
  CommonResponse addFoodItem(FoodItemInDTO foodItemInDTO, MultipartFile image);

  /**
   * Retrieves a list of food items belonging to a specific food category.
   *
   * @param categoryId The ID of the food category.
   * @return A list of {@link FoodItemOutDTO} representing the food items in the specified category.
   */
  List<FoodItemOutDTO> getFoodItemsByCategory(Integer categoryId);

  /**
   * Retrieves a list of food items available in a specific restaurant.
   *
   * @param restaurantId The ID of the restaurant.
   * @return A list of {@link FoodItemOutDTO} representing the food items in the specified restaurant.
   */
  List<FoodItemOutDTO> getFoodItemsByRestaurant(Integer restaurantId);

  /**
   * Updates an existing food item by its ID.
   *
   * @param foodItemId          The ID of the food item to be updated.
   * @param foodItemUpdateInDTO DTO containing updated details of the food item.
   * @return A {@link CommonResponse} indicating the result of the update operation.
   */
  CommonResponse updateFoodItemByFoodItemId(Integer foodItemId, FoodItemUpdateInDTO foodItemUpdateInDTO);

  /**
   * Retrieves the image of a food item by its ID.
   *
   * @param id The ID of the food item.
   * @return A byte array representing the food item's image.
   */
  byte[] getFoodItemImage(Integer id);

  /**
   * Finds and returns a food item entity by its ID.
   *
   * @param id The ID of the food item.
   * @return The {@link FoodItem} entity if found.
   */
  FoodItem findFoodItemById(Integer id);
}
