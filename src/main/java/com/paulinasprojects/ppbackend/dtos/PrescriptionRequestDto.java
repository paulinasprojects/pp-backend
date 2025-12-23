package com.paulinasprojects.ppbackend.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PrescriptionRequestDto {
  @NotBlank(message = "Medication name is required")
  private String medicationName;

  @NotBlank(message = "Dosage is required")
  private String dosage;

  private String instructions;
  private LocalDate startDate;
  private LocalDate endDate;

  @NotNull(message = "Doctor id is required")
  private Long doctorId;

  @NotNull(message = "Patient id is required")
  private Long patientId;

  @NotNull(message = "Diagnose id is required")
  private Long diagnosisId;
}
