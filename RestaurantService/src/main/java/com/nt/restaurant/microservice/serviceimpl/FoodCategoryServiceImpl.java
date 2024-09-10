package com.nt.restaurant.microservice.serviceimpl;

import com.nt.restaurant.microservice.dtoconvertion.FoodCategoryDtoConverter;
import com.nt.restaurant.microservice.entities.FoodCategory;
import com.nt.restaurant.microservice.entities.Restaurant;
import com.nt.restaurant.microservice.exception.AlreadyExistException;
import com.nt.restaurant.microservice.exception.NotFoundException;
import com.nt.restaurant.microservice.indto.FoodCategoryInDTO;
import com.nt.restaurant.microservice.outdto.CommonResponse;
import com.nt.restaurant.microservice.outdto.FoodCategoryOutDTO;
import com.nt.restaurant.microservice.repository.FoodCategoryRepository;
import com.nt.restaurant.microservice.repository.RestaurantRepository;
import com.nt.restaurant.microservice.service.FoodCategoryservice;
import com.nt.restaurant.microservice.util.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FoodCategoryServiceImpl implements FoodCategoryservice {

  private static final Logger logger = LogManager.getLogger(FoodCategoryServiceImpl.class);

  @Autowired
  private FoodCategoryRepository foodCategoryRepository;

  @Autowired
  private RestaurantRepository restaurantRepository;

  @Override
  public CommonResponse addFoodCategory(FoodCategoryInDTO foodCategoryInDTO) {
    logger.info("Adding food category: {}", foodCategoryInDTO);

    Optional<Restaurant> restaurant = restaurantRepository.findById(foodCategoryInDTO.getRestaurantId());
    if (!restaurant.isPresent()) {
      logger.error("Restaurant with ID {} not found", foodCategoryInDTO.getRestaurantId());
      throw new NotFoundException("Restaurant not found");
    }

    Optional<FoodCategory> existingCategoryWithName = foodCategoryRepository.findByRestaurantIdAndFoodCategoryName(
      foodCategoryInDTO.getRestaurantId(),
      foodCategoryInDTO.getFoodCategoryName().toUpperCase()
    );
    if (existingCategoryWithName.isPresent()) {
      logger.error("Food category with name {} already exists in restaurant ID {}", foodCategoryInDTO.getFoodCategoryName(), foodCategoryInDTO.getRestaurantId());
      throw new AlreadyExistException("Food category already exists");
    }

    FoodCategory convertedFoodCategory = FoodCategoryDtoConverter.convertToEntity(foodCategoryInDTO);
    FoodCategory savedFoodCategory = foodCategoryRepository.save(convertedFoodCategory);
    FoodCategoryDtoConverter.convertToOutDTO(savedFoodCategory);
    logger.info("Food category {} successfully added", foodCategoryInDTO.getFoodCategoryName());
    return new CommonResponse(Constants.FOOD_CATEGORY_ADDED_SUCCESS);
  }

  @Override
  public List<FoodCategoryOutDTO> getFoodCategoryByRestaurantId(Integer restaurantId) {
    logger.info("Fetching food categories for restaurant ID: {}", restaurantId);

    Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
    if (!restaurant.isPresent()) {
      logger.error("Restaurant with ID {} not found", restaurantId);
      throw new NotFoundException("Restaurant not found");
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

  @Override
  public CommonResponse updateFoodCategory(Integer foodCategoryId, FoodCategoryInDTO foodCategoryInDTO) {
    logger.info("Updating food category with ID: {}", foodCategoryId);

    Optional<FoodCategory> existingCategory = foodCategoryRepository.findById(foodCategoryId);
    if (!existingCategory.isPresent()) {
      logger.error("Food category with ID {} not found", foodCategoryId);
      throw new NotFoundException("Food category not found");
    }

    Optional<FoodCategory> existingCategoryWithName = foodCategoryRepository.findByRestaurantIdAndFoodCategoryName(
      foodCategoryInDTO.getRestaurantId(),
      foodCategoryInDTO.getFoodCategoryName().toUpperCase()
    );
    if (existingCategoryWithName.isPresent() && !existingCategoryWithName.get().getFoodCategoryId().equals(foodCategoryId)) {
      logger.error("Food category with name {} already exists for restaurant ID {}", foodCategoryInDTO.getFoodCategoryName(), foodCategoryInDTO.getRestaurantId());
      throw new AlreadyExistException("Food category already exists for this restaurant");
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
