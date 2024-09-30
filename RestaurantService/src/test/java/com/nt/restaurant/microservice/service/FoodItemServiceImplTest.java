package com.nt.restaurant.microservice.service;

import com.nt.restaurant.microservice.dto.CommonResponse;
import com.nt.restaurant.microservice.dto.FoodItemInDTO;
import com.nt.restaurant.microservice.dto.FoodItemOutDTO;
import com.nt.restaurant.microservice.dto.FoodItemUpdateInDTO;
import com.nt.restaurant.microservice.entities.FoodCategory;
import com.nt.restaurant.microservice.entities.FoodItem;
import com.nt.restaurant.microservice.entities.Restaurant;
import com.nt.restaurant.microservice.exception.ResourceAlreadyExistException;
import com.nt.restaurant.microservice.exception.ResourceNotFoundException;
import com.nt.restaurant.microservice.repository.FoodCategoryRepository;
import com.nt.restaurant.microservice.repository.FoodItemRepository;
import com.nt.restaurant.microservice.repository.RestaurantRepository;
import com.nt.restaurant.microservice.serviceimpl.FoodItemServiceImpl;
import com.nt.restaurant.microservice.util.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class FoodItemServiceImplTest {

  @InjectMocks
  private FoodItemServiceImpl foodItemService;

  @Mock
  private FoodCategoryRepository foodCategoryRepository;

  @Mock
  private RestaurantRepository restaurantRepository;

  @Mock
  private FoodItemRepository foodItemRepository;

  @Mock
  private MultipartFile image;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }


  @Test
  void testAddFoodItem_FoodItemAlreadyExists() {
    FoodItemInDTO foodItemInDTO = new FoodItemInDTO();
    foodItemInDTO.setFoodItemName("Test FoodItem");
    foodItemInDTO.setRestaurantId(1);
    foodItemInDTO.setFoodCategoryId(1);

    Restaurant restaurant = new Restaurant();
    FoodCategory foodCategory = new FoodCategory();
    FoodItem existingFoodItem = new FoodItem();

    when(restaurantRepository.findById(any(Integer.class))).thenReturn(Optional.of(restaurant));
    when(foodCategoryRepository.findById(any(Integer.class))).thenReturn(Optional.of(foodCategory));
    when(foodItemRepository.findByFoodItemNameAndRestaurantId(any(String.class), any(Integer.class)))
      .thenReturn(Optional.of(existingFoodItem));

    assertThrows(ResourceAlreadyExistException.class, () -> foodItemService.addFoodItem(foodItemInDTO, image));
  }


  @Test
  void testGetFoodItemsByCategory_Success() {
    List<FoodItem> foodItems = new ArrayList<>();
    FoodItem foodItem = new FoodItem();
    foodItems.add(foodItem);

    when(foodItemRepository.findByCategoryId(any(Integer.class))).thenReturn(foodItems);

    List<FoodItemOutDTO> foodItemOutDTOList = foodItemService.getFoodItemsByCategory(1);

    assertFalse(foodItemOutDTOList.isEmpty());
  }

  @Test
  void testGetFoodItemsByCategory_NotFound() {
    when(foodItemRepository.findByCategoryId(any(Integer.class))).thenReturn(new ArrayList<>());

    assertThrows(ResourceNotFoundException.class, () -> foodItemService.getFoodItemsByCategory(1));
  }

  @Test
  void testGetFoodItemsByRestaurant_Success() {
    // Arrange
    Integer restaurantId = 1;
    List<FoodItem> foodItems = new ArrayList<>();
    FoodItem foodItem = new FoodItem();
    foodItem.setFoodItemName("Test FoodItem");
    foodItems.add(foodItem);

    when(foodItemRepository.findByRestaurantId(restaurantId)).thenReturn(foodItems);

    // Act
    List<FoodItemOutDTO> foodItemOutDTOList = foodItemService.getFoodItemsByRestaurant(restaurantId);

    // Assert
    assertFalse(foodItemOutDTOList.isEmpty());
    assertEquals(1, foodItemOutDTOList.size());
    assertEquals("Test FoodItem", foodItemOutDTOList.get(0).getfoodItemName()); // Assuming name is converted to upper case
  }

  @Test
  void testGetFoodItemsByRestaurant_NotFound() {
    // Arrange
    Integer restaurantId = 1;

    when(foodItemRepository.findByRestaurantId(restaurantId)).thenReturn(new ArrayList<>());

    // Act & Assert
    assertThrows(ResourceNotFoundException.class, () -> foodItemService.getFoodItemsByRestaurant(restaurantId));
  }

  @Test
  void testUpdateFoodItemByFoodItemId_Success() throws IOException {
    FoodItemUpdateInDTO foodItemUpdateInDTO = new FoodItemUpdateInDTO();
    foodItemUpdateInDTO.setFoodItemName("Updated FoodItem");

    FoodItem existingFoodItem = new FoodItem();
    existingFoodItem.setFoodItemName("Test FoodItem");

    when(foodItemRepository.findById(any(Integer.class))).thenReturn(Optional.of(existingFoodItem));
    when(foodItemRepository.save(any(FoodItem.class))).thenReturn(existingFoodItem);

    CommonResponse response = foodItemService.updateFoodItemByFoodItemId(1, foodItemUpdateInDTO);

    assertEquals(Constants.FOOD_ITEM_UPDATED_SUCCESS, response.getMessage());
    verify(foodItemRepository).save(any(FoodItem.class));
  }

  @Test
  void testUpdateFoodItemByFoodItemId_ImageProcessingError() throws IOException {
    FoodItemUpdateInDTO foodItemUpdateInDTO = new FoodItemUpdateInDTO();
    foodItemUpdateInDTO.setFoodItemName("Updated FoodItem");
    foodItemUpdateInDTO.setFoodItemImage(image);

    FoodItem existingFoodItem = new FoodItem();
    existingFoodItem.setFoodItemName("Test FoodItem");

    when(foodItemRepository.findById(any(Integer.class))).thenReturn(Optional.of(existingFoodItem));
    doThrow(new IOException("Image processing failed")).when(image).getBytes();

    assertThrows(RuntimeException.class, () -> foodItemService.updateFoodItemByFoodItemId(1, foodItemUpdateInDTO));
  }

  @Test
  void testGetFoodItemImage_Success() {
    FoodItem foodItem = new FoodItem();
    foodItem.setFoodItemImage(new byte[] {1, 2, 3});

    when(foodItemRepository.findById(any(Integer.class))).thenReturn(Optional.of(foodItem));

    byte[] image = foodItemService.getFoodItemImage(1);

    assertArrayEquals(new byte[] {1, 2, 3}, image);
  }

  @Test
  void testFindFoodItemById_NotFound() {
    when(foodItemRepository.findById(any(Integer.class))).thenReturn(Optional.empty());

    assertThrows(ResourceNotFoundException.class, () -> foodItemService.findFoodItemById(1));
  }
}
