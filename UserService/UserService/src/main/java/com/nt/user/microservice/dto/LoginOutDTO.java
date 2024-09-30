package com.nt.user.microservice.dto;

import java.util.Objects;

/**
 * Data Transfer Object (DTO) for representing the login output details.
 * <p>
 * This class encapsulates the user's ID and role, which are returned upon successful login.
 * </p>
 */
public class LoginOutDTO {

  /**
   * The unique identifier for the user.
   */
  private Integer id;

  /**
   * The role of the user (e.g., USER, ADMIN).
   */
  private String role;

  /**
   * Retrieves the unique identifier for the user.
   *
   * @return the user ID.
   */
  public Integer getId() {
    return id;
  }

  /**
   * Sets the unique identifier for the user.
   *
   * @param id the user ID to set.
   */
  public void setId(final Integer id) {
    this.id = id;
  }

  /**
   * Retrieves the role of the user.
   *
   * @return the user's role.
   */
  public String getRole() {
    return role;
  }

  /**
   * Sets the role of the user.
   *
   * @param role the role to set for the user.
   */
  public void setRole(final String role) {
    this.role = role;
  }

  /**
   * Compares this {@link LoginOutDTO} instance to another object for equality.
   * <p>
   * Two {@link LoginOutDTO} objects are considered equal if their id and role fields are both equal.
   * </p>
   *
   * @param o the object to compare this {@link LoginOutDTO} against.
   * @return true if the given object is equal to this {@link LoginOutDTO}; false otherwise.
   */
  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof LoginOutDTO)) {
      return false;
    }
    LoginOutDTO that = (LoginOutDTO) o;
    return Objects.equals(id, that.id) && Objects.equals(role, that.role);
  }

  /**
   * Returns a hash code value for this {@link LoginOutDTO} instance.
   * <p>
   * The hash code is computed based on the id and role fields.
   * This is used in hash-based collections like HashMap.
   * </p>
   *
   * @return a hash code value for this {@link LoginOutDTO}.
   */
  @Override
  public int hashCode() {
    return Objects.hash(id, role);
  }

  /**
   * Returns a string representation of this {@link LoginOutDTO} instance.
   * <p>
   * The string representation includes the user's ID and role.
   * </p>
   *
   * @return a string representation of this {@link LoginOutDTO}.
   */
  @Override
  public String toString() {
    return "LoginOutDTO{"
      + "id=" + id
      + ", role='" + role
      + '\'' + '}';
  }
}
