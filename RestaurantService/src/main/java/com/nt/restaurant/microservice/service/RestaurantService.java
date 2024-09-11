package com.nt.restaurant.microservice.service;

import com.nt.restaurant.microservice.entities.Restaurant;
import com.nt.restaurant.microservice.indto.RestaurantInDTO;
import com.nt.restaurant.microservice.outdto.CommonResponse;
import com.nt.restaurant.microservice.outdto.RestaurantOutDTO;

import java.util.List;

public interface RestaurantService {
  CommonResponse addRestaurant(RestaurantInDTO restaurantInDTO);

  RestaurantOutDTO getRestaurantById(Integer restaurantId);

  List<RestaurantOutDTO> getRestaurantsByUserId(Integer userId);

  byte[] getRestaurantImage(Integer id);

  Restaurant findRestaurantById(Integer id);

  List<RestaurantOutDTO> getAllRestaurants();
//  List<RestaurantOutDTO> getRestaurantsById(Integer restaurantId);
//  RestaurantOutDTO addRestaurant(RestaurantInDTO restaurantInDTO);
}
