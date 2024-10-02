package com.nt.user.microservice.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.HashMap;
import java.util.Map;

/**
 * ErrorResponse is a representation of an error message returned by the API.
 * It contains an HTTP status code, a message describing the error, and optional validation errors.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class  ErrorResponse {

  /**
   * The HTTP status code associated with the error.
   */
  private int status;

  /**
   * The error message providing details about the cause of the error.
   */
  private String message;

  /**
   * A map of field-specific validation errors, if applicable.
   */
  private Map<String, String> errors;

  /**
   * Constructs an ErrorResponse with a status code and message.
   * Initializes the errors map as an empty HashMap.
   *
   * @param status  the HTTP status code
   * @param message the error message
   */
  public ErrorResponse(final int status, final String message) {
    this.status = status;
    this.message = message;
    this.errors = new HashMap<>();
  }

  /**
   * Constructs an ErrorResponse with a status code, message, and a map of field-specific errors.
   * If the errors map is null, it will initialize an empty HashMap.
   *
   * @param status  the HTTP status code
   * @param message the error message
   * @param errors  a map containing validation errors (optional)
   */
  public ErrorResponse(final int status, final String message, final Map<String, String> errors) {
    this.status = status;
    this.message = message;
    this.errors = errors != null ? errors : new HashMap<>();
  }

  /**
   * Default constructor that initializes an empty errors map.
   */
  public ErrorResponse() {
    this.errors = new HashMap<>();
  }

  /**
   * Retrieves the HTTP status code associated with the error.
   *
   * @return the status code
   */
  public int getStatus() {
    return status;
  }

  /**
   * Sets the HTTP status code associated with the error.
   *
   * @param status the status code to set
   */
  public void setStatus(final int status) {
    this.status = status;
  }

  /**
   * Retrieves the error message providing details about the error.
   *
   * @return the error message
   */
  public String getMessage() {
    return message;
  }

  /**
   * Sets the error message providing details about the error.
   *
   * @param message the error message to set
   */
  public void setMessage(final String message) {
    this.message = message;
  }

  /**
   * Retrieves the map of field-specific validation errors, if any.
   *
   * @return a map containing validation errors
   */
  public Map<String, String> getErrors() {
    return errors;
  }

  /**
   * Sets the map of field-specific validation errors.
   * If the provided map is null, an empty map is assigned.
   *
   * @param errors the map of validation errors to set
   */
  public void setErrors(final Map<String, String> errors) {
    this.errors = errors != null ? errors : new HashMap<>();
  }
}
