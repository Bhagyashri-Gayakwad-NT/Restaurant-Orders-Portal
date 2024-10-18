package com.nt.restaurant.microservice.dtoconverter;
import com.nt.restaurant.microservice.dto.FoodItemInDTO;
import com.nt.restaurant.microservice.dto.FoodItemOutDTO;
import com.nt.restaurant.microservice.entities.FoodItem;
import com.nt.restaurant.microservice.dtoconvertion.FoodItemDtoConverter;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FoodItemDtoConverterTest {

  @Test
  void inDtoToEntity_ShouldConvertInDTOToEntity() throws IOException {
    FoodItemInDTO foodItemInDTO = mock(FoodItemInDTO.class);
    MockMultipartFile mockImageFile = new MockMultipartFile("foodItemImage", "image.jpg", "image/jpeg", "sample image content".getBytes());

    when(foodItemInDTO.getFoodCategoryId()).thenReturn(1);
    when(foodItemInDTO.getRestaurantId()).thenReturn(2);
    when(foodItemInDTO.getFoodItemName()).thenReturn("FoodItemName");
    when(foodItemInDTO.getDescription()).thenReturn("Test Description");
    when(foodItemInDTO.getPrice()).thenReturn(10.99);
    when(foodItemInDTO.getFoodItemImage()).thenReturn(mockImageFile);

    FoodItem foodItem = FoodItemDtoConverter.inDtoToEntity(foodItemInDTO);

    assertNotNull(foodItem);
    assertEquals(1, foodItem.getCategoryId());
    assertEquals(2, foodItem.getRestaurantId());
    assertEquals("FOODITEMNAME", foodItem.getFoodItemName());
    assertEquals("Test Description", foodItem.getDescription());
    assertEquals(10.99, foodItem.getPrice());
    assertTrue(foodItem.isAvailable());
    assertArrayEquals(mockImageFile.getBytes(), foodItem.getFoodItemImage());
  }

  @Test
  void entityToOutDTO_ShouldConvertEntityToOutDTO() {
    FoodItem foodItem = new FoodItem();
    foodItem.setFoodItemId(100);
    foodItem.setCategoryId(1);
    foodItem.setRestaurantId(2);
    foodItem.setFoodItemName("FoodItemName");
    foodItem.setDescription("Test Description");
    foodItem.setPrice(10.99);
    foodItem.setAvailable(true);
    foodItem.setFoodItemImage("sample image content".getBytes());

    FoodItemOutDTO foodItemOutDTO = FoodItemDtoConverter.entityToOutDTO(foodItem);

    assertNotNull(foodItemOutDTO);
    assertEquals(100, foodItemOutDTO.getFoodItemId());
    assertEquals(1, foodItemOutDTO.getCategoryId());
    assertEquals(2, foodItemOutDTO.getRestaurantId());
    assertEquals("FoodItemName", foodItemOutDTO.getFoodItemName());
    assertEquals("Test Description", foodItemOutDTO.getDescription());
    assertEquals(10.99, foodItemOutDTO.getprice());
    assertTrue(foodItemOutDTO.isAvailable());
    assertArrayEquals("sample image content".getBytes(), foodItemOutDTO.getFoodItemImage());
  }
}
