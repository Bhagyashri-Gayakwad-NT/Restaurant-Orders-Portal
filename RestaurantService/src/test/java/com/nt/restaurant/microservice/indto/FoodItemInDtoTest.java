package com.nt.restaurant.microservice.indto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class FoodItemInDTOTest {

  private Validator validator;

  @BeforeEach
  void setUp() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  void testValidFoodItemInDTO() {
    // Given
    MultipartFile image = null; // This field is optional, so it can be null
    FoodItemInDTO dto = new FoodItemInDTO(1, 1, "Burger", "Delicious beef burger", 5.99, true, image);

    // When
    Set<ConstraintViolation<FoodItemInDTO>> violations = validator.validate(dto);

    // Then
    assertTrue(violations.isEmpty(), "There should be no violations for a valid DTO");
  }

  @Test
  void testFoodCategoryIdCannotBeNull() {
    // Given
    FoodItemInDTO dto = new FoodItemInDTO(null, 1, "Burger", "Delicious beef burger", 5.99, true, null);

    // When
    Set<ConstraintViolation<FoodItemInDTO>> violations = validator.validate(dto);

    // Then
    assertFalse(violations.isEmpty(), "Food category ID cannot be null");
    assertEquals("Food category ID cannot be null", violations.iterator().next().getMessage());
  }

  @Test
  void testRestaurantIdCannotBeNull() {
    // Given
    FoodItemInDTO dto = new FoodItemInDTO(1, null, "Burger", "Delicious beef burger", 5.99, true, null);

    // When
    Set<ConstraintViolation<FoodItemInDTO>> violations = validator.validate(dto);

    // Then
    assertFalse(violations.isEmpty(), "Restaurant ID cannot be null");
    assertEquals("Restaurant ID cannot be null", violations.iterator().next().getMessage());
  }

  @Test
  void testFoodItemNameCannotBeBlank() {
    // Given
    FoodItemInDTO dto = new FoodItemInDTO(1, 1, "", "Delicious beef burger", 5.99, true, null);

    // When
    Set<ConstraintViolation<FoodItemInDTO>> violations = validator.validate(dto);

    // Then
    assertFalse(violations.isEmpty(), "Food item name cannot be blank");
    assertEquals("Food Item name must contain only alphabets", violations.iterator().next().getMessage());
  }

//  @Test
//  void testFoodItemNameCannotExceedMaxLength() {
//    // Given
//    String longName = "A".repeat(101); // 101 characters, exceeds the max of 100
//    FoodItemInDTO dto = new FoodItemInDTO(1, 1, longName, "Delicious beef burger", 5.99, true, null);
//
//    // When
//    Set<ConstraintViolation<FoodItemInDTO>> violations = validator.validate(dto);
//
//    // Then
//    assertFalse(violations.isEmpty(), "Food item name cannot exceed 100 characters");
//    assertEquals("Food item name cannot exceed 100 characters", violations.iterator().next().getMessage());
//  }

  // Test for NotBlank constraint
  @Test
  void testDescriptionCannotBeBlank() {
    // Given
    MockMultipartFile file = new MockMultipartFile("image", "test.png", "image/png", "some image".getBytes());
    FoodItemInDTO dto = new FoodItemInDTO(1, 1, "Burger", "", 5.99, true, file); // Blank description

    // When
    Set<ConstraintViolation<FoodItemInDTO>> violations = validator.validate(dto);

    // Then
    assertFalse(violations.isEmpty());
    assertEquals("Description cannot contain leading or trailing spaces", violations.iterator().next().getMessage());
  }

  // Test for leading/trailing space validation
  @Test
  void testDescriptionCannotContainLeadingOrTrailingSpaces() {
    // Given
    MockMultipartFile file = new MockMultipartFile("image", "test.png", "image/png", "some image".getBytes());
    FoodItemInDTO dto = new FoodItemInDTO(1, 1, "Burger", "   Invalid description   ", 5.99, true, file); // Leading and trailing spaces

    // When
    Set<ConstraintViolation<FoodItemInDTO>> violations = validator.validate(dto);

    // Then
    assertFalse(violations.isEmpty());
    assertEquals("Description cannot contain leading or trailing spaces", violations.iterator().next().getMessage());
  }


  @Test
  void testPriceCannotBeNull() {
    // Given
    FoodItemInDTO dto = new FoodItemInDTO(1, 1, "Burger", "Delicious beef burger", null, true, null);

    // When
    Set<ConstraintViolation<FoodItemInDTO>> violations = validator.validate(dto);

    // Then
    assertFalse(violations.isEmpty(), "Price cannot be null");
    assertEquals("Price cannot be null", violations.iterator().next().getMessage());
  }

  @Test
  void testPriceMustBeGreaterThanZero() {
    // Given
    FoodItemInDTO dto = new FoodItemInDTO(1, 1, "Burger", "Delicious beef burger", 0.0, true, null);

    // When
    Set<ConstraintViolation<FoodItemInDTO>> violations = validator.validate(dto);

    // Then
    assertFalse(violations.isEmpty(), "Price must be greater than 0");
    assertEquals("Price must be greater than 0", violations.iterator().next().getMessage());
  }

  @Test
  void testPriceFormatIsValid() {
    // Given
    FoodItemInDTO dto = new FoodItemInDTO(1, 1, "Burger", "Delicious beef burger", 5.999, true, null);

    // When
    Set<ConstraintViolation<FoodItemInDTO>> violations = validator.validate(dto);

    // Then
    assertFalse(violations.isEmpty(), "Price format is invalid");
    assertEquals("Price format is invalid", violations.iterator().next().getMessage());
  }
}

