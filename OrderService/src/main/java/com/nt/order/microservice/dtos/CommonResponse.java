package com.nt.order.microservice.dtos;

/**
 * A class representing a common response structure.
 * This class is used to send a response message back to the client.
 */
public class CommonResponse {

  /**
   * The response message that will be sent back to the client.
   * It provides information about the result of an operation, such as success or failure details.
   */
  private String message;

  /**
   * Default constructor.
   */
  public CommonResponse() {
  }

  /**
   * Parameterized constructor to create a CommonResponse with a specified message.
   *
   * @param message the response message
   */
  public CommonResponse(final String message) {
    this.message = message;
  }

  /**
   * Retrieves the response message.
   *
   * @return the response message
   */
  public String getMessage() {
    return message;
  }

  /**
   * Sets the response message.
   *
   * @param message the message to set
   */
  public void setMessage(final String message) {
    this.message = message;
  }
}
