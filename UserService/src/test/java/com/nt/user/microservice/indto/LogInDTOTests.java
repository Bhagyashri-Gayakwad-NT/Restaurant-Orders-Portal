package com.nt.user.microservice.indto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogInDTOTests {

  private Validator validator;

  @BeforeEach
  public void setUp() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  public void testValidLogInDTO() {
    LogInDTO dto = new LogInDTO();
    dto.setEmail("user@nucleusteq.com");
    dto.setPassword("Password1");

    Set<ConstraintViolation<LogInDTO>> violations = validator.validate(dto);
    assertTrue(violations.isEmpty(), "Expected no validation errors for valid DTO");
  }

  @Test
  public void testInvalidEmailFormat() {
    LogInDTO dto = new LogInDTO();
    dto.setEmail("user@domain.com");
    dto.setPassword("Password1");

    Set<ConstraintViolation<LogInDTO>> violations = validator.validate(dto);
    assertEquals(1, violations.size(), "Expected one validation error for email format");
    ConstraintViolation<LogInDTO> violation = violations.iterator().next();
    assertEquals("Email must end with @nucleusteq.com", violation.getMessage());
  }

  @Test
  public void testInvalidPasswordFormat() {
    LogInDTO dto = new LogInDTO();
    dto.setEmail("user@nucleusteq.com");
    dto.setPassword("pass");

    Set<ConstraintViolation<LogInDTO>> violations = validator.validate(dto);
    assertEquals(1, violations.size(), "Expected one validation error for password format");
    ConstraintViolation<LogInDTO> violation = violations.iterator().next();
    assertEquals("Password must contain at least 4 characters and 1 number", violation.getMessage());
  }

  @Test
  public void testEmptyEmail() {
    LogInDTO dto = new LogInDTO();
    dto.setEmail("");
    dto.setPassword("Password1");

    Set<ConstraintViolation<LogInDTO>> violations = validator.validate(dto);
    assertEquals(1, violations.size(), "Expected one validation error for empty email");
    ConstraintViolation<LogInDTO> violation = violations.iterator().next();
    assertEquals("Email is required", violation.getMessage());
  }

  @Test
  public void testEmptyPassword() {
    LogInDTO dto = new LogInDTO();
    dto.setEmail("user@nucleusteq.com");
    dto.setPassword("");

    Set<ConstraintViolation<LogInDTO>> violations = validator.validate(dto);
    assertEquals(1, violations.size(), "Expected one validation error for empty password");
    ConstraintViolation<LogInDTO> violation = violations.iterator().next();
    assertEquals("Password is required", violation.getMessage());
  }

  @Test
  public void testPasswordTooShort() {
    LogInDTO dto = new LogInDTO();
    dto.setEmail("user@nucleusteq.com");
    dto.setPassword("Pwd1");

    Set<ConstraintViolation<LogInDTO>> violations = validator.validate(dto);
    assertEquals(1, violations.size(), "Expected one validation error for short password");
    ConstraintViolation<LogInDTO> violation = violations.iterator().next();
    assertEquals("Password must be at least 4 characters long and 1 number", violation.getMessage());
  }

  @Test
  public void testPasswordMissingNumber() {
    LogInDTO dto = new LogInDTO();
    dto.setEmail("user@nucleusteq.com");
    dto.setPassword("Password");

    Set<ConstraintViolation<LogInDTO>> violations = validator.validate(dto);
    assertEquals(1, violations.size(), "Expected one validation error for password missing a number");
    ConstraintViolation<LogInDTO> violation = violations.iterator().next();
    assertEquals("Password must contain at least 4 characters and 1 number", violation.getMessage());
  }

  @Test
  public void testGettersAndSetters() {
    LogInDTO dto = new LogInDTO();
    dto.setEmail("valid@nucleusteq.com");
    dto.setPassword("Valid1Password");

    assertEquals("valid@nucleusteq.com", dto.getEmail(), "Email getter/setter should work");
    assertEquals("Valid1Password", dto.getPassword(), "Password getter/setter should work");
  }
}
