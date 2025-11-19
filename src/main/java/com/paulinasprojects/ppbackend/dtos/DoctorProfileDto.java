package com.paulinasprojects.ppbackend.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DoctorProfileDto {
  private Long id;
  private String address;
  private String specialization;
  private String hospital;
  private String bio;
  private String phoneNumber;
  private LocalDate dateOfBirth;
  private LocalDate registeredDate;
}
