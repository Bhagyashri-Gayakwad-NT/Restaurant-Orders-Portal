package com.nt.order.microservice.exception;

public class InvalidRequestException extends RuntimeException{
  public InvalidRequestException(String message) {
    super(message);
  }
}
