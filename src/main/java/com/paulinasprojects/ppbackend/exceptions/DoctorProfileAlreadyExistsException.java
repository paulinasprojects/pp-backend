package com.paulinasprojects.ppbackend.exceptions;

public class DoctorProfileAlreadyExistsException extends RuntimeException {
  public DoctorProfileAlreadyExistsException(String message) {
    super(message);
  }
}
