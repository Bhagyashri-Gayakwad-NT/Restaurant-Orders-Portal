package com.nt.restaurant.microservice.service;

import com.nt.restaurant.microservice.dto.CommonResponse;
import com.nt.restaurant.microservice.dto.RestaurantInDTO;
import com.nt.restaurant.microservice.dto.RestaurantOutDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Service interface for managing restaurants.
 */
public interface RestaurantService {

  /**
   * Adds a new restaurant.
   *
   * @param restaurantInDTO The details of the restaurant to be added.
   * @param image The image associated with the restaurant.
   * @return A {@link CommonResponse} indicating the result of the operation.
   */
  CommonResponse addRestaurant(RestaurantInDTO restaurantInDTO, MultipartFile image);

  /**
   * Retrieves a restaurant by its ID.
   *
   * @param restaurantId The ID of the restaurant to retrieve.
   * @return A {@link RestaurantOutDTO} containing the details of the restaurant.
   */
  RestaurantOutDTO getRestaurantById(Integer restaurantId);

  /**
   * Retrieves all restaurants associated with a specific user.
   *
   * @param userId The ID of the user whose restaurants are to be retrieved.
   * @return A list of {@link RestaurantOutDTO} objects representing the restaurants.
   */
  List<RestaurantOutDTO> getRestaurantsByUserId(Integer userId);

  /**
   * Retrieves the image of a restaurant by its ID.
   *
   * @param id The ID of the restaurant whose image is to be retrieved.
   * @return A byte array representing the image of the restaurant.
   */
  byte[] getRestaurantImage(Integer id);

  /**
   * Retrieves all restaurants.
   *
   * @return A list of {@link RestaurantOutDTO} objects representing all restaurants.
   */
  List<RestaurantOutDTO> getAllRestaurants();

}

