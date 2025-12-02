package com.paulinasprojects.ppbackend.exceptions;

public class NotDoctorRoleException extends RuntimeException {
  public NotDoctorRoleException(String message) {
    super(message);
  }
}
