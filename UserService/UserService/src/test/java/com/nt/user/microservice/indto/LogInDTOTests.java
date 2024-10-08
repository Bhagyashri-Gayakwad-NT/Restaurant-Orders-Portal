package com.nt.user.microservice.indto;

import com.nt.user.microservice.dto.LogInDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogInDTOTests {

  private Validator validator;

  @BeforeEach
  void setUp() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  void testValidLogInDTO() {
    LogInDTO logInDTO = new LogInDTO();
    logInDTO.setEmail("user@nucleusteq.com");
    logInDTO.setPassword("password123");

    Set<ConstraintViolation<LogInDTO>> violations = validator.validate(logInDTO);

    assertTrue(violations.isEmpty(), "Expected no validation errors");
  }

  @Test
  void testEmailIsBlank() {
    LogInDTO logInDTO = new LogInDTO();
    logInDTO.setEmail("");
    logInDTO.setPassword("password123");

    Set<ConstraintViolation<LogInDTO>> violations = validator.validate(logInDTO);

    assertEquals(2, violations.size(), "Expected two validation errors");

    // Extract actual violation messages
    List<String> actualMessages = violations.stream()
      .map(ConstraintViolation::getMessage)
      .collect(Collectors.toList());

    assertTrue(actualMessages.contains("Email is required"), "Expected violation for blank email");
    assertTrue(actualMessages.contains("Email must be valid, must end with @nucleusteq.com, " +
        "and contain at least one alphabet before the '@' symbol."),
      "Expected violation for invalid email format");
  }


  @Test
  void testInvalidEmailFormat() {
    LogInDTO logInDTO = new LogInDTO();
    logInDTO.setEmail("invalid-email");
    logInDTO.setPassword("password123");

    Set<ConstraintViolation<LogInDTO>> violations = validator.validate(logInDTO);

    assertEquals(2, violations.size(), "Expected two validation errors");
    List<String> actualMessages = violations.stream()
      .map(ConstraintViolation::getMessage)
      .collect(Collectors.toList());
    assertTrue(actualMessages.contains("Email should be valid"), "Expected violation for invalid email");
    assertTrue(actualMessages.contains("Email must be valid, must end with @nucleusteq.com," +
        " and contain at least one alphabet before the '@' symbol."),
      "Expected violation for nucleusteq.com domain");
  }


  @Test
  void testEmailDoesNotEndWithNucleusTeqDomain() {
    LogInDTO logInDTO = new LogInDTO();
    logInDTO.setEmail("user@gmail.com");
    logInDTO.setPassword("password123");

    Set<ConstraintViolation<LogInDTO>> violations = validator.validate(logInDTO);

    assertEquals(1, violations.size(), "Expected one validation error");
    assertEquals("Email must be valid, must end with @nucleusteq.com, and contain at " +
      "least one alphabet before the '@' symbol.", violations.iterator().next().getMessage());
  }

  @Test
  void testPasswordIsBlank() {
    LogInDTO logInDTO = new LogInDTO();
    logInDTO.setEmail("user@nucleusteq.com");
    logInDTO.setPassword("");

    Set<ConstraintViolation<LogInDTO>> violations = validator.validate(logInDTO);

    assertEquals(1, violations.size(), "Expected one validation error");
    assertEquals("Password is required", violations.iterator().next().getMessage());
  }

  @Test
  void testPasswordNotBlank() {
    LogInDTO logInDTO = new LogInDTO();
    logInDTO.setEmail("user@nucleusteq.com");
    logInDTO.setPassword("ValidPass1!");

    Set<ConstraintViolation<LogInDTO>> violations = validator.validate(logInDTO);

    assertTrue(violations.isEmpty(), "Expected no validation errors");
  }

  @Test
  void testBothEmailAndPasswordAreBlank() {
    LogInDTO logInDTO = new LogInDTO();
    logInDTO.setEmail("");
    logInDTO.setPassword("");

    Set<ConstraintViolation<LogInDTO>> violations = validator.validate(logInDTO);

    assertEquals(3, violations.size(), "Expected two validation errors");
  }
}
