package com.nt.user.microservice.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Objects;


/**
 * Data Transfer Object (DTO) for updating a user's profile information.
 * Contains fields for the user's first name, last name, email, password, and phone number,
 * along with validation annotations to ensure correct input formats.
 */
public class ProfileUpdateDTO {

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
   * It must be a valid email format and should end with '@nucleusteq.com'.
   */
  @NotBlank(message = "Email is required")
  @Email(message = "Email should be valid")
  @Pattern(regexp = "^[a-zA-Z]+[a-zA-Z0-9._%+-]*@(nucleusteq\\.com)$",
    message = "Email must be valid, must end with @nucleusteq.com, and "
      + "contain at least one alphabet before the '@' symbol.")
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
   * Gets the first name of the user.
   * @return the first name
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Sets the first name of the user.
   * @param firstName the first name to set
   */
  public void setFirstName(final String firstName) {
    this.firstName = firstName;
  }

  /**
   * Gets the last name of the user.
   * @return the last name
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Sets the last name of the user.
   * @param lastName the last name to set
   */
  public void setLastName(final String lastName) {
    this.lastName = lastName;
  }

  /**
   * Gets the email address of the user.
   * @return the email
   */
  public String getEmail() {
    return email;
  }

  /**
   * Sets the email address of the user.
   * @param email the email to set
   */
  public void setEmail(final String email) {
    this.email = email;
  }

  /**
   * Gets the password of the user.
   * @return the password
   */
  public String getPassword() {
    return password;
  }

  /**
   * Sets the password of the user.
   * @param password the password to set
   */
  public void setPassword(final String password) {
    this.password = password;
  }

  /**
   * Gets the phone number of the user.
   * @return the phone number
   */
  public String getPhoneNo() {
    return phoneNo;
  }

  /**
   * Sets the phone number of the user.
   * @param phoneNo the phone number to set
   */
  public void setPhoneNo(final String phoneNo) {
    this.phoneNo = phoneNo;
  }

  /**
   * Compares this ProfileUpdateDTO object with another object for equality.
   * @param o the object to compare with
   * @return true if both objects are equal, false otherwise
   */
  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ProfileUpdateDTO)) {
      return false;
    }
    ProfileUpdateDTO that = (ProfileUpdateDTO) o;
    return Objects.equals(firstName, that.firstName)
      && Objects.equals(lastName, that.lastName)
      && Objects.equals(email, that.email)
      && Objects.equals(password, that.password)
      && Objects.equals(phoneNo, that.phoneNo);
  }

  /**
   * Generates a hash code for this ProfileUpdateDTO object.
   * @return the hash code
   */
  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName, email, password, phoneNo);
  }

  /**
   * Provides a string representation of the ProfileUpdateDTO object.
   * @return a string describing the ProfileUpdateDTO
   */
  @Override
  public String toString() {
    return "ProfileUpdateDTO{"
      + "firstName='" + firstName + '\''
      + ", lastName='" + lastName + '\''
      + ", email='" + email + '\''
      + ", password='" + password + '\''
      + ", phoneNo='" + phoneNo + '\''
      + '}';
  }
}
