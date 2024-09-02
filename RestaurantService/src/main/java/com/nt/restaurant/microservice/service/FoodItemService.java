package com.nt.restaurant.microservice.service;

import com.nt.restaurant.microservice.indto.FoodItemInDTO;
import com.nt.restaurant.microservice.outdto.FoodItemOutDTO;

import java.util.List;

public interface FoodItemService {
  FoodItemOutDTO addFoodItem(FoodItemInDTO foodItemInDTO);
  List<FoodItemOutDTO> getFoodItemsByCategory(Integer categoryId);
  List<FoodItemOutDTO> getFoodItemsByRestaurant(Integer restaurantId);
  FoodItemOutDTO updateFoodItemByFoodItemId(Integer foodItemId, FoodItemInDTO foodItemInDTO);
}
