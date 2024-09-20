package com.nt.order.microservice.serviceimpl;

import com.nt.order.microservice.dtos.FoodItemOutDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "foodItem-feignClient", url = "http://localhost:300")
public interface FoodItemFClient {

  @GetMapping("/foodItems/{foodItemId}")
  FoodItemOutDTO getFoodItemById(@PathVariable Integer foodItemId);

  @GetMapping("getFoodItems/{restaurantId}")
  public List<FoodItemOutDTO> getFoodItemsByRestaurant(@PathVariable("id") Integer id);

}
