package com.paulinasprojects.ppbackend.exceptions;

public class NotPatientRoleException extends RuntimeException {
  public NotPatientRoleException(String message) {
    super(message);
  }
}
