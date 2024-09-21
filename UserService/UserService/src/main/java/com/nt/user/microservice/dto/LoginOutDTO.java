package com.nt.user.microservice.dto;

import java.util.Objects;

public class LoginOutDTO {
  private Integer id;
  private String role;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true; }
    if (!(o instanceof LoginOutDTO)) {
      return false; }
    LoginOutDTO that = (LoginOutDTO) o;
    return Objects.equals(id, that.id) && Objects.equals(role, that.role);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, role);
  }

  @Override
  public String toString() {
    return "LoginOutDTO{" +
      "id=" + id +
      ", role='" + role + '\'' +
      '}';
  }
}
