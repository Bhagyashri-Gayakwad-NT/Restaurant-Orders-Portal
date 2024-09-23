package com.nt.order.microservice.dtos;

/**
 * A class representing a common response structure.
 * This class is used to send a response message back to the client.
 */
public class CommonResponse {

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
  public CommonResponse(String message) {
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
  public void setMessage(String message) {
    this.message = message;
  }
}