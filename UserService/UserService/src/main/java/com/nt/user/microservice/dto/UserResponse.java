package com.nt.user.microservice.dto;

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
}
