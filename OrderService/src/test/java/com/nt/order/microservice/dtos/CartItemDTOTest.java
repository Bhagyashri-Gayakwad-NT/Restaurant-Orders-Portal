package com.nt.order.microservice.dtos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.ConstraintViolation;
import java.util.Set;

public class CartItemDTOTest {

  private Validator validator;

  public CartItemDTOTest() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  public void testDefaultConstructor() {
    CartItemDTO cartItemDTO = new CartItemDTO();
    assertNull(cartItemDTO.getFoodItemId());
    assertNull(cartItemDTO.getQuantity());
    assertNull(cartItemDTO.getPrice());
  }

  @Test
  public void testParameterizedConstructor() {
    Integer foodItemId = 1; // Dummy value
    Integer quantity = 2;    // Dummy value
    Double price = 10.0;     // Dummy value

    CartItemDTO cartItemDTO = new CartItemDTO(foodItemId, quantity, price);
    assertEquals(foodItemId, cartItemDTO.getFoodItemId());
    assertEquals(quantity, cartItemDTO.getQuantity());
    assertEquals(price, cartItemDTO.getPrice());
  }

  @Test
  public void testSettersAndGetters() {
    CartItemDTO cartItemDTO = new CartItemDTO();

    Integer foodItemId = 1; // Dummy value
    cartItemDTO.setFoodItemId(foodItemId);
    assertEquals(foodItemId, cartItemDTO.getFoodItemId());

    Integer quantity = 2; // Dummy value
    cartItemDTO.setQuantity(quantity);
    assertEquals(quantity, cartItemDTO.getQuantity());

    Double price = 10.0; // Dummy value
    cartItemDTO.setPrice(price);
    assertEquals(price, cartItemDTO.getPrice());
  }

  @Test
  public void testValidation() {
    CartItemDTO cartItemDTO = new CartItemDTO();

    Set<ConstraintViolation<CartItemDTO>> violations = validator.validate(cartItemDTO);
    assertEquals(3, violations.size());

    cartItemDTO.setFoodItemId(1); // Valid
    violations = validator.validate(cartItemDTO);
    assertEquals(2, violations.size());

    cartItemDTO.setQuantity(0); // Invalid
    violations = validator.validate(cartItemDTO);
    assertEquals(2, violations.size());

    cartItemDTO.setQuantity(1); // Valid
    cartItemDTO.setPrice(0.0); // Invalid
    violations = validator.validate(cartItemDTO);
    assertEquals(1, violations.size());

    cartItemDTO.setPrice(10.0); // Valid
    violations = validator.validate(cartItemDTO);
    assertEquals(0, violations.size());
  }
}
