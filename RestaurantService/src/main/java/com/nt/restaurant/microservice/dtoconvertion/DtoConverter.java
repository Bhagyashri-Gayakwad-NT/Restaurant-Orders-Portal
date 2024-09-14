package com.nt.restaurant.microservice.dtoconvertion;

import com.nt.restaurant.microservice.entities.Restaurant;
import com.nt.restaurant.microservice.dto.RestaurantInDTO;
import com.nt.restaurant.microservice.dto.RestaurantOutDTO;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Objects;

/**
 * Utility class to convert between DTOs and entities for the Restaurant entity.
 */
public class DtoConverter {

  /**
   * Converts a {@link RestaurantInDTO} to a {@link Restaurant} entity.
   *
   * @param restaurantInDTO the DTO containing restaurant input data.
   * @return the {@link Restaurant} entity populated with input data.
   */
  public static Restaurant fromInDTOToEntity(RestaurantInDTO restaurantInDTO) {
    Restaurant restaurant = new Restaurant();
    restaurant.setUserId(restaurantInDTO.getUserId());
    restaurant.setRestaurantName(restaurantInDTO.getRestaurantName());
    restaurant.setRestaurantAddress(restaurantInDTO.getRestaurantAddress());
    restaurant.setContactNumber(restaurantInDTO.getContactNumber());
    restaurant.setDescription(restaurantInDTO.getDescription());
    restaurant.setRegistrationDate(LocalDate.now());
    restaurant.setOpen(true);

    try {
      byte[] imageBytes = restaurantInDTO.getRestaurantImage().getBytes();
      restaurant.setRestaurantImage(imageBytes);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return restaurant;
  }

  /**
   * Converts a {@link Restaurant} entity to a {@link RestaurantOutDTO}.
   *
   * @param restaurant the entity representing the restaurant.
   * @return a {@link RestaurantOutDTO} populated with restaurant data.
   */
  public static RestaurantOutDTO fromEntityToOutDTO(Restaurant restaurant) {
    RestaurantOutDTO restaurantOutDTO = new RestaurantOutDTO();
    restaurantOutDTO.setRestaurantId(restaurant.getRestaurantId());
    restaurantOutDTO.setRestaurantName(restaurant.getRestaurantName());
    restaurantOutDTO.setRestaurantAddress(restaurant.getRestaurantAddress());
    restaurantOutDTO.setContactNumber(restaurant.getContactNumber());
    restaurantOutDTO.setRegistrationDate(restaurant.getRegistrationDate());
    restaurantOutDTO.setDescription(restaurant.getDescription());
    restaurantOutDTO.setOpen(restaurant.isOpen());

    if (Objects.nonNull(restaurant.getRestaurantImage())) {
      String base64Image = Base64.getEncoder().encodeToString(restaurant.getRestaurantImage());
      restaurantOutDTO.setRestaurantImage(base64Image);
    }

    return restaurantOutDTO;
  }
}
