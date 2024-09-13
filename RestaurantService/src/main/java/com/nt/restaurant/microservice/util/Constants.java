package com.nt.restaurant.microservice.util;

/**
 * A utility class containing constant values used across the restaurant microservice application.
 * These constants include various success and error messages used for application responses.
 */
public class Constants {

  /**
   * Success message when a restaurant is added successfully.
   */
  public static final String RESTAURANT_ADDED_SUCCESS = "Restaurant added successfully.";

  /**
   * Error message when a user is not found.
   */
  public static final String USER_NOT_FOUND = "User not found.";

  /**
   * Error message when a user is not a restaurant owner.
   */
  public static final String USER_NOT_RESTAURANT_OWNER = "User is not a Restaurant Owner.";

  /**
   * Success message when a food item is added successfully.
   */
  public static final String FOOD_ITEM_ADDED_SUCCESS = "FoodItem added successfully.";

  /**
   * Success message when a food item is updated successfully.
   */
  public static final String FOOD_ITEM_UPDATED_SUCCESS = "FoodItem updated successfully.";

  /**
   * Success message when a food category is added successfully.
   */
  public static final String FOOD_CATEGORY_ADDED_SUCCESS = "FoodCategory added successfully.";

  /**
   * Success message when a food category is updated successfully.
   */
  public static final String FOOD_CATEGORY_UPDATED_SUCCESS = "FoodCategory updated successfully.";

  /**
   * Error message when a restaurant is not found.
   */
  public static final String RESTAURANT_NOT_FOUND = "Restaurant not found";

  /**
   * Error message when a food category is not found.
   */
  public static final String FOOD_CATEGORY_NOT_FOUND = "Food category not found";

  /**
   * Error message when a food item already exists in a restaurant.
   */
  public static final String FOOD_ITEM_ALREADY_PRESENT = "Food Item is already present in this restaurant";

  /**
   * Error message when no food items are present.
   */
  public static final String NO_FOOD_ITEM_PRESENT = "Food Item is not present";

  /**
   * Error message when there is an error processing a food item image.
   */
  public static final String ERROR_PROCESSING_FOOD_ITEM_IMAGE = "Error processing food item image";

  /**
   * Error message when a food category already exists for a restaurant.
   */
  public static final String FOOD_CATEGORY_ALREADY_EXIST = "Food category already exists for this restaurant";

  /**
   * Error message when the uploaded file type is invalid (only JPEG and PNG are allowed).
   */
  public static final String INVALID_FILE_TYPE = "Invalid file type. Only JPEG and PNG images are allowed.";

  /**
   * Error message when the uploaded file exceeds the maximum size limit of 5 MB.
   */
  public static final String INVALID_FILE_SIZE = "File size exceeds the maximum limit of 5 MB.";

}
