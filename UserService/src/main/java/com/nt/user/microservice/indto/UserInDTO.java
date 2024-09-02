package com.nt.user.microservice.indto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Objects;

public class UserInDTO {
  @NotBlank(message = "First name is required")
  @Pattern(regexp = "^[A-Z][a-zA-Z]{2,}$",
    message = "First name must start with a capital letter," +
      " have no spaces, digits, or special characters, and be at least 3 characters long")
  private String firstName;

  @NotBlank(message = "Last name is required")
  @Pattern(regexp = "^[A-Z][a-zA-Z]{2,}$",
    message = "Last name must start with a capital letter," +
      " have no spaces, digits, or special characters, and be at least 3 characters long")
  private String lastName;

//  @NotBlank(message = "Email is required")
  @Email(message = "Email should be valid")
  @Pattern(regexp = "^[A-Za-z0-9._%+-]+@nucleusteq\\.com$", message = "Email is required and Email must end with @nucleusteq.com")
  private String email;

  @NotBlank(message = "Password is required")
  private String password;

  @NotBlank(message = "Phone number is required")
  @Pattern(regexp = "^[9876]\\d{9}$", message = "Phone number must start with 9, 8, 7, or 6 and contain 10 digits")
  private String phoneNo;


//  @NotBlank(message = "Role is required")
  @Pattern(regexp = "^(USER|RESTAURANT_OWNER)$", message = "Role must be either 'USER' or 'RESTAURANT_OWNER'")
  private String role;

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPhoneNo() {
    return phoneNo;
  }

  public void setPhoneNo(String phoneNo) {
    this.phoneNo = phoneNo;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
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



