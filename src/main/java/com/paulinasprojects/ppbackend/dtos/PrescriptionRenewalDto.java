package com.paulinasprojects.ppbackend.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PrescriptionRenewalDto {
  @NotBlank(message = "New end date is required")
  private LocalDate newEndDate;
}
