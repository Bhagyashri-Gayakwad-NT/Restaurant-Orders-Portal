package com.nt.restaurant.microservice.indto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class FoodCategoryInDTOTest {

  private Validator validator;

  @BeforeEach
  void setUp() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  void testValidFoodCategoryInDTO() {
    FoodCategoryInDTO dto = new FoodCategoryInDTO(1, "Appetizers");
    Set<ConstraintViolation<FoodCategoryInDTO>> violations = validator.validate(dto);
    assertTrue(violations.isEmpty());
  }

  @Test
  void testRestaurantIdCannotBeNull() {
    FoodCategoryInDTO dto = new FoodCategoryInDTO(null, "Appetizers");
    Set<ConstraintViolation<FoodCategoryInDTO>> violations = validator.validate(dto);
    assertFalse(violations.isEmpty());

    ConstraintViolation<FoodCategoryInDTO> violation = violations.iterator().next();
    assertEquals("Restaurant ID cannot be null", violation.getMessage());
  }

  @Test
  void testFoodCategoryNameCannotBeBlank() {
    FoodCategoryInDTO dto = new FoodCategoryInDTO(1, "");
    Set<ConstraintViolation<FoodCategoryInDTO>> violations = validator.validate(dto);
    assertFalse(violations.isEmpty());

    ConstraintViolation<FoodCategoryInDTO> violation = violations.iterator().next();
    assertEquals("Food category name cannot be blank", violation.getMessage());
  }

  @Test
  void testFoodCategoryNameCannotExceedMaxLength() {
    String longName = repeatString("A", 101);
    FoodCategoryInDTO dto = new FoodCategoryInDTO(1, longName);
    Set<ConstraintViolation<FoodCategoryInDTO>> violations = validator.validate(dto);
    assertFalse(violations.isEmpty());

    ConstraintViolation<FoodCategoryInDTO> violation = violations.iterator().next();
    assertEquals("Food category name cannot exceed 100 characters", violation.getMessage());
  }

  @Test
  void testEqualsAndHashCode() {
    FoodCategoryInDTO dto1 = new FoodCategoryInDTO(1, "Appetizers");
    FoodCategoryInDTO dto2 = new FoodCategoryInDTO(1, "Appetizers");

    assertEquals(dto1, dto2);
    assertEquals(dto1.hashCode(), dto2.hashCode());
  }

  @Test
  void testNotEqualsWithDifferentRestaurantId() {
    FoodCategoryInDTO dto1 = new FoodCategoryInDTO(1, "Appetizers");
    FoodCategoryInDTO dto2 = new FoodCategoryInDTO(2, "Appetizers");

    assertNotEquals(dto1, dto2);
  }

  @Test
  void testNotEqualsWithDifferentCategoryName() {
    FoodCategoryInDTO dto1 = new FoodCategoryInDTO(1, "Appetizers");
    FoodCategoryInDTO dto2 = new FoodCategoryInDTO(1, "Main Course");

    assertNotEquals(dto1, dto2);
  }

  @Test
  void testToString() {
    FoodCategoryInDTO dto = new FoodCategoryInDTO(1, "Appetizers");
    String expectedString = "FoodCategoryInDTO{restaurantId=1, foodCategoryName='Appetizers'}";
    assertEquals(expectedString, dto.toString());
  }

  // Helper method to create a long string for testing
  private String repeatString(String str, int times) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < times; i++) {
      sb.append(str);
    }
    return sb.toString();
  }
}

