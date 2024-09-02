package com.nt.restaurant.microservice.service;

import com.nt.restaurant.microservice.indto.RestaurantInDTO;
import com.nt.restaurant.microservice.outdto.RestaurantOutDTO;

import java.util.List;

public interface RestaurantService {
 RestaurantOutDTO addRestaurant(RestaurantInDTO restaurantInDTO);
 RestaurantOutDTO getRestaurantById(Integer restaurantId);
//  List<RestaurantOutDTO> getRestaurantsById(Integer restaurantId);
//  RestaurantOutDTO addRestaurant(RestaurantInDTO restaurantInDTO);
}
