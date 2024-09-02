package com.nt.restaurant.microservice.controller;

import com.nt.restaurant.microservice.exception.AlreadyExistException;
import com.nt.restaurant.microservice.exception.NotFoundException;
import com.nt.restaurant.microservice.indto.FoodItemInDTO;
import com.nt.restaurant.microservice.outdto.FoodItemOutDTO;
import com.nt.restaurant.microservice.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
@RequestMapping("/foodItems")
public class FoodItemController {
  @Autowired
  private FoodItemService foodItemService;

  @PostMapping("/addFoodItem")
  public ResponseEntity<FoodItemOutDTO> addFoodItem(@Valid @ModelAttribute FoodItemInDTO foodItemInDTO) {
    FoodItemOutDTO foodItemOutDTO = foodItemService.addFoodItem(foodItemInDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(foodItemOutDTO);
  }
  @GetMapping("/getFoodItem/{categoryId}")
  public ResponseEntity<List<FoodItemOutDTO>> getFoodItemsByCategory(@PathVariable Integer categoryId) {
    List<FoodItemOutDTO> foodItems = foodItemService.getFoodItemsByCategory(categoryId);
    return ResponseEntity.ok(foodItems);
  }
  @GetMapping("getFoodItems/{restaurantId}")
  public ResponseEntity<List<FoodItemOutDTO>> getFoodItemsByRestaurant(@PathVariable Integer restaurantId) {
    List<FoodItemOutDTO> foodItems = foodItemService.getFoodItemsByRestaurant(restaurantId);
    return ResponseEntity.ok(foodItems);
  }

  @PutMapping("/updateFoodItem/{foodItemId}")
  public ResponseEntity<FoodItemOutDTO> updateFoodItemByFoodItemId(
    @PathVariable Integer foodItemId,
    @RequestBody FoodItemInDTO foodItemInDTO) {
    try {
      FoodItemOutDTO updatedFoodItem = foodItemService.updateFoodItemByFoodItemId(foodItemId, foodItemInDTO);
      return new ResponseEntity<>(updatedFoodItem, HttpStatus.OK);
    } catch (NotFoundException e) {
      return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    } catch (AlreadyExistException e) {
      return new ResponseEntity<>(null, HttpStatus.CONFLICT);
    }
  }
}
