package com.nt.restaurant.microservice.indto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FoodItemInDtoTest {

  private Validator validator;

  @BeforeEach
  void setUp() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  void testValidFoodItemInDTO() {
    // Given
    MockMultipartFile file = new MockMultipartFile("image", "test.png", "image/png", "some image".getBytes());
    FoodItemInDTO dto = new FoodItemInDTO(1, 1, "Burger", "Delicious beef burger", 5.99, true, file);

    // When
    Set<ConstraintViolation<FoodItemInDTO>> violations = validator.validate(dto);

    // Then
    assertTrue(violations.isEmpty());
  }

//  @Test
//  void testFoodCategoryIdCannotBeNull() {
//    // Given
//    MockMultipartFile file = new MockMultipartFile("image", "test.png", "image/png", "some image".getBytes());
//    FoodItemInDTO dto = new FoodItemInDTO(null, 1, "Burger", "Delicious beef burger", 5.99, true, file);
//
//    // When
//    Set<ConstraintViolation<FoodItemInDTO>> violations = validator.validate(dto);
//
//    // Then
//    assertFalse(violations.isEmpty());
//    assertEquals("Food category ID cannot be null", violations.iterator().next().getMessage());
//  }

  @Test
  void testRestaurantIdCannotBeNull() {
    // Given
    MockMultipartFile file = new MockMultipartFile("image", "test.png", "image/png", "some image".getBytes());
    FoodItemInDTO dto = new FoodItemInDTO(1, null, "Burger", "Delicious beef burger", 5.99, true, file);

    // When
    Set<ConstraintViolation<FoodItemInDTO>> violations = validator.validate(dto);

    // Then
    assertFalse(violations.isEmpty());
    assertEquals("Restaurant ID cannot be null", violations.iterator().next().getMessage());
  }

//  @Test
//  void testFoodItemNameCannotBeBlank() {
//    // Given
//    MockMultipartFile file = new MockMultipartFile("image", "test.png", "image/png", "some image".getBytes());
//    FoodItemInDTO dto = new FoodItemInDTO(1, 1, "", "Delicious beef burger", 5.99, true, file);
//
//    // When
//    Set<ConstraintViolation<FoodItemInDTO>> violations = validator.validate(dto);
//
//    // Then
//    assertFalse(violations.isEmpty());
//    assertEquals("Food item name cannot be blank", violations.iterator().next().getMessage());
//  }

  @Test
  void testDescriptionCannotBeBlank() {
    // Given
    MockMultipartFile file = new MockMultipartFile("image", "test.png", "image/png", "some image".getBytes());
    FoodItemInDTO dto = new FoodItemInDTO(1, 1, "Burger", "", 5.99, true, file);

    // When
    Set<ConstraintViolation<FoodItemInDTO>> violations = validator.validate(dto);

    // Then
    assertFalse(violations.isEmpty());
    assertEquals("Description cannot be blank", violations.iterator().next().getMessage());
  }

  @Test
  void testPriceMustBeGreaterThanZero() {
    // Given
    MockMultipartFile file = new MockMultipartFile("image", "test.png", "image/png", "some image".getBytes());
    FoodItemInDTO dto = new FoodItemInDTO(1, 1, "Burger", "Delicious beef burger", -1.0, true, file);

    // When
    Set<ConstraintViolation<FoodItemInDTO>> violations = validator.validate(dto);

    // Then
    assertFalse(violations.isEmpty());
    assertEquals("Price must be greater than 0", violations.iterator().next().getMessage());
  }

//  @Test
//  void testFoodItemImageCannotBeNull() {
//    // Given
//    FoodItemInDTO dto = new FoodItemInDTO(1, 1, "Burger", "Delicious beef burger", 5.99, true, null);
//
//    // When
//    Set<ConstraintViolation<FoodItemInDTO>> violations = validator.validate(dto);
//
//    // Then
//    assertFalse(violations.isEmpty());
//    assertEquals("Food item image cannot be null", violations.iterator().next().getMessage());
//  }

  @Test
  void testInvalidPriceFormat() {
    // Given
    MockMultipartFile file = new MockMultipartFile("image", "test.png", "image/png", "some image".getBytes());
    FoodItemInDTO dto = new FoodItemInDTO(1, 1, "Burger", "Delicious beef burger", 1234567890.123, true, file);

    // When
    Set<ConstraintViolation<FoodItemInDTO>> violations = validator.validate(dto);

    // Then
    assertFalse(violations.isEmpty());
    assertEquals("Price format is invalid", violations.iterator().next().getMessage());
  }
}
