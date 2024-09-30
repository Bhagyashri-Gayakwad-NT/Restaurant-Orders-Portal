package com.nt.order.microservice.serviceimpl;

import com.nt.order.microservice.dtos.RestaurantOutDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Feign client interface for communicating with the Restaurant microservice.
 */
@FeignClient(name = "restaurant-service", url = "http://localhost:300")
public interface RestaurantFClient {

  /**
   * Retrieves the restaurant details by its ID.
   *
   * @param id the ID of the restaurant to retrieve
   * @return RestaurantOutDTO containing the restaurant's details
   */
  @GetMapping("/restaurant/getRestaurant/{restaurantId}")
  RestaurantOutDTO getRestaurantById(@PathVariable("restaurantId") Integer id);

}
