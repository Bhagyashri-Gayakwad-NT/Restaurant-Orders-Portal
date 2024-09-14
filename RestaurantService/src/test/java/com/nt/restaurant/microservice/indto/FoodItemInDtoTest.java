package com.nt.restaurant.microservice.indto;

import com.nt.restaurant.microservice.dto.FoodItemInDTO;
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

    MultipartFile image = null;
    FoodItemInDTO dto = new FoodItemInDTO(1, 1, "Burger", "Delicious beef burger", 5.99, true, image);

    Set<ConstraintViolation<FoodItemInDTO>> violations = validator.validate(dto);

    assertTrue(violations.isEmpty(), "There should be no violations for a valid DTO");
  }

  @Test
  void testFoodCategoryIdCannotBeNull() {

    FoodItemInDTO dto = new FoodItemInDTO(null, 1, "Burger", "Delicious beef burger", 5.99, true, null);

    Set<ConstraintViolation<FoodItemInDTO>> violations = validator.validate(dto);

    assertFalse(violations.isEmpty(), "Food category ID cannot be null");
    assertEquals("Food category ID cannot be null", violations.iterator().next().getMessage());
  }

  @Test
  void testRestaurantIdCannotBeNull() {

    FoodItemInDTO dto = new FoodItemInDTO(1, null, "Burger", "Delicious beef burger", 5.99, true, null);

    Set<ConstraintViolation<FoodItemInDTO>> violations = validator.validate(dto);

    assertFalse(violations.isEmpty(), "Restaurant ID cannot be null");
    assertEquals("Restaurant ID cannot be null", violations.iterator().next().getMessage());
  }

  @Test
  void testFoodItemNameMustContainOnlyAlphabets() {
    FoodItemInDTO dto = new FoodItemInDTO(1, 1, "Burger123", "Delicious beef burger", 5.99, true, null);

    Set<ConstraintViolation<FoodItemInDTO>> violations = validator.validate(dto);

    assertFalse(violations.isEmpty(), "Expected validation error for food item name pattern");
    assertEquals("Food Item name must contain only alphabets", violations.iterator().next().getMessage());
  }
  @Test
  void testDescriptionCannotContainLeadingOrTrailingSpaces() {
    MockMultipartFile file = new MockMultipartFile("image", "test.png", "image/png", "some image".getBytes());
    FoodItemInDTO dto = new FoodItemInDTO(1, 1, "Burger", "  Description with spaces  ",
      5.99, true, file); // Description with leading/trailing spaces

    Set<ConstraintViolation<FoodItemInDTO>> violations = validator.validate(dto);

    assertFalse(violations.isEmpty());
    assertEquals("Description cannot contain leading or trailing spaces", violations.iterator().next().getMessage());
  }



  @Test
  void testPriceCannotBeNull() {

    FoodItemInDTO dto = new FoodItemInDTO(1, 1, "Burger", "Delicious beef burger", null, true, null);

    Set<ConstraintViolation<FoodItemInDTO>> violations = validator.validate(dto);

    assertFalse(violations.isEmpty(), "Price cannot be null");
    assertEquals("Price cannot be null", violations.iterator().next().getMessage());
  }

  @Test
  void testPriceMustBeGreaterThanZero() {

    FoodItemInDTO dto = new FoodItemInDTO(1, 1, "Burger", "Delicious beef burger", 0.0, true, null);

    Set<ConstraintViolation<FoodItemInDTO>> violations = validator.validate(dto);

    assertFalse(violations.isEmpty(), "Price must be greater than 0");
    assertEquals("Price must be greater than 0", violations.iterator().next().getMessage());
  }

  @Test
  void testPriceFormatIsValid() {

    FoodItemInDTO dto = new FoodItemInDTO(1, 1, "Burger", "Delicious beef burger", 5.999, true, null);

    Set<ConstraintViolation<FoodItemInDTO>> violations = validator.validate(dto);

    assertFalse(violations.isEmpty(), "Price format is invalid");
    assertEquals("Price format is invalid", violations.iterator().next().getMessage());
  }

  @Test
  void testPriceFormatIsInvalid() {
    FoodItemInDTO dto = new FoodItemInDTO(1, 1, "Burger", "Delicious beef burger", 1234567890.123, true, null);

    Set<ConstraintViolation<FoodItemInDTO>> violations = validator.validate(dto);

    assertFalse(violations.isEmpty(), "Expected validation error for price format");
    assertEquals("Price format is invalid", violations.iterator().next().getMessage());
  }

  @Test
  void testFoodItemInDTOEquality() {
    MultipartFile image = null;
    FoodItemInDTO dto1 = new FoodItemInDTO(1, 1, "Burger", "Delicious beef burger", 5.99, true, image);
    FoodItemInDTO dto2 = new FoodItemInDTO(1, 1, "Burger", "Delicious beef burger", 5.99, true, image);

    assertEquals(dto1, dto2, "DTO objects with the same data should be equal");
  }

  @Test
  void testFoodItemInDTOInequality() {
    MultipartFile image = null;
    FoodItemInDTO dto1 = new FoodItemInDTO(1, 1, "Burger", "Delicious beef burger", 5.99, true, image);
    FoodItemInDTO dto2 = new FoodItemInDTO(1, 2, "Pizza", "Cheesy pizza", 7.99, true, image);

    assertNotEquals(dto1, dto2, "DTO objects with different data should not be equal");
  }

  @Test
  void testToStringMethod() {
    MultipartFile image = null;
    FoodItemInDTO dto = new FoodItemInDTO(1, 1, "Burger", "Delicious beef burger", 5.99, true, image);

    String expectedString = "FoodItemInDTO{foodCategoryId=1, restaurantId=1, foodItemName='Burger'," +
      " description='Delicious beef burger'," +
      " price=5.99, isAvailable=true, foodItemImage=null}";
    assertEquals(expectedString, dto.toString(), "toString method output is incorrect");
  }
}



