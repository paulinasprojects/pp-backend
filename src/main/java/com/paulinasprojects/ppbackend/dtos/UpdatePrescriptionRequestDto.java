package com.paulinasprojects.ppbackend.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdatePrescriptionRequestDto {
  private String medicationName;
  private String dosage;
  private String instructions;
  private LocalDate startDate;
  private LocalDate endDate;
}
