package com.nt.user.microservice.indto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class LogInDTO {

  @NotBlank(message = "Email is required")
  @Email(message = "Email should be valid")
  @Pattern(regexp = "^[A-Za-z0-9._%+-]+@nucleusteq\\.com$", message = "Email is required and Email must end with @nucleusteq.com")
  private String email;

  @NotBlank(message = "Password is required")
  @Size(min = 4, message = "Password must be at least 4 characters long and 1 number ")
  @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z]).{4,}$", message = "Password must contain at least 4 characters and 1 number")
  private String password;

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
}






