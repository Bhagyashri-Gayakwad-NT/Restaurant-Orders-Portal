package com.nt.restaurant.microservice.serviceimpl;

import com.nt.restaurant.microservice.dtoconvertion.DtoConverter;
import com.nt.restaurant.microservice.entities.Restaurant;
import com.nt.restaurant.microservice.exception.NotFoundException;
import com.nt.restaurant.microservice.exception.UserNotFoundException;
import com.nt.restaurant.microservice.exception.UserNotRestaurantOwnerException;
import com.nt.restaurant.microservice.indto.RestaurantInDTO;
import com.nt.restaurant.microservice.outdto.CommonResponse;
import com.nt.restaurant.microservice.outdto.RestaurantOutDTO;
import com.nt.restaurant.microservice.outdto.UserOutDTO;
import com.nt.restaurant.microservice.repository.RestaurantRepository;
import com.nt.restaurant.microservice.service.RestaurantService;
import com.nt.restaurant.microservice.util.Constants;
import com.nt.restaurant.microservice.util.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {

  private static final Logger logger = LogManager.getLogger(RestaurantServiceImpl.class);

  @Autowired
  private RestaurantRepository restaurantRepository;

  @Autowired
  private UserFClient userFClient;

  @Override
  public CommonResponse addRestaurant(RestaurantInDTO restaurantInDTO) {
    logger.info("Attempting to add restaurant with details: {}", restaurantInDTO);
    UserOutDTO userOutDto;
    try {
      userOutDto = userFClient.getUserProfile(restaurantInDTO.getUserId());
    } catch (Exception e) {
      throw new UserNotFoundException();
    }
    if (userOutDto.getRole().equals(Role.USER.name())) {
      throw new UserNotRestaurantOwnerException(Constants.USER_NOT_RESTAURANT_OWNER);
    }

    Restaurant restaurant = DtoConverter.fromInDTOToEntity(restaurantInDTO);
    Restaurant savedRestaurant = restaurantRepository.save(restaurant);
    DtoConverter.fromEntityToOutDTO(savedRestaurant);

    logger.info("Successfully added restaurant with ID: {}", savedRestaurant.getRestaurantId());
    return new CommonResponse(Constants.RESTAURANT_ADDED_SUCCESS);

  }

  @Override
  public RestaurantOutDTO getRestaurantById(Integer restaurantId) {
    logger.info("Fetching restaurant with ID: {}", restaurantId);

    Optional<Restaurant> restaurantOptional = restaurantRepository.findById(restaurantId);
    if (restaurantOptional.isPresent()) {
      Restaurant restaurant = restaurantOptional.get();
      RestaurantOutDTO restaurantOutDTO = DtoConverter.fromEntityToOutDTO(restaurant);

      logger.info("Successfully retrieved restaurant with ID: {}", restaurantId);
      return restaurantOutDTO;
    } else {
      logger.error("Restaurant not found with ID: {}", restaurantId);
      throw new NotFoundException("Restaurant not found with ID: " + restaurantId);
    }
  }

  @Override
  public List<RestaurantOutDTO> getRestaurantsByUserId(Integer userId) {
    logger.info("Fetching restaurants for user ID: {}", userId);

    List<Restaurant> restaurants = restaurantRepository.findByUserId(userId);
    List<RestaurantOutDTO> restaurantOutDTOList = new ArrayList<>();

    for (Restaurant restaurant : restaurants) {
      RestaurantOutDTO restaurantOutDTO = DtoConverter.fromEntityToOutDTO(restaurant);
      restaurantOutDTOList.add(restaurantOutDTO);
    }

    logger.info("Successfully retrieved {} restaurants for user ID: {}", restaurantOutDTOList.size(), userId);
    return restaurantOutDTOList;
  }

  @Override
  public byte[] getRestaurantImage(Integer id) {
    logger.info("Fetching image for restaurant with ID: {}", id);

    Restaurant restaurant = findRestaurantById(id);
    return restaurant.getRestaurantImage();
  }

  public Restaurant findRestaurantById(Integer id) {
    logger.info("Finding restaurant with ID: {}", id);

    return restaurantRepository.findById(id)
      .orElseThrow(() -> {
        logger.error("Restaurant not found with ID: {}", id);
        return new NotFoundException("Restaurant not found with ID: " + id);
      });
  }

  @Override
  public List<RestaurantOutDTO> getAllRestaurants() {
    logger.info("Fetching all restaurants");

    List<Restaurant> restaurants = restaurantRepository.findAll();
    List<RestaurantOutDTO> restaurantOutDTOs = new ArrayList<>();

    for (Restaurant restaurant : restaurants) {
      RestaurantOutDTO restaurantOutDTO = DtoConverter.fromEntityToOutDTO(restaurant);
      restaurantOutDTOs.add(restaurantOutDTO);
    }

    logger.info("Successfully retrieved {} restaurants", restaurantOutDTOs.size());
    return restaurantOutDTOs;
  }
}
