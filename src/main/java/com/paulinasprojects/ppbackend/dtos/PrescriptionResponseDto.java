package com.paulinasprojects.ppbackend.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PrescriptionResponseDto {
  private Long id;
  private String medicationName;
  private String dosage;
  private String instructions;
  private LocalDate startDate = LocalDate.now();
  private LocalDate endDate;

  private DoctorInfoDto doctor;
  private PatientInfoDto patient;

  private Long diagnosisId;
}
