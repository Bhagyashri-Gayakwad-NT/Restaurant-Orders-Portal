package com.nt.restaurant.microservice.service;

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
import com.nt.restaurant.microservice.serviceimpl.FoodItemServiceImpl;
import com.nt.restaurant.microservice.util.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
public class FoodItemServiceImplTest {

  @Mock
  private FoodCategoryRepository foodCategoryRepository;

  @Mock
  private RestaurantRepository restaurantRepository;

  @Mock
  private FoodItemRepository foodItemRepository;

  @InjectMocks
  private FoodItemServiceImpl foodItemServiceImpl;

  @Autowired
  public FoodItemServiceImplTest() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testAddFoodItem_Success() {
    FoodItemInDTO foodItemInDTO = new FoodItemInDTO();
    foodItemInDTO.setFoodItemName("Pizza");
    foodItemInDTO.setFoodCategoryId(1);
    foodItemInDTO.setRestaurantId(1);
    foodItemInDTO.setPrice(10.0);
    foodItemInDTO.setAvailable(true);

    Restaurant restaurant = new Restaurant();
    FoodCategory category = new FoodCategory();
    FoodItem foodItem = new FoodItem();

    when(restaurantRepository.findById(anyInt())).thenReturn(Optional.of(restaurant));
    when(foodCategoryRepository.findById(anyInt())).thenReturn(Optional.of(category));
    when(foodItemRepository.findByFoodItemName(anyString())).thenReturn(Optional.empty());
    when(foodItemRepository.save(any(FoodItem.class))).thenReturn(foodItem);

    CommonResponse response = foodItemServiceImpl.addFoodItem(foodItemInDTO);

    assertEquals(Constants.FOOD_ITEM_ADDED_SUCCESS, response.getMessage());
    verify(foodItemRepository).save(any(FoodItem.class));
  }

  @Test
  public void testAddFoodItem_RestaurantNotFound() {
    FoodItemInDTO foodItemInDTO = new FoodItemInDTO();
    foodItemInDTO.setRestaurantId(1);

    when(restaurantRepository.findById(anyInt())).thenReturn(Optional.empty());

    assertThrows(NotFoundException.class, () -> {
      foodItemServiceImpl.addFoodItem(foodItemInDTO);
    });
  }

  @Test
  public void testAddFoodItem_FoodCategoryNotFound() {
    FoodItemInDTO foodItemInDTO = new FoodItemInDTO();
    foodItemInDTO.setFoodCategoryId(1);

    when(restaurantRepository.findById(anyInt())).thenReturn(Optional.of(new Restaurant()));
    when(foodCategoryRepository.findById(anyInt())).thenReturn(Optional.empty());

    assertThrows(NotFoundException.class, () -> {
      foodItemServiceImpl.addFoodItem(foodItemInDTO);
    });
  }

  @Test
  public void testAddFoodItem_FoodItemAlreadyExists() {
    FoodItemInDTO foodItemInDTO = new FoodItemInDTO();
    foodItemInDTO.setFoodItemName("Pizza");

    when(restaurantRepository.findById(anyInt())).thenReturn(Optional.of(new Restaurant()));
    when(foodCategoryRepository.findById(anyInt())).thenReturn(Optional.of(new FoodCategory()));
    when(foodItemRepository.findByFoodItemName(anyString())).thenReturn(Optional.of(new FoodItem()));

    assertThrows(AlreadyExistException.class, () -> {
      foodItemServiceImpl.addFoodItem(foodItemInDTO);
    });
  }

//  @Test
//  public void testGetFoodItemsByCategory_Success() {
//    List<FoodItem> foodItems = new ArrayList<>();
//    foodItems.add(new FoodItem());
//    FoodItemOutDTO foodItemOutDTO = new FoodItemOutDTO();
//
//    when(foodItemRepository.findByCategoryId(anyInt())).thenReturn(foodItems);
//    when(FoodItemDtoConverter.entityToOutDTO(any(FoodItem.class))).thenReturn(foodItemOutDTO);
//
//    List<FoodItemOutDTO> response = foodItemServiceImpl.getFoodItemsByCategory(1);
//
//    assertEquals(1, response.size());
//  }

  @Test
  public void testGetFoodItemsByCategory_NotFound() {
    when(foodItemRepository.findByCategoryId(anyInt())).thenReturn(new ArrayList<>());

    assertThrows(NotFoundException.class, () -> {
      foodItemServiceImpl.getFoodItemsByCategory(1);
    });
  }

//  @Test
//  public void testGetFoodItemsByRestaurant_Success() {
//    List<FoodItem> foodItems = new ArrayList<>();
//    foodItems.add(new FoodItem());
//    FoodItemOutDTO foodItemOutDTO = new FoodItemOutDTO();
//
//    when(foodItemRepository.findByRestaurantId(anyInt())).thenReturn(foodItems);
//    when(FoodItemDtoConverter.entityToOutDTO(any(FoodItem.class))).thenReturn(foodItemOutDTO);
//
//    List<FoodItemOutDTO> response = foodItemServiceImpl.getFoodItemsByRestaurant(1);
//
//    assertEquals(1, response.size());
//  }

  @Test
  public void testGetFoodItemsByRestaurant_NotFound() {
    when(foodItemRepository.findByRestaurantId(anyInt())).thenReturn(new ArrayList<>());

    assertThrows(NotFoundException.class, () -> {
      foodItemServiceImpl.getFoodItemsByRestaurant(1);
    });
  }

  @Test
  public void testUpdateFoodItemByFoodItemId_Success() throws IOException {
    FoodItemInDTO foodItemInDTO = new FoodItemInDTO();
    foodItemInDTO.setFoodItemName("Burger");

    FoodItem existingFoodItem = new FoodItem();
    when(foodItemRepository.findById(anyInt())).thenReturn(Optional.of(existingFoodItem));
    when(foodItemRepository.save(any(FoodItem.class))).thenReturn(existingFoodItem);

    CommonResponse response = foodItemServiceImpl.updateFoodItemByFoodItemId(1, foodItemInDTO);

    assertEquals(Constants.FOOD_ITEM_UPDATED_SUCCESS, response.getMessage());
    verify(foodItemRepository).save(any(FoodItem.class));
  }

  @Test
  public void testUpdateFoodItemByFoodItemId_NotFound() {
    FoodItemInDTO foodItemInDTO = new FoodItemInDTO();
    when(foodItemRepository.findById(anyInt())).thenReturn(Optional.empty());

    assertThrows(NotFoundException.class, () -> {
      foodItemServiceImpl.updateFoodItemByFoodItemId(1, foodItemInDTO);
    });
  }

  @Test
  public void testGetFoodItemImage_Success() {
    FoodItem foodItem = new FoodItem();
    foodItem.setFoodItemImage(new byte[]{1, 2, 3});

    when(foodItemRepository.findById(anyInt())).thenReturn(Optional.of(foodItem));

    byte[] image = foodItemServiceImpl.getFoodItemImage(1);

    assertArrayEquals(new byte[]{1, 2, 3}, image);
  }

  @Test
  public void testGetFoodItemImage_NotFound() {
    when(foodItemRepository.findById(anyInt())).thenReturn(Optional.empty());

    assertThrows(NotFoundException.class, () -> {
      foodItemServiceImpl.getFoodItemImage(1);
    });
  }
}