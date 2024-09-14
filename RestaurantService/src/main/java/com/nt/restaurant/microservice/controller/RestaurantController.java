package com.nt.restaurant.microservice.controller;

import com.nt.restaurant.microservice.dto.RestaurantInDTO;
import com.nt.restaurant.microservice.dto.CommonResponse;
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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

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
  private static final Logger logger = LogManager.getLogger(RestaurantController.class);

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
   * @return a response entity with a success message if the restaurant is added successfully.
   */
  @PostMapping(value = "/addRestaurant", consumes = "multipart/form-data")
  public ResponseEntity<CommonResponse> addRestaurant(@Valid @ModelAttribute RestaurantInDTO restaurantInDTO,
                                                      @RequestParam("restaurantImage")
                                                      MultipartFile image) {
    logger.info("Received request to add a restaurant: {}", restaurantInDTO);
    CommonResponse response = restaurantService.addRestaurant(restaurantInDTO, image);
    logger.info("Successfully added restaurant: {}", response.getMessage());
    return new ResponseEntity<CommonResponse>(response, HttpStatus.CREATED);
  }

  /**
   * Fetches restaurant details by restaurant ID.
   *
   * @param restaurantId the ID of the restaurant to be fetched.
   * @return a response entity containing restaurant details or a 404 status if the restaurant is not found.
   */
  @GetMapping("/getRestaurant/{restaurantId}")
  public ResponseEntity<RestaurantOutDTO> getRestaurantById(@PathVariable Integer restaurantId) {
    logger.info("Fetching restaurant details for ID: {}", restaurantId);
    RestaurantOutDTO restaurantOutDTO = restaurantService.getRestaurantById(restaurantId);
    logger.info("Successfully retrieved restaurant details for ID: {}", restaurantId);
    return ResponseEntity.ok(restaurantOutDTO);
  }

  /**
   * Fetches all restaurants for a given user ID.
   *
   * @param userId the ID of the user whose restaurants are to be fetched.
   * @return a response entity containing a list of restaurants for the specified user.
   */
  @GetMapping("/restaurants/{userId}")
  public ResponseEntity<List<RestaurantOutDTO>> getRestaurantsByUserId(@PathVariable Integer userId) {
    logger.info("Fetching restaurants for user ID: {}", userId);
    List<RestaurantOutDTO> restaurants = restaurantService.getRestaurantsByUserId(userId);
    logger.info("Successfully retrieved {} restaurants for user ID: {}", restaurants.size(), userId);
    return ResponseEntity.ok(restaurants);
  }

  /**
   * Fetches the image of a restaurant by its ID.
   *
   * @param id the ID of the restaurant whose image is to be fetched.
   * @return a response entity containing the restaurant image in JPEG format.
   */
  @GetMapping("/{id}/image")
  public ResponseEntity<byte[]> getRestaurantImage(@PathVariable Integer id) {
    logger.info("Fetching image for restaurant ID: {}", id);
    byte[] imageData = restaurantService.getRestaurantImage(id);
    logger.info("Successfully retrieved image for restaurant ID: {}", id);
    return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageData);
  }

  /**
   * Fetches all available restaurants.
   *
   * @return a response entity containing a list of all restaurants.
   */
  @GetMapping()
  public ResponseEntity<List<RestaurantOutDTO>> getAllRestaurants() {
    logger.info("Fetching all restaurants");
    List<RestaurantOutDTO> restaurantOutDTOs = restaurantService.getAllRestaurants();
    logger.info("Successfully retrieved {} restaurants", restaurantOutDTOs.size());
    return ResponseEntity.ok(restaurantOutDTOs);
  }

  /**
   * Data Transfer Object (DTO) for input validation of the Food Category entity.
   * This class is used to capture and validate the input data required to create
   * or update a food category for a restaurant.
   */
  public static class FoodCategoryInDTO {

    /**
     * Represents the ID of the restaurant that owns the food category.
     * This field cannot be null.
     */
    @NotNull(message = "Restaurant ID cannot be null")
    private Integer restaurantId;

    /**
     * Represents the name of the food category.
     * The name cannot be blank, should not exceed 100 characters, and must contain only alphabets.
     */
    @NotBlank(message = "Food category name cannot be blank")
    @Size(max = 100, message = "Food category name cannot exceed 100 characters")
    @Pattern(regexp = "^[A-Za-z]+(?:\\s[A-Za-z]+)*$", message = "Category name must contain only alphabets")
    private String foodCategoryName;

    /**
     * Default constructor for the {@code FoodCategoryInDTO} class.
     */
    public FoodCategoryInDTO() {
    }

    /**
     * Parameterized constructor to initialize {@code FoodCategoryInDTO} with restaurant ID and food category name.
     *
     * @param restaurantId     the ID of the restaurant
     * @param foodCategoryName the name of the food category
     */
    public FoodCategoryInDTO(Integer restaurantId, String foodCategoryName) {
      this.restaurantId = restaurantId;
      this.foodCategoryName = foodCategoryName;
    }

    /**
     * Gets the ID of the restaurant.
     *
     * @return the restaurant ID
     */
    public Integer getRestaurantId() {
      return restaurantId;
    }

    /**
     * Sets the ID of the restaurant.
     *
     * @param restaurantId the new restaurant ID
     */
    public void setRestaurantId(Integer restaurantId) {
      this.restaurantId = restaurantId;
    }

    /**
     * Gets the name of the food category.
     *
     * @return the food category name
     */
    public String getFoodCategoryName() {
      return foodCategoryName;
    }

    /**
     * Sets the name of the food category.
     *
     * @param foodCategoryName the new food category name
     */
    public void setFoodCategoryName(String foodCategoryName) {
      this.foodCategoryName = foodCategoryName;
    }

    /**
     * Compares this object with the specified object for equality.
     *
     * @param o the object to compare with
     * @return {@code true} if both objects are equal, otherwise {@code false}
     */
    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      FoodCategoryInDTO that = (FoodCategoryInDTO) o;
      return Objects.equals(restaurantId, that.restaurantId) && Objects.equals(foodCategoryName, that.foodCategoryName);
    }

    /**
     * Returns the hash code value for this object.
     *
     * @return the hash code value
     */
    @Override
    public int hashCode() {
      return Objects.hash(restaurantId, foodCategoryName);
    }

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object
     */
    @Override
    public String toString() {
      return "FoodCategoryInDTO{" +
        "restaurantId=" + restaurantId +
        ", foodCategoryName='" + foodCategoryName + '\'' +
        '}';
    }
  }
}

