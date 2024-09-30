package com.nt.restaurant.microservice.controller;

import com.nt.restaurant.microservice.dto.CommonResponse;
import com.nt.restaurant.microservice.dto.RestaurantInDTO;
import com.nt.restaurant.microservice.dto.RestaurantOutDTO;
import com.nt.restaurant.microservice.service.RestaurantService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;


/**
 * Controller for managing restaurant-related operations such as adding, fetching, and retrieving images of restaurants.
 */
@RestController
@CrossOrigin
@RequestMapping("/restaurant")
public class RestaurantController {

  /**
   * Logger for this class, used to log important information, request details, and any issues encountered.
   * <p>
   * The {@link Logger} instance tracks events such as adding, fetching, and retrieving restaurants or their images.
   * It helps with application flow monitoring, debugging, and auditing purposes.
   */
  private static final Logger LOGGER = LogManager.getLogger(RestaurantController.class);

  /**
   * Service responsible for handling the business logic related to restaurant operations.
   * This service is used for operations such as adding, fetching, and managing restaurant details.
   * <p>
   * The {@code restaurantService} is injected by Spring's dependency injection mechanism.
   * It provides access to methods that interact with restaurants, including adding new restaurants
   * and handling related data such as restaurant images.
   */
  @Autowired
  private RestaurantService restaurantService;


  /**
   * Adds a new restaurant based on the provided {@link RestaurantInDTO} data.
   *
   * @param restaurantInDTO the restaurant information to add.
   * @param image the image file of the restaurant.
   * @return a response entity with a success message if the restaurant is added successfully.
   */
  @PostMapping(value = "/addRestaurant", consumes = "multipart/form-data")
  public ResponseEntity<CommonResponse> addRestaurant(@Valid @ModelAttribute final RestaurantInDTO restaurantInDTO,
                                                      @RequestParam("restaurantImage") final MultipartFile image) {
    LOGGER.info("Received request to add a restaurant: {}", restaurantInDTO);
    CommonResponse response = restaurantService.addRestaurant(restaurantInDTO, image);
    LOGGER.info("Successfully added restaurant: {}", response.getMessage());
    return new ResponseEntity<CommonResponse>(response, HttpStatus.CREATED);
  }

  /**
   * Fetches restaurant details by restaurant ID.
   *
   * @param restaurantId the ID of the restaurant to be fetched.
   * @return a response entity containing restaurant details or a 404 status if the restaurant is not found.
   */
  @GetMapping("/getRestaurant/{restaurantId}")
  public ResponseEntity<RestaurantOutDTO> getRestaurantById(@PathVariable final Integer restaurantId) {
    LOGGER.info("Fetching restaurant details for ID: {}", restaurantId);
    RestaurantOutDTO restaurantOutDTO = restaurantService.getRestaurantById(restaurantId);
    LOGGER.info("Successfully retrieved restaurant details for ID: {}", restaurantId);
    return ResponseEntity.ok(restaurantOutDTO);
  }

  /**
   * Fetches all restaurants for a given user ID.
   *
   * @param userId the ID of the user whose restaurants are to be fetched.
   * @return a response entity containing a list of restaurants for the specified user.
   */
  @GetMapping("/restaurants/{userId}")
  public ResponseEntity<List<RestaurantOutDTO>> getRestaurantsByUserId(@PathVariable final Integer userId) {
    LOGGER.info("Fetching restaurants for user ID: {}", userId);
    List<RestaurantOutDTO> restaurants = restaurantService.getRestaurantsByUserId(userId);
    LOGGER.info("Successfully retrieved {} restaurants for user ID: {}", restaurants.size(), userId);
    return ResponseEntity.ok(restaurants);
  }

  /**
   * Fetches the image of a restaurant by its ID.
   *
   * @param id the ID of the restaurant whose image is to be fetched.
   * @return a response entity containing the restaurant image in JPEG format.
   */
  @GetMapping("/{id}/image")
  public ResponseEntity<byte[]> getRestaurantImage(@PathVariable final Integer id) {
    LOGGER.info("Fetching image for restaurant ID: {}", id);
    byte[] imageData = restaurantService.getRestaurantImage(id);
    LOGGER.info("Successfully retrieved image for restaurant ID: {}", id);
    return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageData);
  }

  /**
   * Fetches all available restaurants.
   *
   * @return a response entity containing a list of all restaurants.
   */
  @GetMapping()
  public ResponseEntity<List<RestaurantOutDTO>> getAllRestaurants() {
    LOGGER.info("Fetching all restaurants");
    List<RestaurantOutDTO> restaurantOutDTOs = restaurantService.getAllRestaurants();
    LOGGER.info("Successfully retrieved {} restaurants", restaurantOutDTOs.size());
    return ResponseEntity.ok(restaurantOutDTOs);
  }

}
