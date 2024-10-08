package com.nt.user.microservice.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * DTO class for email request with subject and text.
 * This class includes validations for the subject and text fields.
 */
public class EmailRequestDTO {

  /**
   * The subject of the email.
   * This field cannot be null or blank, and its length must be between 1 and 100 characters.
   */
  @NotBlank(message = "Subject is required.")
  @Size(min = 3, max = 100, message = "Subject must be between 3 and 100 characters.")
  private String subject;

  /**
   * The body of the email.
   * This field cannot be null or blank, and its length must be between 1 and 500 characters.
   */
  @NotBlank(message = "Text is required.")
  @Size(min = 5, max = 500, message = "Text must be between 5 and 500 characters.")
  private String text;

  /**
   * Gets the subject of the email.
   *
   * @return the subject of the email
   */
  public String getSubject() {
    return subject;
  }

  /**
   * Sets the subject of the email.
   *
   * @param subject the new subject to be set
   */
  public void setSubject(final String subject) {
    this.subject = subject;
  }

  /**
   * Gets the body of the email.
   *
   * @return the body of the email
   */
  public String getText() {
    return text;
  }

  /**
   * Sets the body of the email.
   *
   * @param text the new text to be set as the body of the email
   */
  public void setText(final String text) {
    this.text = text;
  }

  /**
   * Compares this email request DTO to the specified object.
   * The result is {@code true} if and only if the argument is not {@code null} and is an
   * {@code EmailRequestDTO} object that represents the same subject and text as this object.
   *
   * @param o the object to compare this {@code EmailRequestDTO} against
   * @return {@code true} if the given object represents an {@code EmailRequestDTO}
   * equivalent to this DTO, {@code false} otherwise
   */
  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof EmailRequestDTO)) {
      return false;
    }
    EmailRequestDTO that = (EmailRequestDTO) o;
    return Objects.equals(subject, that.subject) && Objects.equals(text, that.text);
  }

  /**
   * Returns a hash code for this email request DTO.
   * The hash code is generated based on the subject and text fields.
   *
   * @return a hash code value for this DTO
   */
  @Override
  public int hashCode() {
    return Objects.hash(subject, text);
  }

  /**
   * Returns a string representation of this email request DTO.
   * The string includes the subject and text fields of the DTO.
   *
   * @return a string representation of the object
   */
  @Override
  public String toString() {
    return "EmailRequestDTO{"
      + "subject='" + subject
      + '\'' + ", text='" + text
      + '\'' + '}';
  }
}
