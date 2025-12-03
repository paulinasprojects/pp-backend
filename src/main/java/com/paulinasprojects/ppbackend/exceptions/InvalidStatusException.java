package com.paulinasprojects.ppbackend.exceptions;

public class InvalidStatusException extends RuntimeException {
  public InvalidStatusException(String message) {
    super(message);
  }
}
