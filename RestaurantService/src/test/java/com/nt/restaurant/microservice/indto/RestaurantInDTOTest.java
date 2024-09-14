package com.nt.restaurant.microservice.indto;

import com.nt.restaurant.microservice.dto.RestaurantInDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RestaurantInDTOTest {

  private Validator validator;

  @BeforeEach
  public void setUp() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  public void testValidRestaurantInDTO() {
    RestaurantInDTO dto = new RestaurantInDTO(
      1,
      "Restaurant Name",
      "123 Street, City, Country",
      "9876543210",
      "A great place to eat.",
      null // Assuming no image is provided for now
    );

    Set<ConstraintViolation<RestaurantInDTO>> violations = validator.validate(dto);
    assertTrue(violations.isEmpty(), "Expected no validation violations");
  }

  @Test
  public void testNullUserId() {
    RestaurantInDTO dto = new RestaurantInDTO(
      null,
      "Restaurant Name",
      "123 Street, City, Country",
      "9876543210",
      "A great place to eat.",
      null
    );

    Set<ConstraintViolation<RestaurantInDTO>> violations = validator.validate(dto);
    assertEquals(1, violations.size());
    assertEquals("User ID cannot be null", violations.iterator().next().getMessage());
  }

  @Test
  public void testBlankRestaurantName() {
    RestaurantInDTO dto = new RestaurantInDTO(
      1,
      "",
      "123 Street, City, Country",
      "9876543210",
      "A great place to eat.",
      null
    );

    Set<ConstraintViolation<RestaurantInDTO>> violations = validator.validate(dto);
    assertEquals(2, violations.size());
    assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Restaurant name cannot be blank")));
    assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Restaurant name must contain only alphabets")));
  }

  @Test
  public void testRestaurantNameWithInvalidCharacters() {
    RestaurantInDTO dto = new RestaurantInDTO(
      1,
      "Restaurant123",
      "123 Street, City, Country",
      "9876543210",
      "A great place to eat.",
      null
    );

    Set<ConstraintViolation<RestaurantInDTO>> violations = validator.validate(dto);
    assertEquals(1, violations.size());
    assertEquals("Restaurant name must contain only alphabets", violations.iterator().next().getMessage());
  }

  @Test
  public void testBlankDescription() {
    RestaurantInDTO dto = new RestaurantInDTO(
      1,
      "Restaurant Name",
      "123 Street, City, Country",
      "9876543210",
      "",
      null
    );

    Set<ConstraintViolation<RestaurantInDTO>> violations = validator.validate(dto);
    assertEquals(2, violations.size());
    assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Description cannot be blank")));
    assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Description cannot contain leading or trailing spaces")));
  }

  @Test
  public void testDescriptionExceedsMaxLength() {
    RestaurantInDTO dto = new RestaurantInDTO(
      1,
      "Restaurant Name",
      "123 Street, City, Country",
      "9876543210",
      repeat("A", 256),
      null
    );

    Set<ConstraintViolation<RestaurantInDTO>> violations = validator.validate(dto);
    assertEquals(1, violations.size());
    assertEquals("Description cannot exceed 255 characters", violations.iterator().next().getMessage());
  }

  @Test
  public void testInvalidContactNumber() {
    RestaurantInDTO dto = new RestaurantInDTO(
      1,
      "Restaurant Name",
      "123 Street, City, Country",
      "1234567890", // Invalid contact number
      "A great place to eat.",
      null
    );

    Set<ConstraintViolation<RestaurantInDTO>> violations = validator.validate(dto);
    assertEquals(1, violations.size());
    assertEquals("Phone number must start with 9, 8, 7, or 6 and contain 10 digits", violations.iterator().next().getMessage());
  }

  @Test
  public void testAddressCannotBeBlank() {
    RestaurantInDTO dto = new RestaurantInDTO(
      1,
      "Restaurant Name",
      "  ", // Invalid blank address
      "9876543210",
      "A great place to eat.",
      null
    );

    Set<ConstraintViolation<RestaurantInDTO>> violations = validator.validate(dto);

    assertEquals(2, violations.size());
    assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Address cannot contain leading or trailing spaces")));
  }

  @Test
  public void testAddressCannotContainLeadingOrTrailingSpaces() {
    RestaurantInDTO dto = new RestaurantInDTO(
      1,
      "Restaurant Name",
      "  Address with spaces  ",
      "9876543210",
      "A great place to eat.",
      null
    );
    Set<ConstraintViolation<RestaurantInDTO>> violations = validator.validate(dto);
    assertEquals(1, violations.size());
    assertEquals("Address cannot contain leading or trailing spaces", violations.iterator().next().getMessage());
  }

  public static String repeat(String str, int count) {
    StringBuilder repeatedString = new StringBuilder();
    for (int i = 0; i < count; i++) {
      repeatedString.append(str);
    }
    return repeatedString.toString();
  }
}
