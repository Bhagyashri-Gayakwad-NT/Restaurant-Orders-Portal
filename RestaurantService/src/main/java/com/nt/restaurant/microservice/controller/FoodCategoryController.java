package com.nt.restaurant.microservice.controller;

import com.nt.restaurant.microservice.indto.FoodCategoryInDTO;
import com.nt.restaurant.microservice.outdto.FoodCategoryOutDTO;
import com.nt.restaurant.microservice.service.FoodCategoryservice;
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

  @Autowired
  private FoodCategoryservice foodCategoryService;

  @PostMapping("/addFoodCategory")
  public ResponseEntity<FoodCategoryOutDTO> addFoodCategory(@Valid @RequestBody FoodCategoryInDTO foodCategoryInDTO) {
    FoodCategoryOutDTO foodCategoryOutDTO = foodCategoryService.addFoodCategory(foodCategoryInDTO);
    return ResponseEntity.ok(foodCategoryOutDTO);
  }

  @GetMapping("/foodCategoryByRestaurantId/{restaurantId}")
  public ResponseEntity<List<FoodCategoryOutDTO>> getFoodCategoryByRestaurantId(@PathVariable("restaurantId") Integer restaurantId) {
    List<FoodCategoryOutDTO> foodCategories = foodCategoryService.getFoodCategoryByRestaurantId(restaurantId);
    return ResponseEntity.ok(foodCategories);
  }

  @PutMapping("/foodCategoryByFoodCategoryId/{id}")
  public ResponseEntity<FoodCategoryOutDTO> updateFoodCategory(@PathVariable("id") Integer id,
                                                               @Valid @RequestBody FoodCategoryInDTO foodCategoryInDTO) {
    FoodCategoryOutDTO updatedCategory = foodCategoryService.updateFoodCategory(id, foodCategoryInDTO);
    return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
  }
}
