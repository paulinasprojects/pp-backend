package com.paulinasprojects.ppbackend.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DiagnosisRequestDto {
  @NotBlank(message = "Diagnose is required")
  private String diagnose;

  private String notes;

  @NotNull(message = "Creation date and time is required")
  private LocalDateTime dateCreated;

  @NotNull(message = "Doctor id is required")
  private Long doctorId;

  @NotNull(message = "Patient id is required")
  private Long patientId;

  @NotNull(message = "Appointment id is required")
  private Long appointmentId;
}
