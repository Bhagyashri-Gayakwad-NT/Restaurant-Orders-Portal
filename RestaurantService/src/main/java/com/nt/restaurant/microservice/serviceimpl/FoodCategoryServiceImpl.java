package com.nt.restaurant.microservice.serviceimpl;


import com.nt.restaurant.microservice.dto.FoodCategoryInDTO;
import com.nt.restaurant.microservice.dtoconvertion.FoodCategoryDtoConverter;
import com.nt.restaurant.microservice.entities.FoodCategory;
import com.nt.restaurant.microservice.entities.Restaurant;
import com.nt.restaurant.microservice.exception.AlreadyExistException;
import com.nt.restaurant.microservice.exception.NotFoundException;
import com.nt.restaurant.microservice.dto.CommonResponse;
import com.nt.restaurant.microservice.dto.FoodCategoryOutDTO;
import com.nt.restaurant.microservice.repository.FoodCategoryRepository;
import com.nt.restaurant.microservice.repository.RestaurantRepository;
import com.nt.restaurant.microservice.service.FoodCategoryService;
import com.nt.restaurant.microservice.util.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of {@link FoodCategoryService} for managing food categories.
 */
@Service
public class FoodCategoryServiceImpl implements FoodCategoryService {

  /**
   * Logger instance for logging information and errors.
   */
  private static final Logger logger = LogManager.getLogger(FoodCategoryServiceImpl.class);

  /**
   * Injects the {@link FoodCategoryRepository} to interact with the food category data in the database.
   */
  @Autowired
  private FoodCategoryRepository foodCategoryRepository;

  /**
   * Injects the {@link RestaurantRepository} to interact with the restaurant data in the database.
   */
  @Autowired
  private RestaurantRepository restaurantRepository;

  /**
   * Adds a new food category.
   *
   * @param foodCategoryInDTO The details of the food category to be added.
   * @return A {@link CommonResponse} indicating the result of the operation.
   * @throws NotFoundException     If the associated restaurant is not found.
   * @throws AlreadyExistException If a food category with the same name already exists in the restaurant.
   */
  /**
   * Adds a new food category.
   *
   * @param foodCategoryInDTO The details of the food category to be added.
   * @return A {@link CommonResponse} indicating the result of the operation.
   * @throws NotFoundException     If the associated restaurant is not found.
   * @throws AlreadyExistException If a food category with the same name already exists in the restaurant.
   */
  @Override
  public CommonResponse addFoodCategory(FoodCategoryInDTO foodCategoryInDTO) {
    logger.info("Adding food category: {}", foodCategoryInDTO);

    Optional<Restaurant> restaurant = restaurantRepository.findById(foodCategoryInDTO.getRestaurantId());
    if (!restaurant.isPresent()) {
      logger.error("Restaurant with ID {} not found", foodCategoryInDTO.getRestaurantId());
      throw new NotFoundException(Constants.RESTAURANT_NOT_FOUND);
    }

    Optional<FoodCategory> existingCategoryWithName = foodCategoryRepository.findByRestaurantIdAndFoodCategoryName(
      foodCategoryInDTO.getRestaurantId(),
      foodCategoryInDTO.getFoodCategoryName().toUpperCase()
    );
    if (existingCategoryWithName.isPresent()) {
      logger.error("Food category with name {} already exists in restaurant ID {}", foodCategoryInDTO.getFoodCategoryName(),
        foodCategoryInDTO.getRestaurantId());
      throw new AlreadyExistException(Constants.FOOD_CATEGORY_ALREADY_EXIST);
    }

    FoodCategory convertedFoodCategory = FoodCategoryDtoConverter.convertToEntity(foodCategoryInDTO);
    FoodCategory savedFoodCategory = foodCategoryRepository.save(convertedFoodCategory);
    FoodCategoryDtoConverter.convertToOutDTO(savedFoodCategory);
    logger.info("Food category {} successfully added", foodCategoryInDTO.getFoodCategoryName());
    return new CommonResponse(Constants.FOOD_CATEGORY_ADDED_SUCCESS);
  }

  /**
   * Retrieves food categories for a specific restaurant.
   *
   * @param restaurantId The ID of the restaurant whose food categories are to be fetched.
   * @return A list of {@link FoodCategoryOutDTO} objects representing the food categories.
   * @throws NotFoundException If the restaurant is not found.
   */
  @Override
  public List<FoodCategoryOutDTO> getFoodCategoryByRestaurantId(Integer restaurantId) {
    logger.info("Fetching food categories for restaurant ID: {}", restaurantId);

    Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
    if (!restaurant.isPresent()) {
      logger.error("Restaurant with ID {} not found", restaurantId);
      throw new NotFoundException(Constants.RESTAURANT_NOT_FOUND);
    }

    List<FoodCategory> foodCategories = foodCategoryRepository.findByRestaurantId(restaurantId);
    List<FoodCategoryOutDTO> foodCategoryOutDTOList = new ArrayList<>();
    for (FoodCategory foodCategory : foodCategories) {
      FoodCategoryOutDTO dto = FoodCategoryDtoConverter.convertToOutDTO(foodCategory);
      foodCategoryOutDTOList.add(dto);
    }

    logger.info("Retrieved {} food categories for restaurant ID {}", foodCategoryOutDTOList.size(), restaurantId);
    return foodCategoryOutDTOList;
  }

  /**
   * Updates an existing food category.
   *
   * @param foodCategoryId    The ID of the food category to be updated.
   * @param foodCategoryInDTO The updated details of the food category.
   * @return A {@link CommonResponse} indicating the result of the operation.
   * @throws NotFoundException     If the food category with the given ID is not found.
   * @throws AlreadyExistException If a food category with the same name already exists in the restaurant, excluding the current one.
   */
  @Override
  public CommonResponse updateFoodCategory(Integer foodCategoryId, FoodCategoryInDTO foodCategoryInDTO) {
    logger.info("Updating food category with ID: {}", foodCategoryId);

    Optional<FoodCategory> existingCategory = foodCategoryRepository.findById(foodCategoryId);
    if (!existingCategory.isPresent()) {
      logger.error("Food category with ID {} not found", foodCategoryId);
      throw new NotFoundException(Constants.FOOD_CATEGORY_NOT_FOUND);
    }

    Optional<FoodCategory> existingCategoryWithName = foodCategoryRepository.findByRestaurantIdAndFoodCategoryName(
      foodCategoryInDTO.getRestaurantId(),
      foodCategoryInDTO.getFoodCategoryName().toUpperCase()
    );
    if (existingCategoryWithName.isPresent() && !existingCategoryWithName.get().getFoodCategoryId().equals(foodCategoryId)) {
      logger.error("Food category with name {} already exists for restaurant ID {}", foodCategoryInDTO.getFoodCategoryName(),
        foodCategoryInDTO.getRestaurantId());
      throw new AlreadyExistException(Constants.FOOD_CATEGORY_ALREADY_EXIST);
    }

    FoodCategory categoryToUpdate = existingCategory.get();
    categoryToUpdate.setRestaurantId(foodCategoryInDTO.getRestaurantId());
    categoryToUpdate.setFoodCategoryName(foodCategoryInDTO.getFoodCategoryName().toUpperCase());

    FoodCategory updatedCategory = foodCategoryRepository.save(categoryToUpdate);
    logger.info("Successfully updated food category with ID: {}", foodCategoryId);
    FoodCategoryDtoConverter.convertToOutDTO(updatedCategory);
    return new CommonResponse(Constants.FOOD_CATEGORY_UPDATED_SUCCESS);
  }
}

