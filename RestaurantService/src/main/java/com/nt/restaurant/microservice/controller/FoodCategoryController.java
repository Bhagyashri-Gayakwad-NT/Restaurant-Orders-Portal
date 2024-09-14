package com.nt.restaurant.microservice.controller;

import com.nt.restaurant.microservice.dto.CommonResponse;
import com.nt.restaurant.microservice.dto.FoodCategoryOutDTO;
import com.nt.restaurant.microservice.service.FoodCategoryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller for handling food category-related operations such as adding, fetching, and updating food categories.
 */
@RestController
@CrossOrigin
@RequestMapping("/categories")
public class FoodCategoryController {

  /**
   * Logger to log important events related to food category operations.
   */
  private static final Logger logger = LogManager.getLogger(FoodCategoryController.class);

  /**
   * Service responsible for handling the business logic related to food categories.
   * This service is used for operations such as adding, fetching, and updating food categories.
   * <p>
   * The {@code foodCategoryService} is injected automatically by Spring's dependency injection mechanism,
   * ensuring that the controller has access to the appropriate implementation of the {@link FoodCategoryService}.
   */
  @Autowired
  private FoodCategoryService foodCategoryService;

  /**
   * Adds a new food category based on the provided {@link RestaurantController.FoodCategoryInDTO} data.
   *
   * @param foodCategoryInDTO the food category details to be added.
   * @return a response entity with a success message if the food category is added successfully.
   */
  @PostMapping("/addFoodCategory")
  public ResponseEntity<CommonResponse> addFoodCategory(@Valid @RequestBody RestaurantController.FoodCategoryInDTO foodCategoryInDTO) {
    logger.info("Received request to add food category: {}", foodCategoryInDTO);
    CommonResponse response = foodCategoryService.addFoodCategory(foodCategoryInDTO);
    logger.info("Successfully added food category with ID: {}", response.getMessage());
    return ResponseEntity.ok(response);
  }

  /**
   * Retrieves food categories for a specified restaurant by its restaurant ID.
   *
   * @param restaurantId the ID of the restaurant whose food categories are to be fetched.
   * @return a response entity containing the list of food categories for the specified restaurant.
   */
  @GetMapping("/foodCategory/{restaurantId}")
  public ResponseEntity<List<FoodCategoryOutDTO>> getFoodCategoryByRestaurantId(@PathVariable("restaurantId") Integer restaurantId) {
    logger.info("Fetching food categories for restaurant ID: {}", restaurantId);
    List<FoodCategoryOutDTO> foodCategories = foodCategoryService.getFoodCategoryByRestaurantId(restaurantId);
    logger.info("Successfully retrieved {} food categories for restaurant ID: {}", foodCategories.size(), restaurantId);
    return ResponseEntity.ok(foodCategories);
  }

  /**
   * Updates an existing food category based on its ID and the provided {@link RestaurantController.FoodCategoryInDTO} data.
   *
   * @param id                the ID of the food category to update.
   * @param foodCategoryInDTO the updated food category details.
   * @return a response entity with a success message if the food category is updated successfully.
   */
  @PutMapping("/updateFoodCategory/{id}")
  public ResponseEntity<CommonResponse> updateFoodCategory(@PathVariable("id") Integer id, @Valid @RequestBody
    RestaurantController.FoodCategoryInDTO foodCategoryInDTO) {
    logger.info("Received request to update food category with ID: {} with details: {}", id, foodCategoryInDTO);
    CommonResponse updatedCategory = foodCategoryService.updateFoodCategory(id, foodCategoryInDTO);
    logger.info("Successfully updated food category with ID: {}", id);
    return new ResponseEntity<CommonResponse>(updatedCategory, HttpStatus.OK);
  }
}

