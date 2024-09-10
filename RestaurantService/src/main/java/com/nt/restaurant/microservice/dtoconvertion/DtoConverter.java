package com.nt.restaurant.microservice.dtoconvertion;

import com.nt.restaurant.microservice.entities.Restaurant;
import com.nt.restaurant.microservice.indto.RestaurantInDTO;
import com.nt.restaurant.microservice.outdto.RestaurantOutDTO;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;

public class DtoConverter {

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
  public static RestaurantOutDTO fromEntityToOutDTO(Restaurant restaurant) {
    RestaurantOutDTO restaurantOutDTO = new RestaurantOutDTO();
    restaurantOutDTO.setRestaurantId(restaurant.getRestaurantId());
    restaurantOutDTO.setRestaurantName(restaurant.getRestaurantName());
    restaurantOutDTO.setRestaurantAddress(restaurant.getRestaurantAddress());
    restaurantOutDTO.setContactNumber(restaurant.getContactNumber());
    restaurantOutDTO.setRegistrationDate(restaurant.getRegistrationDate());
    restaurantOutDTO.setDescription(restaurant.getDescription());
    restaurantOutDTO.setOpen(restaurant.isOpen());

    if (restaurant.getRestaurantImage() != null) {
      String base64Image = Base64.getEncoder().encodeToString(restaurant.getRestaurantImage());
      restaurantOutDTO.setRestaurantImage(base64Image);
    }

    return restaurantOutDTO;
  }
}
