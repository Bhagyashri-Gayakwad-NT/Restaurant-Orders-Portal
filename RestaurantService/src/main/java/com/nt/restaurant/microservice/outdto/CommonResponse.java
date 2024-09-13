package com.nt.restaurant.microservice.outdto;

/**
 * A generic response DTO for conveying simple messages in the application.
 * This class is commonly used to return a message in response to an API call.
 */
public class CommonResponse {

  /**
   * The message to be conveyed in the response.
   */
  private String message;

  /**
   * Default constructor for creating an empty {@code CommonResponse} instance.
   */
  public CommonResponse() {
  }

  /**
   * Constructor for creating a {@code CommonResponse} instance with a specified message.
   *
   * @param message the message to be set in the response.
   */
  public CommonResponse(String message) {
    this.message = message;
  }

  /**
   * Gets the message in the response.
   *
   * @return the message as a {@code String}.
   */
  public String getMessage() {
    return message;
  }

  /**
   * Sets the message in the response.
   *
   * @param message the message to set.
   */
  public void setMessage(String message) {
    this.message = message;
  }
}

