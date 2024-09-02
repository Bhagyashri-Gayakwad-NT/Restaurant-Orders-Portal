package com.nt.restaurant.microservice.serviceimpl;

import com.nt.restaurant.microservice.dtoconvertion.FoodItemDtoConverter;
import com.nt.restaurant.microservice.entities.FoodCategory;
import com.nt.restaurant.microservice.entities.FoodItem;
import com.nt.restaurant.microservice.entities.Restaurant;
import com.nt.restaurant.microservice.exception.AlreadyExistException;
import com.nt.restaurant.microservice.exception.NotFoundException;
import com.nt.restaurant.microservice.indto.FoodItemInDTO;
import com.nt.restaurant.microservice.outdto.FoodItemOutDTO;
import com.nt.restaurant.microservice.repository.FoodCategoryRepository;
import com.nt.restaurant.microservice.repository.FoodItemRepository;
import com.nt.restaurant.microservice.repository.RestaurantRepository;
import com.nt.restaurant.microservice.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class FoodItemServiceImp implements FoodItemService {
  @Autowired
  private FoodCategoryRepository foodCategoryRepository;

  @Autowired
  private RestaurantRepository restaurantRepository;

  @Autowired
  private FoodItemRepository foodItemRepository;

  @Override
  public FoodItemOutDTO addFoodItem(FoodItemInDTO foodItemInDTO) {

    Optional<Restaurant> restaurant = restaurantRepository.findById(foodItemInDTO.getRestaurantId());
    Optional<FoodCategory> existingCategory = foodCategoryRepository.findById(foodItemInDTO.getFoodCategoryId());
    Optional<FoodItem> existingFoodItem = foodItemRepository.findByFoodItemName(foodItemInDTO.getFoodItemName().toUpperCase());
    if (!restaurant.isPresent()) {
      throw new NotFoundException("Restaurant not found");
    } else if (!existingCategory.isPresent()) {
      throw new NotFoundException("Food category not found");
    } else if (existingFoodItem.isPresent()) {
      throw new AlreadyExistException("Food Item is already present");
    }
    FoodItem foodItem = FoodItemDtoConverter.inDtoToEntity(foodItemInDTO);
    FoodItem savedFoodItem = foodItemRepository.save(foodItem);
    FoodItemOutDTO foodItemOutDTO = FoodItemDtoConverter.entityToOutDTO(savedFoodItem);
    return foodItemOutDTO;
  }

  @Override
  public List<FoodItemOutDTO> getFoodItemsByCategory(Integer categoryId) {
    List<FoodItem> foodItems = foodItemRepository.findByCategoryId(categoryId);
    if (foodItems.isEmpty()) {
      throw new NotFoundException("No Food Item Present");
    }
    List<FoodItemOutDTO> foodItemOutDTOList = new ArrayList<>();

    for (FoodItem foodItem : foodItems) {
      FoodItemOutDTO foodItemOutDTO = FoodItemDtoConverter.entityToOutDTO(foodItem);
      foodItemOutDTOList.add(foodItemOutDTO);
    }
    return foodItemOutDTOList;
  }

  @Override
  public List<FoodItemOutDTO> getFoodItemsByRestaurant(Integer restaurantId) {
    List<FoodItem> foodItems = foodItemRepository.findByRestaurantId(restaurantId);
    if (foodItems.isEmpty()) {
      throw new NotFoundException("No Food Item Present");
    }
    List<FoodItemOutDTO> foodItemOutDTOList = new ArrayList<>();

    for (FoodItem foodItem : foodItems) {
      FoodItemOutDTO foodItemOutDTO = FoodItemDtoConverter.entityToOutDTO(foodItem);
      foodItemOutDTOList.add(foodItemOutDTO);
    }
    return foodItemOutDTOList;
  }

  @Override
  public FoodItemOutDTO updateFoodItemByFoodItemId(Integer foodItemId, FoodItemInDTO foodItemInDTO) {
    FoodItem existingFoodItem = findFoodItemById(foodItemId);
    try {
      updateFoodItemRequest(foodItemInDTO, existingFoodItem);
    } catch (IOException e) {
      throw new RuntimeException("Error processing food item image", e);
    }
    FoodItem updatedFoodItem = foodItemRepository.save(existingFoodItem);

    return convertFoodItemToFoodItemResponse(updatedFoodItem);
  }
  private FoodItem findFoodItemById(Integer id) {
    return foodItemRepository.findById(id)
      .orElseThrow(() -> new NotFoundException("Food item not found with ID: " + id));
  }
  private void updateFoodItemRequest(FoodItemInDTO foodItemInDTO, FoodItem existingFoodItem) throws IOException {
    existingFoodItem.setCategoryId(foodItemInDTO.getFoodCategoryId());
    existingFoodItem.setRestaurantId(foodItemInDTO.getRestaurantId());
    existingFoodItem.setFoodItemName(foodItemInDTO.getFoodItemName().toUpperCase());
    existingFoodItem.setDescription(foodItemInDTO.getDescription());
    existingFoodItem.setPrice(foodItemInDTO.getPrice());
    existingFoodItem.setAvailable(foodItemInDTO.isAvailable());

    if (foodItemInDTO.getFoodItemImage() != null && !foodItemInDTO.getFoodItemImage().isEmpty()) {
      existingFoodItem.setFoodItemImage(foodItemInDTO.getFoodItemImage().getBytes());
    }
  }
  private FoodItemOutDTO convertFoodItemToFoodItemResponse(FoodItem foodItem) {
    return FoodItemDtoConverter.entityToOutDTO(foodItem);
  }
}