package com.nt.restaurant.microservice.outdto;

public class CommonResponse {
  private String message;

  public CommonResponse() {
  }

  public CommonResponse(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
