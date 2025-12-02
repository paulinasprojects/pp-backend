package com.paulinasprojects.ppbackend.exceptions;

public class ProfileNotFoundException extends RuntimeException {
  public ProfileNotFoundException(String message) {
    super(message);
  }
}
