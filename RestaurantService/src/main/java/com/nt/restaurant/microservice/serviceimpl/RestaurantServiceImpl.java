package com.nt.restaurant.microservice.serviceimpl;

import com.nt.restaurant.microservice.dtoconvertion.DtoConverter;
import com.nt.restaurant.microservice.entities.Restaurant;
import com.nt.restaurant.microservice.exception.InvalidImageFileException;
import com.nt.restaurant.microservice.exception.NotFoundException;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Implementation of the RestaurantService interface that provides operations for managing restaurants.
 */
@Service
public class RestaurantServiceImpl implements RestaurantService {

  /**
   * Logger for the RestaurantServiceImpl class.
   */
  private static final Logger logger = LogManager.getLogger(RestaurantServiceImpl.class);

  /**
   * Autowired RestaurantRepository to perform CRUD operations on Restaurant entities.
   */
  @Autowired
  private RestaurantRepository restaurantRepository;

  /**
   * Autowired UserFClient to communicate with the user microservice.
   */
  @Autowired
  private UserFClient userFClient;

  /**
   * Adds a new restaurant to the system.
   *
   * @param restaurantInDTO The DTO containing the details of the restaurant to be added.
   * @param image           The image of the restaurant to be added.
   * @return A response indicating the outcome of the operation.
   * @throws NotFoundException If the user is not found or is not a restaurant owner.
   */
  @Override
  public CommonResponse addRestaurant(RestaurantInDTO restaurantInDTO, MultipartFile image) {
    logger.info("Attempting to add restaurant with details: {}", restaurantInDTO);
    UserOutDTO userOutDto;

    try {
      logger.debug("Fetching user profile for userId: {}", restaurantInDTO.getUserId());
      userOutDto = userFClient.getUserProfile(restaurantInDTO.getUserId());
      logger.debug("User profile fetched successfully: {}", userOutDto);
    } catch (Exception e) {
      logger.error("User with ID {} not found: {}", restaurantInDTO.getUserId(), e.getMessage());
      throw new NotFoundException(Constants.USER_NOT_FOUND);
    }

    if (userOutDto.getRole().equals(Role.USER.name())) {
      logger.warn("User with ID {} is not a restaurant owner", restaurantInDTO.getUserId());
      throw new NotFoundException(Constants.USER_NOT_RESTAURANT_OWNER);
    }

    logger.debug("Converting RestaurantInDTO to Restaurant entity");
    Restaurant restaurant = DtoConverter.fromInDTOToEntity(restaurantInDTO);

    try {
      if (Objects.nonNull(image) && !image.isEmpty()) {
        logger.debug("Processing image for restaurant: {}", restaurantInDTO.getRestaurantName());
        String contentType = image.getContentType();

        if (Objects.isNull(contentType) || !(contentType.equals("image/jpeg") || contentType.equals("image/png"))) {
          logger.error("Invalid image type: {}. Only JPG and PNG are allowed.", contentType);
          throw new InvalidImageFileException(Constants.INVALID_FILE_TYPE);
        }

        restaurant.setRestaurantImage(image.getBytes());
        logger.debug("Image processed and added successfully for restaurant: {}", restaurantInDTO.getRestaurantName());
      } else {
        logger.warn("No image provided for restaurant: {}", restaurantInDTO.getRestaurantName());
      }
    } catch (InvalidImageFileException e) {
      logger.error("Invalid image type provided: {}", e.getMessage());
      throw e;
    } catch (Exception e) {
      logger.error("Error occurred while processing image file for restaurant: {}", e.getMessage());
      e.printStackTrace();
      throw new RuntimeException("Image processing failed");
    }

    logger.debug("Saving restaurant entity to the database");
    Restaurant savedRestaurant = restaurantRepository.save(restaurant);

    logger.info("Successfully added restaurant with ID: {}", savedRestaurant.getRestaurantId());
    DtoConverter.fromEntityToOutDTO(savedRestaurant);

    logger.debug("Returning success response after adding restaurant");
    return new CommonResponse(Constants.RESTAURANT_ADDED_SUCCESS);
  }

  /**
   * Retrieves a restaurant by its ID.
   *
   * @param restaurantId The ID of the restaurant to be retrieved.
   * @return The DTO containing the restaurant details.
   * @throws NotFoundException If the restaurant is not found.
   */
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
      throw new NotFoundException(Constants.RESTAURANT_NOT_FOUND);
    }
  }

  /**
   * Retrieves a list of restaurants for a specific user.
   *
   * @param userId The ID of the user whose restaurants are to be retrieved.
   * @return A list of DTOs containing the details of the user's restaurants.
   */
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

  /**
   * Retrieves the image of a restaurant by its ID.
   *
   * @param id The ID of the restaurant whose image is to be retrieved.
   * @return A byte array representing the image of the restaurant.
   */
  @Override
  public byte[] getRestaurantImage(Integer id) {
    logger.info("Fetching image for restaurant with ID: {}", id);

    Restaurant restaurant = findRestaurantById(id);
    return restaurant.getRestaurantImage();
  }

  /**
   * Finds a restaurant by its ID.
   *
   * @param id The ID of the restaurant to be found.
   * @return The Restaurant entity corresponding to the given ID.
   * @throws NotFoundException If the restaurant is not found.
   */
  public Restaurant findRestaurantById(Integer id) {
    logger.info("Finding restaurant with ID: {}", id);

    return restaurantRepository.findById(id)
      .orElseThrow(() -> {
        logger.error("Restaurant not found with ID: {}", id);
        return new NotFoundException(Constants.RESTAURANT_NOT_FOUND);
      });
  }

  /**
   * Retrieves a list of all restaurants in the system.
   *
   * @return A list of DTOs containing the details of all restaurants.
   */
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
