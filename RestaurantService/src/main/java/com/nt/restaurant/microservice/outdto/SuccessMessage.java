package com.nt.restaurant.microservice.outdto;

/**
 * Data Transfer Object (DTO) for representing a success message in the response.
 * This class is used to convey success messages from the server to the client.
 */
public class SuccessMessage {

  /**
   * The success message to be conveyed.
   */
  private String message;

  /**
   * Default constructor for creating an empty {@code SuccessMessage} instance.
   */
  public SuccessMessage() {
  }

  /**
   * Constructor for creating a {@code SuccessMessage} instance with a specified message.
   *
   * @param message the success message to set.
   */
  public SuccessMessage(String message) {
    this.message = message;
  }

  /**
   * Gets the success message.
   *
   * @return the success message.
   */
  public String getMessage() {
    return message;
  }

  /**
   * Sets the success message.
   *
   * @param message the success message to set.
   */
  public void setMessage(String message) {
    this.message = message;
  }
}

