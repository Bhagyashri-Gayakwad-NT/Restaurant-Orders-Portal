package com.nt.restaurant.microservice.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the structure of an error response for the REST API.
 * This class is used to encapsulate error information, including the HTTP status code,
 * a message, and any additional validation errors.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErrorResponse {

  /**
   * The HTTP status code of the error response.
   */
  private int status;

  /**
   * The message providing more details about the error.
   */
  private String message;

  /**
   * A map containing field-specific validation errors, if any.
   * The keys represent field names, and the values represent error messages.
   */
  private Map<String, String> errors;

  /**
   * Constructs an ErrorResponse with a given status and message.
   * Initializes an empty map for validation errors.
   *
   * @param status  the HTTP status code of the error
   * @param message a description of the error
   */
  public ErrorResponse(final int status, final String message) {
    this.status = status;
    this.message = message;
    this.errors = new HashMap<>();
  }

  /**
   * Constructs an ErrorResponse with a given status, message, and validation errors.
   *
   * @param status  the HTTP status code of the error
   * @param message a description of the error
   * @param errors  a map of field-specific validation errors
   */
  public ErrorResponse(final int status, final String message, final Map<String, String> errors) {
    this.status = status;
    this.message = message;
    this.errors = errors != null ? errors : new HashMap<>();
  }

  /**
   * Default constructor that initializes an empty map for validation errors.
   */
  public ErrorResponse() {
    this.errors = new HashMap<>();
  }

  /**
   * Returns the HTTP status code of the error.
   *
   * @return the status code
   */
  public int getStatus() {
    return status;
  }

  /**
   * Sets the HTTP status code of the error.
   *
   * @param status the status code to set
   */
  public void setStatus(final int status) {
    this.status = status;
  }

  /**
   * Returns the message describing the error.
   *
   * @return the error message
   */
  public String getMessage() {
    return message;
  }

  /**
   * Sets the message describing the error.
   *
   * @param message the error message to set
   */
  public void setMessage(final String message) {
    this.message = message;
  }

  /**
   * Returns the map of field-specific validation errors.
   *
   * @return the map of validation errors
   */
  public Map<String, String> getErrors() {
    return errors;
  }

  /**
   * Sets the map of field-specific validation errors.
   *
   * @param errors the validation errors to set
   */
  public void setErrors(final Map<String, String> errors) {
    this.errors = errors != null ? errors : new HashMap<>();
  }
}
