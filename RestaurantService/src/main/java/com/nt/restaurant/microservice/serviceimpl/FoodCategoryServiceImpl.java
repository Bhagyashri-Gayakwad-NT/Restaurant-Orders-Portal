package com.nt.restaurant.microservice.serviceimpl;

import com.nt.restaurant.microservice.dtoconvertion.FoodCategoryDtoConverter;
import com.nt.restaurant.microservice.entities.FoodCategory;
import com.nt.restaurant.microservice.entities.Restaurant;
import com.nt.restaurant.microservice.exception.AlreadyExistException;
import com.nt.restaurant.microservice.exception.NotFoundException;
import com.nt.restaurant.microservice.indto.FoodCategoryInDTO;
import com.nt.restaurant.microservice.outdto.FoodCategoryOutDTO;
import com.nt.restaurant.microservice.repository.FoodCategoryRepository;
import com.nt.restaurant.microservice.repository.RestaurantRepository;
import com.nt.restaurant.microservice.service.FoodCategoryservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class FoodCategoryServiceImpl implements FoodCategoryservice {
  @Autowired
  private FoodCategoryRepository foodCategoryRepository;

  @Autowired
  private RestaurantRepository restaurantRepository;
  @Override
  public FoodCategoryOutDTO addFoodCategory(FoodCategoryInDTO foodCategoryInDTO) {
    Optional<Restaurant> restaurant = restaurantRepository.findById(foodCategoryInDTO.getRestaurantId());
    Optional<FoodCategory> existingCategory = foodCategoryRepository.findByFoodCategoryName(foodCategoryInDTO.getFoodCategoryName().toUpperCase());
    if(!restaurant.isPresent()) {
    throw new NotFoundException("Restaurant not found");
    } else if (existingCategory.isPresent()) {
      throw new AlreadyExistException("Food category already exist");
    }
    FoodCategory convertedFoodCategory = FoodCategoryDtoConverter.convertToEntity(foodCategoryInDTO);
    FoodCategory savedFoodCategory = foodCategoryRepository.save(convertedFoodCategory);
    FoodCategoryOutDTO foodCategoryOutDTO = FoodCategoryDtoConverter.convertToOutDTO(savedFoodCategory);
    return foodCategoryOutDTO;
  }

  @Override
  public List<FoodCategoryOutDTO> getFoodCategoryByRestaurantId(Integer restaurantId) {
    Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
    if(!restaurant.isPresent()){
      throw new NotFoundException("Restaurant not found");
    }
    List<FoodCategory> foodCategories = foodCategoryRepository.findByRestaurantId(restaurantId);
    List<FoodCategoryOutDTO> foodCategoryOutDTOList = new ArrayList<>();

    for (FoodCategory foodCategory : foodCategories) {
      FoodCategoryOutDTO dto = FoodCategoryDtoConverter.convertToOutDTO(foodCategory);
      foodCategoryOutDTOList.add(dto);
    }

    return foodCategoryOutDTOList;
  }

  @Override
  public FoodCategoryOutDTO updateFoodCategory(Integer foodCategoryId, FoodCategoryInDTO foodCategoryInDTO) {
    Optional<FoodCategory> existingCategory = foodCategoryRepository.findById(foodCategoryId);

    if (!existingCategory.isPresent()) {
      throw new NotFoundException("Food category not found");
    }
    FoodCategory foodCategoryToUpdate = existingCategory.get();
    if (foodCategoryToUpdate.getRestaurantId().equals(foodCategoryInDTO.getRestaurantId()) &&
      foodCategoryToUpdate.getFoodCategoryName().equalsIgnoreCase(foodCategoryInDTO.getFoodCategoryName())) {
      throw new AlreadyExistException("Food category already exists with the same details");
    }
    Optional<FoodCategory> existingCategoryWithName = foodCategoryRepository.findByRestaurantIdAndFoodCategoryName(
      foodCategoryInDTO.getRestaurantId(),
      foodCategoryInDTO.getFoodCategoryName().toUpperCase()
    );

    if (existingCategoryWithName.isPresent() && !existingCategoryWithName.get().getFoodCategoryId().equals(foodCategoryId)) {
      throw new AlreadyExistException("Food category already exists for this restaurant");
    }
    FoodCategory categoryToUpdate = existingCategory.get();
    categoryToUpdate.setRestaurantId(foodCategoryInDTO.getRestaurantId());
    categoryToUpdate.setFoodCategoryName(foodCategoryInDTO.getFoodCategoryName().toUpperCase());

    FoodCategory updatedCategory = foodCategoryRepository.save(categoryToUpdate);

    return FoodCategoryDtoConverter.convertToOutDTO(updatedCategory);
  }
}
