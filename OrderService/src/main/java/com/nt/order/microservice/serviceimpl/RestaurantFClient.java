package com.nt.order.microservice.serviceimpl;

import com.nt.order.microservice.dtos.RestaurantOutDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "restaurant-service", url = "http://localhost:300")
public interface RestaurantFClient {

  @GetMapping("/restaurant/getRestaurant/{restaurantId}")
  RestaurantOutDTO getRestaurantById(@PathVariable("restaurantId") Integer id);

//  @GetMapping("/foodItems/{foodItemId}")
//  RestaurantMenuResponse getMenuItemById(@PathVariable("foodItemId") Long foodItemId);
//
//  @GetMapping("/foodItem/{id}")
//  FoodItemOutDto getFoodItemById(@PathVariable("id") Integer id);
}
