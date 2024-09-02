package com.nt.restaurant.microservice.serviceimpl;

import com.nt.restaurant.microservice.dtoconvertion.DtoConverter;
import com.nt.restaurant.microservice.entities.Restaurant;
import com.nt.restaurant.microservice.indto.RestaurantInDTO;
import com.nt.restaurant.microservice.outdto.RestaurantOutDTO;
import com.nt.restaurant.microservice.repository.RestaurantRepository;
import com.nt.restaurant.microservice.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {
  @Autowired
  private RestaurantRepository restaurantRepository;

  @Override
  public RestaurantOutDTO addRestaurant(RestaurantInDTO restaurantInDTO) {

    Restaurant restaurant = DtoConverter.fromInDTOToEntity(restaurantInDTO);
    Restaurant savedRestaurant = restaurantRepository.save(restaurant);
    RestaurantOutDTO restaurantOutDTO = DtoConverter.fromEntityToOutDTO(savedRestaurant);
    return restaurantOutDTO;
  }

  @Override
  public RestaurantOutDTO getRestaurantById(Integer restaurantId) {
    Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurantId);
    if (restaurantOptional.isPresent()) {
      Restaurant restaurant = restaurantOptional.get();
      return DtoConverter.fromEntityToOutDTO(restaurant);
    }
    return null;
  }
}
