package com.nt.user.microservice.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Objects;

/**
 * Data Transfer Object (DTO) for login input.
 * This DTO is used to capture the user's login credentials.
 */
public class LogInDTO {

  /**
   * The email address of the user.
   * Must be a valid email, contain at least one alphabetic character before the '@' symbol, and end with '@nucleusteq.com'.
   */
  @NotBlank(message = "Email is required")
  @Email(message = "Email should be valid")
  @Pattern(regexp = "^[a-zA-Z]+[a-zA-Z0-9._%+-]*@(nucleusteq\\.com)$",
    message = "Email must be valid, must end with @nucleusteq.com, and " +
      "contain at least one alphabet before the '@' symbol.")
  private String email;

  /**
   * The password of the user.
   * Cannot be blank.
   */
  @NotBlank(message = "Password is required")
  private String password;

  /**
   * Gets the email address of the user.
   *
   * @return the email address of the user.
   */
  public String getEmail() {
    return email;
  }

  /**
   * Sets the email address of the user.
   *
   * @param email the email address to set.
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Gets the password of the user.
   *
   * @return the password of the user.
   */
  public String getPassword() {
    return password;
  }

  /**
   * Sets the password of the user.
   *
   * @param password the password to set.
   */
  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof LogInDTO)) return false;
    LogInDTO logInDTO = (LogInDTO) o;
    return Objects.equals(email, logInDTO.email) && Objects.equals(password, logInDTO.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(email, password);
  }

  @Override
  public String toString() {
    return "LogInDTO{" +
      "email='" + email + '\'' +
      ", password='" + password + '\'' +
      '}';
  }
}
