package com.nt.order.microservice.exception;

public class ResourceAlreadyExistException extends RuntimeException{
  public ResourceAlreadyExistException(String message) {
    super(message);
  }
}
