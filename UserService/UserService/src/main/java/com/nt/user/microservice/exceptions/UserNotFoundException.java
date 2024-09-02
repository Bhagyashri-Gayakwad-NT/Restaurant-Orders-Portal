package com.nt.user.microservice.exceptions;

public class UserNotFoundException extends RuntimeException{
  public UserNotFoundException(String message) {
    super(message);
  }
}
