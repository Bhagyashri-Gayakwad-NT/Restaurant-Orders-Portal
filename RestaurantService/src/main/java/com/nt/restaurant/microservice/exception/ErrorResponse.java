package com.nt.restaurant.microservice.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.HashMap;
import java.util.Map;
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErrorResponse {
  private int status;
  private String message;
  private Map<String, String> errors;


  public ErrorResponse(int status, String message) {
    this.status = status;
    this.message = message;
    this.errors = new HashMap<>();
  }

  public ErrorResponse(int status, String message, Map<String, String> errors) {
    this.status = status;
    this.message = message;
    this.errors = errors != null ? errors : new HashMap<>();
  }

  public ErrorResponse() {
    this.errors = new HashMap<>();
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