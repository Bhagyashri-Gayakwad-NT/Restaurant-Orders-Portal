package com.nt.user.microservice.dto;

import java.util.Objects;

/**
 * UserResponse is a simple DTO (Data Transfer Object) that holds a success message.
 * It is typically used to convey a message upon successful operations such as registration, update, or deletion of a user.
 */
public class UserResponse {

  /**
   * The message that indicates the success of an operation.
   */
  private String successMessage;

  /**
   * Retrieves the success message.
   *
   * @return the success message as a String.
   */
  public String getSuccessMessage() {
    return successMessage;
  }

  /**
   * Sets the success message to indicate the outcome of an operation.
   *
   * @param successMessage the success message to be set.
   */
  public void setSuccessMessage(String successMessage) {
    this.successMessage = successMessage;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true; }
    if (!(o instanceof UserResponse)) {
      return false; }
    UserResponse that = (UserResponse) o;
    return Objects.equals(successMessage, that.successMessage);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(successMessage);
  }

  @Override
  public String toString() {
    return "UserResponse{" +
      "successMessage='" + successMessage + '\'' +
      '}';
  }
}
