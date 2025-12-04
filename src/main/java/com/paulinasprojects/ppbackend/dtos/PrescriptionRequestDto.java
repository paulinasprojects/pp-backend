package com.paulinasprojects.ppbackend.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PrescriptionRequestDto {
  private String medicationName;
  private String dosage;
  private String instructions;
  private LocalDate startDate;
  private LocalDate endDate;

  private Long doctorId;
  private Long patientId;

  private Long diagnosisId;
}
