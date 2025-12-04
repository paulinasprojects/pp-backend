package com.paulinasprojects.ppbackend.exceptions;

public class PrescriptionNotFoundException extends RuntimeException {
  public PrescriptionNotFoundException(String message) {
    super(message);
  }
}
