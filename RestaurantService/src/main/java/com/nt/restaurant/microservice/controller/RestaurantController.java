package com.nt.restaurant.microservice.controller;

import com.nt.restaurant.microservice.indto.RestaurantInDTO;
import com.nt.restaurant.microservice.outdto.CommonResponse;
import com.nt.restaurant.microservice.outdto.RestaurantOutDTO;
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
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/restaurant")
public class RestaurantController {

  private static final Logger logger = LogManager.getLogger(RestaurantController.class);

  @Autowired
  private RestaurantService restaurantService;

  @PostMapping(value = "/addRestaurant", consumes = "multipart/form-data")
  public ResponseEntity<CommonResponse> addRestaurant(@Valid @ModelAttribute RestaurantInDTO restaurantInDTO) {
    logger.info("Received request to add a restaurant: {}", restaurantInDTO);
    CommonResponse response = restaurantService.addRestaurant(restaurantInDTO);
    logger.info("Successfully added restaurant: {}", response.getMessage());
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @GetMapping("/getRestaurant/{restaurantId}")
  public ResponseEntity<RestaurantOutDTO> getRestaurantById(@PathVariable Integer restaurantId) {
    logger.info("Fetching restaurant details for ID: {}", restaurantId);
    RestaurantOutDTO restaurantOutDTO = restaurantService.getRestaurantById(restaurantId);
    if (restaurantOutDTO != null) {
      logger.info("Successfully retrieved restaurant details for ID: {}", restaurantId);
      return ResponseEntity.ok(restaurantOutDTO);
    } else {
      logger.warn("Restaurant with ID {} not found", restaurantId);
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/restaurants/{userId}")
  public ResponseEntity<List<RestaurantOutDTO>> getRestaurantsByUserId(@PathVariable Integer userId) {
    logger.info("Fetching restaurants for user ID: {}", userId);
    List<RestaurantOutDTO> restaurants = restaurantService.getRestaurantsByUserId(userId);
    logger.info("Successfully retrieved {} restaurants for user ID: {}", restaurants.size(), userId);
    return ResponseEntity.ok(restaurants);
  }

  @GetMapping("/{id}/image")
  public ResponseEntity<byte[]> getRestaurantImage(@PathVariable Integer id) {
    logger.info("Fetching image for restaurant ID: {}", id);
    byte[] imageData = restaurantService.getRestaurantImage(id);
    logger.info("Successfully retrieved image for restaurant ID: {}", id);
    return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageData);
  }

  @GetMapping()
  public ResponseEntity<List<RestaurantOutDTO>> getAllRestaurants() {
    logger.info("Fetching all restaurants");
    List<RestaurantOutDTO> restaurantOutDTOs = restaurantService.getAllRestaurants();
    logger.info("Successfully retrieved {} restaurants", restaurantOutDTOs.size());
    return ResponseEntity.ok(restaurantOutDTOs);
  }
}
