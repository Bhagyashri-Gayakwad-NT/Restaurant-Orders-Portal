package com.nt.user.microservice.indto;

import com.nt.user.microservice.dto.EmailRequestDTO;
import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class EmailRequestDTOTest {

  private final Validator validator;

  public EmailRequestDTOTest() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  private String repeatString(String str, int times) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < times; i++) {
      sb.append(str);
    }
    return sb.toString();
  }

  @Test
  void testValidEmailRequestDTO() {
    EmailRequestDTO emailRequest = new EmailRequestDTO();
    emailRequest.setSubject("Test Subject");
    emailRequest.setText("This is a test email body.");

    Set<?> violations = validator.validate(emailRequest);
    assertTrue(violations.isEmpty(), "Should be valid");
  }

  @Test
  void testInvalidEmailRequestDTOSubjectBlank() {
    EmailRequestDTO emailRequest = new EmailRequestDTO();
    emailRequest.setSubject(""); // invalid subject
    emailRequest.setText("This is a test email body.");

    Set<?> violations = validator.validate(emailRequest);
    assertFalse(violations.isEmpty(), "Should not be valid due to blank subject");
  }

  @Test
  void testInvalidEmailRequestDTOTextBlank() {
    EmailRequestDTO emailRequest = new EmailRequestDTO();
    emailRequest.setSubject("Test Subject");
    emailRequest.setText(""); // invalid text

    Set<?> violations = validator.validate(emailRequest);
    assertFalse(violations.isEmpty(), "Should not be valid due to blank text");
  }

  @Test
  void testSubjectLengthValidation() {
    EmailRequestDTO emailRequest = new EmailRequestDTO();
    emailRequest.setSubject("ABC"); // valid subject with 3 characters
    emailRequest.setText("This is a test email body.");

    Set<?> violations = validator.validate(emailRequest);
    assertTrue(violations.isEmpty(), "Should be valid");

    emailRequest.setSubject(repeatString("A", 101)); // invalid subject (exceeds max length of 100)
    violations = validator.validate(emailRequest);
    assertFalse(violations.isEmpty(), "Should not be valid due to subject length exceeding 100 characters");
  }


  @Test
  void testTextLengthValidation() {
    EmailRequestDTO emailRequest = new EmailRequestDTO();
    emailRequest.setSubject("Test Subject");
    emailRequest.setText("test5"); // valid text with 5 characters

    Set<?> violations = validator.validate(emailRequest);
    assertTrue(violations.isEmpty(), "Should be valid");

    emailRequest.setText(repeatString("A", 501)); // invalid text (exceeds max length)
    violations = validator.validate(emailRequest);
    assertFalse(violations.isEmpty(), "Should not be valid due to text length exceeding 500 characters");
  }


  @Test
  void testEquals() {
    EmailRequestDTO emailRequest1 = new EmailRequestDTO();
    emailRequest1.setSubject("Subject");
    emailRequest1.setText("Text");

    EmailRequestDTO emailRequest2 = new EmailRequestDTO();
    emailRequest2.setSubject("Subject");
    emailRequest2.setText("Text");

    assertEquals(emailRequest1, emailRequest2, "Should be equal with same subject and text");
    emailRequest2.setText("Different Text");
    assertNotEquals(emailRequest1, emailRequest2, "Should not be equal with different text");
  }

  @Test
  void testHashCode() {
    EmailRequestDTO emailRequest1 = new EmailRequestDTO();
    emailRequest1.setSubject("Subject");
    emailRequest1.setText("Text");

    EmailRequestDTO emailRequest2 = new EmailRequestDTO();
    emailRequest2.setSubject("Subject");
    emailRequest2.setText("Text");

    assertEquals(emailRequest1.hashCode(), emailRequest2.hashCode(), "Hash codes should be equal for same subject and text");
  }

  @Test
  void testToString() {
    EmailRequestDTO emailRequest = new EmailRequestDTO();
    emailRequest.setSubject("Subject");
    emailRequest.setText("Text");

    String expectedString = "EmailRequestDTO{subject='Subject', text='Text'}";
    assertEquals(expectedString, emailRequest.toString(), "toString should match expected format");
  }
}
