package com.nt.user.microservice.errors;

import java.util.HashMap;
import java.util.Map;

public class ErrorResponse {
  private int status;
  private String message;
  private Map<String, String> errors;


  public ErrorResponse(int status, String message) {
    this.status = status;
    this.message = message;
    this.errors = new HashMap<>(); // Always initialize errors as an empty map
  }

  public ErrorResponse(int status, String message, Map<String, String> errors) {
    this.status = status;
    this.message = message;
    this.errors = errors != null ? errors : new HashMap<>();
  }

  public ErrorResponse() {
    this.errors = new HashMap<>(); // Ensure errors is initialized
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Map<String, String> getErrors() {
    return errors;
  }

  public void setErrors(Map<String, String> errors) {
    this.errors = errors != null ? errors : new HashMap<>();
  }
}
