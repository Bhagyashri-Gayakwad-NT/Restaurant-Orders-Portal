package com.nt.order.microservice.serviceimpl;

import com.nt.order.microservice.dtos.FoodItemOutDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Feign client interface for interacting with the Food Item microservice.
 */
@FeignClient(name = "foodItem-feignClient", url = "http://localhost:300")
public interface FoodItemFClient {

  /**
   * Retrieves the food item details by its ID.
   *
   * @param foodItemId the ID of the food item to retrieve
   * @return FoodItemOutDTO containing the food item's details
   */
  @GetMapping("/foodItems/{foodItemId}")
  FoodItemOutDTO getFoodItemById(@PathVariable("foodItemId") Integer foodItemId);

  /**
   * Retrieves all food items associated with a specific restaurant.
   *
   * @param restaurantId the ID of the restaurant whose food items are to be retrieved
   * @return a list of FoodItemOutDTO containing the food items of the specified restaurant
   */
  @GetMapping("/foodItems/getFoodItems/{restaurantId}")
  List<FoodItemOutDTO> getFoodItemsByRestaurant(@PathVariable("restaurantId") Integer restaurantId);
}

