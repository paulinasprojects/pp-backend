package com.paulinasprojects.ppbackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(DoctorNotFoundException.class)
  public ResponseEntity<String> DoctorNotFoundException(DoctorNotFoundException e) {
    String message = e.getMessage();
    return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(PatientNotFoundException.class)
  public ResponseEntity<String> PatientNotFoundException(PatientNotFoundException e) {
    String message = e.getMessage();
    return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<String> UserNotFoundException(UserNotFoundException e) {
    String message = e.getMessage();
    return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(DoctorAlreadyExistsException.class)
  public ResponseEntity<String> DoctorAlreadyExistsException(DoctorAlreadyExistsException e) {
    String message = e.getMessage();
    return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
  }
  @ExceptionHandler(PatientAlreadyExistsException.class)
  public ResponseEntity<String> PatientAlreadyExistsException(PatientAlreadyExistsException e) {
    String message = e.getMessage();
    return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(DuplicateUserException.class)
  public ResponseEntity<String> DuplicateUserException(DuplicateUserException e) {
    String message = e.getMessage();
    return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(NotDoctorRoleException.class)
  public ResponseEntity<String> NotDoctorRoleException(NotDoctorRoleException e) {
    String message = e.getMessage();
    return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(NotPatientRoleException.class)
  public ResponseEntity<String> NotPatientRoleException(NotPatientRoleException e) {
    String message = e.getMessage();
    return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(DoctorProfileAlreadyExistsException.class)
  public ResponseEntity<String> DoctorProfileAlreadyExists(DoctorProfileAlreadyExistsException e) {
    String message = e.getMessage();
    return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(PatientProfileAlreadyExistsException.class)
  public ResponseEntity<String> PatientProfileAlreadyExists(PatientProfileAlreadyExistsException e) {
    String message = e.getMessage();
    return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
  }


  @ExceptionHandler(ProfileNotFoundException.class)
  public ResponseEntity<String>ProfileNotFound(ProfileNotFoundException e) {
    String message = e.getMessage();
    return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(AppointmentNotFoundException.class)
  public ResponseEntity<String> AppointmentNotFound(AppointmentNotFoundException e) {
    String message = e.getMessage();
    return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(DiagnoseNotFoundException.class)
  public ResponseEntity<String> DiagnoseNotFound(DiagnoseNotFoundException e) {
    String message = e.getMessage();
    return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
  }
  @ExceptionHandler(PrescriptionNotFoundException.class)
  public ResponseEntity<String> PrescriptionNotFound(PrescriptionNotFoundException e) {
    String message = e.getMessage();
    return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(InvalidStatusException.class)
  public ResponseEntity<String> InvalidStatus(InvalidStatusException e) {
    String message = e.getMessage();
    return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
  }
}
