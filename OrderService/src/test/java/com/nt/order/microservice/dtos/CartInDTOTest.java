package com.nt.order.microservice.dtos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.*;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class CartInDTOTest {

  private Validator validator;

  @BeforeEach
  public void setUp() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  public void testValidCartInDTO() {
    CartInDTO cartInDTO = new CartInDTO(1, 1, 1, 2, 100.00);

    Set<ConstraintViolation<CartInDTO>> violations = validator.validate(cartInDTO);
    assertTrue(violations.isEmpty());
  }

  @Test
  public void testNullUserId() {
    CartInDTO cartInDTO = new CartInDTO(null, 1, 1, 2, 100.00);

    Set<ConstraintViolation<CartInDTO>> violations = validator.validate(cartInDTO);
    assertFalse(violations.isEmpty());
    assertEquals("User ID cannot be null", violations.iterator().next().getMessage());
  }

  @Test
  public void testNullRestaurantId() {
    CartInDTO cartInDTO = new CartInDTO(1, null, 1, 2, 100.00);

    Set<ConstraintViolation<CartInDTO>> violations = validator.validate(cartInDTO);
    assertFalse(violations.isEmpty());
    assertEquals("Restaurant ID cannot be null", violations.iterator().next().getMessage());
  }

  @Test
  public void testNullFoodItemId() {
    CartInDTO cartInDTO = new CartInDTO(1, 1, null, 2, 100.00);

    Set<ConstraintViolation<CartInDTO>> violations = validator.validate(cartInDTO);
    assertFalse(violations.isEmpty());
    assertEquals("Food Item ID cannot be null", violations.iterator().next().getMessage());
  }

  @Test
  public void testNullQuantity() {
    CartInDTO cartInDTO = new CartInDTO(1, 1, 1, null, 100.00);

    Set<ConstraintViolation<CartInDTO>> violations = validator.validate(cartInDTO);
    assertFalse(violations.isEmpty());
    assertEquals("Quantity cannot be null", violations.iterator().next().getMessage());
  }

  @Test
  public void testInvalidQuantity() {
    CartInDTO cartInDTO = new CartInDTO(1, 1, 1, 0, 100.00); // Invalid quantity

    Set<ConstraintViolation<CartInDTO>> violations = validator.validate(cartInDTO);
    assertFalse(violations.isEmpty());
    assertEquals("Quantity must be at least 1", violations.iterator().next().getMessage());
  }

  @Test
  public void testNullPrice() {
    CartInDTO cartInDTO = new CartInDTO(1, 1, 1, 2, null);

    Set<ConstraintViolation<CartInDTO>> violations = validator.validate(cartInDTO);
    assertFalse(violations.isEmpty());
    assertEquals("Price cannot be null", violations.iterator().next().getMessage());
  }

  @Test
  public void testInvalidPrice() {
    CartInDTO cartInDTO = new CartInDTO(1, 1, 1, 2, 0.00); // Invalid price

    Set<ConstraintViolation<CartInDTO>> violations = validator.validate(cartInDTO);
    assertFalse(violations.isEmpty());
    assertEquals("Price must be greater than 0", violations.iterator().next().getMessage());
  }

  @Test
  public void testInvalidPriceFormat() {
    CartInDTO cartInDTO = new CartInDTO(1, 1, 1, 2, 100.123); // Invalid format, more than 2 decimal places

    Set<ConstraintViolation<CartInDTO>> violations = validator.validate(cartInDTO);
    assertFalse(violations.isEmpty());
    assertEquals("Price format is invalid", violations.iterator().next().getMessage());
  }

  @Test
  public void testEqualityAndHashCode() {
    CartInDTO cart1 = new CartInDTO(1, 1, 1, 2, 100.00);
    CartInDTO cart2 = new CartInDTO(1, 1, 1, 2, 100.00);

    assertEquals(cart1, cart2);
    assertEquals(cart1.hashCode(), cart2.hashCode());
  }

  @Test
  public void testToString() {
    CartInDTO cartInDTO = new CartInDTO(1, 1, 1, 2, 100.00);
    String expectedString = "CartInDTO{userId=1, restaurantId=1, foodItemId=1, quantity=2, price=100.0}";
    assertEquals(expectedString, cartInDTO.toString());
  }
}
