package com.nt.restaurant.microservice.service;

import com.nt.restaurant.microservice.entities.FoodItem;
import com.nt.restaurant.microservice.indto.FoodItemInDTO;
import com.nt.restaurant.microservice.outdto.CommonResponse;
import com.nt.restaurant.microservice.outdto.FoodItemOutDTO;

import java.util.List;

public interface FoodItemService {
  CommonResponse addFoodItem(FoodItemInDTO foodItemInDTO);

  List<FoodItemOutDTO> getFoodItemsByCategory(Integer categoryId);

  List<FoodItemOutDTO> getFoodItemsByRestaurant(Integer restaurantId);

  CommonResponse updateFoodItemByFoodItemId(Integer foodItemId, FoodItemInDTO foodItemInDTO);

  byte[] getFoodItemImage(Integer id);

  //FoodItemOutDTO getFoodFoodItemById(Integer foodItemId);
  FoodItem findFoodItemById(Integer id);
}
