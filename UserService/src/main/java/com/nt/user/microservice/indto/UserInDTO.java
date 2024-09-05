package com.nt.user.microservice.indto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Objects;

/**
 * Data Transfer Object (DTO) for user registration input.
 * This DTO is used to capture user details during registration.
 */
public class UserInDTO {

  /**
   * First name of the user.
   * It must start with a capital letter and contain only alphabetic characters.
   */
  @NotBlank(message = "First name is required")
  @Pattern(regexp = "^[A-Z][a-zA-Z]*$",
    message = "First name must start with a capital letter and contain only letters")
  private String firstName;

  /**
   * Last name of the user.
   * It must start with a capital letter and contain only alphabetic characters.
   */
  @NotBlank(message = "Last name is required")
  @Pattern(regexp = "^[A-Z][a-zA-Z]*$",
    message = "Last name must start with a capital letter and contain only letters")
  private String lastName;

  /**
   * Email address of the user.
   * It must be valid and should end with '@nucleusteq.com'.
   */
  @NotBlank(message = "Email is required")
  @Email(message = "Email should be valid")
  @Pattern(regexp = "^[a-zA-Z]+[a-zA-Z0-9._%+-]*@(nucleusteq\\.com)$",
    message = "Email must be valid, must end with @nucleusteq.com, and " +
      "contain at least one alphabet before the '@' symbol.")
  private String email;

  /**
   * Password of the user.
   * Cannot be blank.
   */
  @NotBlank(message = "Password is required")
  private String password;

  /**
   * Phone number of the user.
   * It must start with 9, 8, 7, or 6 and contain exactly 10 digits.
   */
  @NotBlank(message = "Phone number is required")
  @Pattern(regexp = "^[9876]\\d{9}$",
    message = "Phone number must start with 9, 8, 7, or 6 and contain 10 digits")
  private String phoneNo;

  /**
   * Role of the user.
   * Must be either 'USER' or 'RESTAURANT_OWNER'.
   */
  @NotBlank(message = "Role is required")
  @Pattern(regexp = "^(USER|RESTAURANT_OWNER)$",
    message = "Role must be either 'USER' or 'RESTAURANT_OWNER'")
  private String role;

  // Getters and setters

  /**
   * Gets the first name of the user.
   *
   * @return the first name of the user.
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Sets the first name of the user.
   *
   * @param firstName the first name to set.
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * Gets the last name of the user.
   *
   * @return the last name of the user.
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Sets the last name of the user.
   *
   * @param lastName the last name to set.
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

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

  /**
   * Gets the phone number of the user.
   *
   * @return the phone number of the user.
   */
  public String getPhoneNo() {
    return phoneNo;
  }

  /**
   * Sets the phone number of the user.
   *
   * @param phoneNo the phone number to set.
   */
  public void setPhoneNo(String phoneNo) {
    this.phoneNo = phoneNo;
  }

  /**
   * Gets the role of the user.
   *
   * @return the role of the user.
   */
  public String getRole() {
    return role;
  }

  /**
   * Sets the role of the user.
   *
   * @param role the role to set.
   */
  public void setRole(String role) {
    this.role = role;
  }

  // Override methods

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserInDTO userInDTO = (UserInDTO) o;
    return Objects.equals(firstName, userInDTO.firstName) && Objects.equals(lastName, userInDTO.lastName) &&
      Objects.equals(email, userInDTO.email) && Objects.equals(password, userInDTO.password) &&
      Objects.equals(phoneNo, userInDTO.phoneNo) && Objects.equals(role, userInDTO.role);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName, email, password, phoneNo, role);
  }

  @Override
  public String toString() {
    return "UserInDTO{" +
      "firstName='" + firstName + '\'' +
      ", lastName='" + lastName + '\'' +
      ", email='" + email + '\'' +
      ", password='" + password + '\'' +
      ", phoneNo='" + phoneNo + '\'' +
      ", role='" + role + '\'' +
      '}';
  }
}
