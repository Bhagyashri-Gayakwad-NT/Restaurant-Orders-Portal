package com.nt.restaurant.microservice.service;

import com.nt.restaurant.microservice.indto.FoodCategoryInDTO;
import com.nt.restaurant.microservice.outdto.CommonResponse;
import com.nt.restaurant.microservice.outdto.FoodCategoryOutDTO;

import java.util.List;

public interface FoodCategoryservice {
  CommonResponse addFoodCategory(FoodCategoryInDTO foodCategoryInDTO);
  List<FoodCategoryOutDTO> getFoodCategoryByRestaurantId(Integer restaurantId);
  CommonResponse updateFoodCategory(Integer foodCategoryId, FoodCategoryInDTO foodCategoryInDTO);
}
