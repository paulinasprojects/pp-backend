package com.paulinasprojects.ppbackend.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdatePatientProfileReq {
  private String address;
  private String bio;
  private String phoneNumber;
  private LocalDate dateOfBirth;
  private LocalDate registeredDate;
}
