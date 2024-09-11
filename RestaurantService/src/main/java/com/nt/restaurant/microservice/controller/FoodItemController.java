package com.nt.restaurant.microservice.controller;

import com.nt.restaurant.microservice.indto.FoodItemInDTO;
import com.nt.restaurant.microservice.outdto.CommonResponse;
import com.nt.restaurant.microservice.outdto.FoodItemOutDTO;
import com.nt.restaurant.microservice.service.FoodItemService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/foodItems")
public class FoodItemController {

  private static final Logger logger = LogManager.getLogger(FoodItemController.class);

  @Autowired
  private FoodItemService foodItemService;

  @PostMapping("/addFoodItem")
  public ResponseEntity<CommonResponse> addFoodItem(@Valid @ModelAttribute FoodItemInDTO foodItemInDTO) {
    logger.info("Received request to add food item: {}", foodItemInDTO);
    CommonResponse response = foodItemService.addFoodItem(foodItemInDTO);
    logger.info("Successfully added food item with ID: {}", response.getMessage());
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @GetMapping("/getFoodItem/{categoryId}")
  public ResponseEntity<List<FoodItemOutDTO>> getFoodItemsByCategory(@PathVariable Integer categoryId) {
    logger.info("Fetching food items for category ID: {}", categoryId);
    List<FoodItemOutDTO> foodItems = foodItemService.getFoodItemsByCategory(categoryId);
    logger.info("Successfully retrieved {} food items for category ID: {}", foodItems.size(), categoryId);
    return ResponseEntity.ok(foodItems);
  }

  @GetMapping("getFoodItems/{restaurantId}")
  public ResponseEntity<List<FoodItemOutDTO>> getFoodItemsByRestaurant(@PathVariable Integer restaurantId) {
    logger.info("Fetching food items for restaurant ID: {}", restaurantId);
    List<FoodItemOutDTO> foodItems = foodItemService.getFoodItemsByRestaurant(restaurantId);
    logger.info("Successfully retrieved {} food items for restaurant ID: {}", foodItems.size(), restaurantId);
    return ResponseEntity.ok(foodItems);
  }

  @PutMapping("/updateFoodItem/{foodItemId}")
  public ResponseEntity<CommonResponse> updateFoodItemByFoodItemId(
    @PathVariable Integer foodItemId,
    @Valid @ModelAttribute FoodItemInDTO foodItemInDTO) {

    logger.info("Received request to update food item with ID: {} with details: {}", foodItemId, foodItemInDTO);
    CommonResponse updatedFoodItem = foodItemService.updateFoodItemByFoodItemId(foodItemId, foodItemInDTO);
    logger.info("Successfully updated food item with ID: {}", foodItemId);
    return new ResponseEntity<>(updatedFoodItem, HttpStatus.OK);
  }


  @GetMapping("/{id}/image")
  public ResponseEntity<byte[]> getFoodItemImage(@PathVariable Integer id) {
    logger.info("Fetching image for food item with ID: {}", id);
    byte[] imageData = foodItemService.getFoodItemImage(id);
    logger.info("Successfully retrieved image for food item with ID: {}", id);
    return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageData);
  }
}
