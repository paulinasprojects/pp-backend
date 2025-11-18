package com.paulinasprojects.ppbackend.exceptions;

public class DoctorAlreadyExistsException extends RuntimeException {
  public DoctorAlreadyExistsException(String message) {
    super(message);
  }
}
