package com.nt.restaurant.microservice.controller;

import com.nt.restaurant.microservice.indto.FoodItemInDTO;
import com.nt.restaurant.microservice.indto.FoodItemUpdateInDTO;
import com.nt.restaurant.microservice.outdto.CommonResponse;
import com.nt.restaurant.microservice.outdto.FoodItemOutDTO;
import com.nt.restaurant.microservice.service.FoodItemService;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller for managing food item-related operations such as adding, fetching, and updating food items.
 */
@RestController
@CrossOrigin
@RequestMapping("/foodItems")
public class FoodItemController {

  /**
   * Logger for this class, used to log important information, request details, and any issues encountered.
   * <p>
   * The {@link Logger} instance tracks events such as adding, fetching, and updating food items.
   * It helps with application flow monitoring, debugging, and auditing purposes.
   */
  private static final Logger logger = LogManager.getLogger(FoodItemController.class);

  /**
   * Service responsible for handling the business logic related to food items.
   * This service is used for operations such as adding, fetching, and updating food items.
   * <p>
   * The {@code foodItemService} is injected by Spring's dependency injection mechanism.
   * It provides access to methods for interacting with food items, including adding new food items
   * along with associated metadata such as images.
   */
  @Autowired
  private FoodItemService foodItemService;

  /**
   * Adds a new food item based on the provided {@link FoodItemInDTO} data.
   *
   * @param foodItemInDTO the food item information to add.
   * @return a response entity with a success message if the food item is added successfully.
   */
  @PostMapping("/addFoodItem")
  public ResponseEntity<CommonResponse> addFoodItem(@Valid @ModelAttribute FoodItemInDTO foodItemInDTO, @RequestParam("foodItemImage")
    MultipartFile image) {
    logger.info("Received request to add food item: {}", foodItemInDTO);
    CommonResponse response = foodItemService.addFoodItem(foodItemInDTO, image);
    logger.info("Successfully added food item with ID: {}", response.getMessage());
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  /**
   * Fetches all food items for a given category ID.
   *
   * @param categoryId the ID of the food category whose items are to be fetched.
   * @return a response entity containing a list of food items for the specified category.
   */
  @GetMapping("/getFoodItem/{categoryId}")
  public ResponseEntity<List<FoodItemOutDTO>> getFoodItemsByCategory(@PathVariable Integer categoryId) {
    logger.info("Fetching food items for category ID: {}", categoryId);
    List<FoodItemOutDTO> foodItems = foodItemService.getFoodItemsByCategory(categoryId);
    logger.info("Successfully retrieved {} food items for category ID: {}", foodItems.size(), categoryId);
    return ResponseEntity.ok(foodItems);
  }

  /**
   * Fetches all food items for a given restaurant ID.
   *
   * @param restaurantId the ID of the restaurant whose food items are to be fetched.
   * @return a response entity containing a list of food items for the specified restaurant.
   */
  @GetMapping("getFoodItems/{restaurantId}")
  public ResponseEntity<List<FoodItemOutDTO>> getFoodItemsByRestaurant(@PathVariable Integer restaurantId) {
    logger.info("Fetching food items for restaurant ID: {}", restaurantId);
    List<FoodItemOutDTO> foodItems = foodItemService.getFoodItemsByRestaurant(restaurantId);
    logger.info("Successfully retrieved {} food items for restaurant ID: {}", foodItems.size(), restaurantId);
    return ResponseEntity.ok(foodItems);
  }

  /**
   * Updates a food item by its food item ID.
   *
   * @param foodItemId          the ID of the food item to be updated.
   * @param foodItemUpdateInDTO the updated food item information.
   * @return a response entity with a success message if the food item is updated successfully.
   */
  @PutMapping("/updateFoodItem/{foodItemId}")
  public ResponseEntity<CommonResponse> updateFoodItemByFoodItemId(
    @PathVariable Integer foodItemId,
    @Valid @ModelAttribute FoodItemUpdateInDTO foodItemUpdateInDTO) {

    logger.info("Received request to update food item with ID: {} with details: {}", foodItemId, foodItemUpdateInDTO);
    CommonResponse updatedFoodItem = foodItemService.updateFoodItemByFoodItemId(foodItemId, foodItemUpdateInDTO);
    logger.info("Successfully updated food item with ID: {}", foodItemId);
    return new ResponseEntity<CommonResponse>(updatedFoodItem, HttpStatus.OK);
  }

  /**
   * Fetches the image of a food item by its ID.
   *
   * @param id the ID of the food item whose image is to be fetched.
   * @return a response entity containing the food item image in JPEG format.
   */
  @GetMapping("/{id}/image")
  public ResponseEntity<byte[]> getFoodItemImage(@PathVariable Integer id) {
    logger.info("Fetching image for food item with ID: {}", id);
    byte[] imageData = foodItemService.getFoodItemImage(id);
    logger.info("Successfully retrieved image for food item with ID: {}", id);
    return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageData);
  }
}

