package com.nt.restaurant.microservice.serviceimpl;

import com.nt.restaurant.microservice.dtoconvertion.FoodItemDtoConverter;
import com.nt.restaurant.microservice.entities.FoodCategory;
import com.nt.restaurant.microservice.entities.FoodItem;
import com.nt.restaurant.microservice.entities.Restaurant;
import com.nt.restaurant.microservice.exception.AlreadyExistException;
import com.nt.restaurant.microservice.exception.NotFoundException;
import com.nt.restaurant.microservice.indto.FoodItemInDTO;
import com.nt.restaurant.microservice.outdto.CommonResponse;
import com.nt.restaurant.microservice.outdto.FoodItemOutDTO;
import com.nt.restaurant.microservice.repository.FoodCategoryRepository;
import com.nt.restaurant.microservice.repository.FoodItemRepository;
import com.nt.restaurant.microservice.repository.RestaurantRepository;
import com.nt.restaurant.microservice.service.FoodItemService;
import com.nt.restaurant.microservice.util.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FoodItemServiceImpl implements FoodItemService {

  private static final Logger logger = LogManager.getLogger(FoodItemServiceImpl.class);

  @Autowired
  private FoodCategoryRepository foodCategoryRepository;

  @Autowired
  private RestaurantRepository restaurantRepository;

  @Autowired
  private FoodItemRepository foodItemRepository;

  @Override
  public CommonResponse addFoodItem(FoodItemInDTO foodItemInDTO) {

    logger.info("Attempting to add food item: {}", foodItemInDTO.getFoodItemName());

    Optional<Restaurant> restaurant = restaurantRepository.findById(foodItemInDTO.getRestaurantId());
    Optional<FoodCategory> existingCategory = foodCategoryRepository.findById(foodItemInDTO.getFoodCategoryId());
    Optional<FoodItem> existingFoodItem = foodItemRepository.findByFoodItemNameAndRestaurantId(
      foodItemInDTO.getFoodItemName().toUpperCase(),
      foodItemInDTO.getRestaurantId()
    );

    if (!restaurant.isPresent()) {
      logger.error("Restaurant not found for ID: {}", foodItemInDTO.getRestaurantId());
      throw new NotFoundException("Restaurant not found");
    } else if (!existingCategory.isPresent()) {
      logger.error("Food category not found for ID: {}", foodItemInDTO.getFoodCategoryId());
      throw new NotFoundException("Food category not found");
    } else if (existingFoodItem.isPresent()) {
      logger.warn("Food item already exists in restaurant: {}", foodItemInDTO.getFoodItemName());
      throw new AlreadyExistException("Food Item is already present in this restaurant");
    }

    FoodItem foodItem = FoodItemDtoConverter.inDtoToEntity(foodItemInDTO);
    FoodItem savedFoodItem = foodItemRepository.save(foodItem);
    FoodItemDtoConverter.entityToOutDTO(savedFoodItem);
    logger.info("Successfully added food item: {}", foodItem.getFoodItemName());

    return new CommonResponse(Constants.FOOD_ITEM_ADDED_SUCCESS);
  }

  @Override
  public List<FoodItemOutDTO> getFoodItemsByCategory(Integer categoryId) {
    logger.info("Fetching food items for category ID: {}", categoryId);

    List<FoodItem> foodItems = foodItemRepository.findByCategoryId(categoryId);
    if (foodItems.isEmpty()) {
      logger.error("No food items found for category ID: {}", categoryId);
      throw new NotFoundException("No Food Item Present");
    }

    List<FoodItemOutDTO> foodItemOutDTOList = new ArrayList<>();
    for (FoodItem foodItem : foodItems) {
      FoodItemOutDTO foodItemOutDTO = FoodItemDtoConverter.entityToOutDTO(foodItem);
      foodItemOutDTOList.add(foodItemOutDTO);
    }

    logger.info("Found {} food items for category ID: {}", foodItemOutDTOList.size(), categoryId);
    return foodItemOutDTOList;
  }

  @Override
  public List<FoodItemOutDTO> getFoodItemsByRestaurant(Integer restaurantId) {
    logger.info("Fetching food items for restaurant ID: {}", restaurantId);

    List<FoodItem> foodItems = foodItemRepository.findByRestaurantId(restaurantId);
    if (foodItems.isEmpty()) {
      logger.error("No food items found for restaurant ID: {}", restaurantId);
      throw new NotFoundException("No Food Item Present");
    }

    List<FoodItemOutDTO> foodItemOutDTOList = new ArrayList<>();
    for (FoodItem foodItem : foodItems) {
      FoodItemOutDTO foodItemOutDTO = FoodItemDtoConverter.entityToOutDTO(foodItem);
      foodItemOutDTOList.add(foodItemOutDTO);
    }

    logger.info("Found {} food items for restaurant ID: {}", foodItemOutDTOList.size(), restaurantId);
    return foodItemOutDTOList;
  }

  @Override
  public CommonResponse updateFoodItemByFoodItemId(Integer foodItemId, FoodItemInDTO foodItemInDTO) {
    logger.info("Attempting to update food item with ID: {}", foodItemId);

    FoodItem existingFoodItem = findFoodItemById(foodItemId);
    try {
      updateFoodItemRequest(foodItemInDTO, existingFoodItem);
    } catch (IOException e) {
      logger.error("Error processing food item image for ID: {}", foodItemId, e);
      throw new RuntimeException("Error processing food item image", e);
    }

    FoodItem updatedFoodItem = foodItemRepository.save(existingFoodItem);
    logger.info("Successfully updated food item with ID: {}", foodItemId);
    convertFoodItemToFoodItemResponse(updatedFoodItem);

    return new CommonResponse(Constants.FOOD_ITEM_UPDATED_SUCCESS);
  }

  private void updateFoodItemRequest(FoodItemInDTO foodItemInDTO, FoodItem existingFoodItem) throws IOException {
    existingFoodItem.setCategoryId(foodItemInDTO.getFoodCategoryId());
    existingFoodItem.setRestaurantId(foodItemInDTO.getRestaurantId());
    existingFoodItem.setFoodItemName(foodItemInDTO.getFoodItemName().toUpperCase());
    existingFoodItem.setDescription(foodItemInDTO.getDescription());
    existingFoodItem.setPrice(foodItemInDTO.getPrice());
    existingFoodItem.setAvailable(foodItemInDTO.isAvailable());

    if (foodItemInDTO.getFoodItemImage() != null && !foodItemInDTO.getFoodItemImage().isEmpty()) {
      logger.info("Updating food item image for food item: {}", existingFoodItem.getFoodItemName());
      existingFoodItem.setFoodItemImage(foodItemInDTO.getFoodItemImage().getBytes());
    }
  }

  private FoodItemOutDTO convertFoodItemToFoodItemResponse(FoodItem foodItem) {
    return FoodItemDtoConverter.entityToOutDTO(foodItem);
  }

  @Override
  public byte[] getFoodItemImage(Integer id) {
    logger.info("Fetching food item image for ID: {}", id);

    FoodItem foodItem = findFoodItemById(id);
    return foodItem.getFoodItemImage();
  }

  public FoodItem findFoodItemById(Integer id) {
    logger.info("Looking for food item with ID: {}", id);

    return foodItemRepository.findById(id)
      .orElseThrow(() -> new NotFoundException("Food item not found with ID: " + id));
  }
}
