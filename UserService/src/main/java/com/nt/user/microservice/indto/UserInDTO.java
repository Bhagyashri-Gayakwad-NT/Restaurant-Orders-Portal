package com.nt.user.microservice.indto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserInDTO {
  @NotBlank(message = "Last name is required")
  @Size(min = 2, max = 30, message = "First name must be between 2 and 30 characters")
  private String firstName;
  @NotBlank(message = "Last name is required")
  @Size(min = 2, max = 30, message = "First name must be between 2 and 30 characters")
  private String lastName;
  @NotBlank(message = "Email is required")
  @Email(message = "Email should be valid")
  private String email;
  @NotBlank(message = "Password is required")
  @Size(min = 4, message = "Password must be at least 4 characters long")
  private String password;
  @NotBlank(message = "Phone number is required")
  @Pattern(regexp = "^\\d{10}$", message = "Phone number must be 10 digits")
  private String phoneNo;
  @NotBlank(message = "Role is required")
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
}



