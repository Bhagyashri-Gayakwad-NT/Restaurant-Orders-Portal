package com.nt.restaurant.microservice.exception;

public class UserNotRestaurantOwnerException extends RuntimeException {
  public UserNotRestaurantOwnerException(String message) {
    super(message);
  }
}
