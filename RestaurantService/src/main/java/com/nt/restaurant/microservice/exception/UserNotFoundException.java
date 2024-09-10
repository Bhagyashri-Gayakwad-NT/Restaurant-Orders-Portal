package com.nt.restaurant.microservice.exception;

public class UserNotFoundException extends RuntimeException {
  public UserNotFoundException() {
    super("user not found");
  }
}