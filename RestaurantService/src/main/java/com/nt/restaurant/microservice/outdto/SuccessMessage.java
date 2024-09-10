package com.nt.restaurant.microservice.outdto;

public class SuccessMessage {
  private String message;

  public SuccessMessage() {
  }

  public SuccessMessage(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
