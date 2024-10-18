package com.nt.restaurant.microservice.dtoconverter;
import com.nt.restaurant.microservice.dto.FoodCategoryInDTO;
import com.nt.restaurant.microservice.dto.FoodCategoryOutDTO;
import com.nt.restaurant.microservice.entities.FoodCategory;
import com.nt.restaurant.microservice.dtoconvertion.FoodCategoryDtoConverter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FoodCategoryDtoConverterTest {

  @Test
  void convertToEntity_ShouldConvertInDTOToEntity() {
    FoodCategoryInDTO foodCategoryInDTO = new FoodCategoryInDTO();
    foodCategoryInDTO.setRestaurantId(1);
    foodCategoryInDTO.setFoodCategoryName("FoodCategoryName");

    FoodCategory foodCategory = FoodCategoryDtoConverter.convertToEntity(foodCategoryInDTO);

    assertNotNull(foodCategory);
    assertEquals(1, foodCategory.getRestaurantId());
    assertEquals("FOODCATEGORYNAME", foodCategory.getFoodCategoryName());
  }

  @Test
  void convertToOutDTO_ShouldConvertEntityToOutDTO() {
    FoodCategory foodCategory = new FoodCategory();
    foodCategory.setFoodCategoryId(100);
    foodCategory.setRestaurantId(1);
    foodCategory.setFoodCategoryName("FoodCategoryName");

    FoodCategoryOutDTO foodCategoryOutDTO = FoodCategoryDtoConverter.convertToOutDTO(foodCategory);

    assertNotNull(foodCategoryOutDTO);
    assertEquals(100, foodCategoryOutDTO.getFoodCategoryId());
    assertEquals(1, foodCategoryOutDTO.getRestaurantId());
    assertEquals("FoodCategoryName", foodCategoryOutDTO.getFoodCategoryName());
  }
}
