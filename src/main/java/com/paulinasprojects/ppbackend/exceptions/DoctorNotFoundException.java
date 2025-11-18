package com.paulinasprojects.ppbackend.exceptions;

public class DoctorNotFoundException extends RuntimeException {
  public DoctorNotFoundException(String message) {
    super(message);
  }
}
