package com.nt.order.microservice.dtos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class AmountInDTOTest {

  private Validator validator;

  @BeforeEach
  public void setUp() {
    // Create a Validator object using ValidatorFactory
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  public void testBalanceNotNullConstraintViolation() {
    // Arrange
    AmountInDTO amountInDTO = new AmountInDTO();
    amountInDTO.setBalance(null);  // Intentionally setting balance to null

    // Act
    Set<ConstraintViolation<AmountInDTO>> violations = validator.validate(amountInDTO);

    // Assert
    assertFalse(violations.isEmpty());  // Ensure that validation fails
    assertEquals(1, violations.size()); // Only one violation should be found
    assertEquals("Balance cannot be null", violations.iterator().next().getMessage());
  }

  @Test
  public void testValidBalance() {
    // Arrange
    AmountInDTO amountInDTO = new AmountInDTO();
    amountInDTO.setBalance(100.0);  // Set a valid balance (not null)

    // Act
    Set<ConstraintViolation<AmountInDTO>> violations = validator.validate(amountInDTO);

    // Assert
    assertTrue(violations.isEmpty());  // Ensure no validation violations
  }

  @Test
  public void testSetAndGetBalance() {
    // Arrange
    AmountInDTO amountInDTO = new AmountInDTO();
    Double testBalance = 150.50;

    // Act
    amountInDTO.setBalance(testBalance);
    Double balance = amountInDTO.getBalance();

    // Assert
    assertEquals(testBalance, balance);  // Verify the getter/setter works correctly
  }
}
