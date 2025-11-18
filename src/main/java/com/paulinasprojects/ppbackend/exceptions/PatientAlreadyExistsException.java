package com.paulinasprojects.ppbackend.exceptions;

public class PatientAlreadyExistsException extends RuntimeException {
  public PatientAlreadyExistsException(String message) {
    super(message);
  }
}
