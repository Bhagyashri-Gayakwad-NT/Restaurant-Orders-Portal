package com.nt.user.microservice.indto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
public class LogInDTO {

  @NotBlank(message = "Email is required")
  @Email(message = "Email should be valid")
  @Pattern(regexp = "^[A-Za-z0-9._%+-]+@nucleusteq\\.com$", message = "Email must end with @nucleusteq.com")
  private String email;

  @NotBlank(message = "Password is required")
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






