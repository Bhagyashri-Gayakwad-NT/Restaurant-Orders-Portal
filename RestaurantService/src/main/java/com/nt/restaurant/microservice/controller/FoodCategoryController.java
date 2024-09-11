package com.nt.restaurant.microservice.controller;

import com.nt.restaurant.microservice.indto.FoodCategoryInDTO;
import com.nt.restaurant.microservice.outdto.CommonResponse;
import com.nt.restaurant.microservice.outdto.FoodCategoryOutDTO;
import com.nt.restaurant.microservice.service.FoodCategoryservice;
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

@RestController
@CrossOrigin
@RequestMapping("/categories")
public class FoodCategoryController {

  private static final Logger logger = LogManager.getLogger(FoodCategoryController.class);

  @Autowired
  private FoodCategoryservice foodCategoryService;

  @PostMapping("/addFoodCategory")
  public ResponseEntity<CommonResponse> addFoodCategory(@Valid @RequestBody FoodCategoryInDTO foodCategoryInDTO) {
    logger.info("Received request to add food category: {}", foodCategoryInDTO);
    CommonResponse response = foodCategoryService.addFoodCategory(foodCategoryInDTO);
    logger.info("Successfully added food category with ID: {}", response.getMessage());
    return ResponseEntity.ok(response);
  }

  @GetMapping("/foodCategoryByRestaurantId/{restaurantId}")
  public ResponseEntity<List<FoodCategoryOutDTO>> getFoodCategoryByRestaurantId(@PathVariable("restaurantId") Integer restaurantId) {
    logger.info("Fetching food categories for restaurant ID: {}", restaurantId);
    List<FoodCategoryOutDTO> foodCategories = foodCategoryService.getFoodCategoryByRestaurantId(restaurantId);
    logger.info("Successfully retrieved {} food categories for restaurant ID: {}", foodCategories.size(), restaurantId);
    return ResponseEntity.ok(foodCategories);
  }

  @PutMapping("/updateFoodCategory/{id}")
  public ResponseEntity<CommonResponse> updateFoodCategory(@PathVariable("id") Integer id,
                                                           @Valid @RequestBody FoodCategoryInDTO foodCategoryInDTO) {
    logger.info("Received request to update food category with ID: {} with details: {}", id, foodCategoryInDTO);
    CommonResponse updatedCategory = foodCategoryService.updateFoodCategory(id, foodCategoryInDTO);
    logger.info("Successfully updated food category with ID: {}", id);
    return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
  }
}
