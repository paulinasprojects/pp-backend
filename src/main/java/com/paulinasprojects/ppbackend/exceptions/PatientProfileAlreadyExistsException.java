package com.paulinasprojects.ppbackend.exceptions;

public class PatientProfileAlreadyExistsException extends RuntimeException {
  public PatientProfileAlreadyExistsException(String message) {
    super(message);
  }
}
