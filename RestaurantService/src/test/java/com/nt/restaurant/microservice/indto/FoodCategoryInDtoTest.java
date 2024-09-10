package com.nt.restaurant.microservice.indto;

import org.junit.jupiter.api.Test;

import javax.validation.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class FoodCategoryInDTOTest {

  private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
  private final Validator validator = factory.getValidator();

  @Test
  void testConstructorAndGetters() {
    FoodCategoryInDTO dto = new FoodCategoryInDTO(1, "Indian");

    assertEquals(1, dto.getRestaurantId());
    assertEquals("Indian", dto.getFoodCategoryName());
  }

  @Test
  void testSetters() {
    FoodCategoryInDTO dto = new FoodCategoryInDTO();
    dto.setRestaurantId(2);
    dto.setFoodCategoryName("Chinese");

    assertEquals(2, dto.getRestaurantId());
    assertEquals("Chinese", dto.getFoodCategoryName());
  }

  @Test
  void testEquals() {
    FoodCategoryInDTO dto1 = new FoodCategoryInDTO(1, "Italian");
    FoodCategoryInDTO dto2 = new FoodCategoryInDTO(1, "Italian");

    assertEquals(dto1, dto2);
    assertEquals(dto1.hashCode(), dto2.hashCode());
  }

  @Test
  void testNotEquals() {
    FoodCategoryInDTO dto1 = new FoodCategoryInDTO(1, "Italian");
    FoodCategoryInDTO dto2 = new FoodCategoryInDTO(2, "Mexican");

    assertNotEquals(dto1, dto2);
    assertNotEquals(dto1.hashCode(), dto2.hashCode());
  }

  @Test
  void testToString() {
    FoodCategoryInDTO dto = new FoodCategoryInDTO(1, "Italian");

    String expected = "FoodCategoryInDTO{restaurantId=1, foodCategoryName='Italian'}";
    assertEquals(expected, dto.toString());
  }

  @Test
  void testValidation_Success() {
    FoodCategoryInDTO dto = new FoodCategoryInDTO(1, "Mexican");

    Set<ConstraintViolation<FoodCategoryInDTO>> violations = validator.validate(dto);
    assertTrue(violations.isEmpty());
  }

  @Test
  void testValidation_Failure() {
    FoodCategoryInDTO dto = new FoodCategoryInDTO(null, "InvalidName123");

    Set<ConstraintViolation<FoodCategoryInDTO>> violations = validator.validate(dto);
    assertEquals(2, violations.size());

    for (ConstraintViolation<FoodCategoryInDTO> violation : violations) {
      String message = violation.getMessage();
      assertTrue(message.equals("Restaurant ID cannot be null") ||
        message.equals("Category name must contain only alphabets"));
    }
  }
  @Test
  void testValidation_FoodCategoryNameBlank() {
    FoodCategoryInDTO dto = new FoodCategoryInDTO(1, "");

    Set<ConstraintViolation<FoodCategoryInDTO>> violations = validator.validate(dto);
    assertEquals(2, violations.size());

    List<String> errorMessages = violations.stream()
      .map(ConstraintViolation::getMessage)
      .collect(Collectors.toList());

    assertTrue(errorMessages.contains("Food category name cannot be blank"));
    assertTrue(errorMessages.contains("Category name must contain only alphabets"));
  }


  @Test
  void testValidation_FoodCategoryNameTooLong() {
    FoodCategoryInDTO dto = new FoodCategoryInDTO(1, "ThisNameIsWayTooLongForAFoodCategoryNameAndShouldFailTheValidationBecauseItExceedsOneHundredCharacters");

    Set<ConstraintViolation<FoodCategoryInDTO>> violations = validator.validate(dto);
    assertEquals(1, violations.size());

    String message = violations.iterator().next().getMessage();
    assertEquals("Food category name cannot exceed 100 characters", message);
  }
}

