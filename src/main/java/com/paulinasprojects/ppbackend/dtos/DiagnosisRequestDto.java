package com.paulinasprojects.ppbackend.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DiagnosisRequestDto {
  @NotBlank(message = "Diagnose is required")
  private String diagnose;

  private String notes;

  @NotBlank(message = "Creation date and time is required")
  private LocalDateTime dateCreated;

  @NotBlank(message = "Doctor id is required")
  private Long doctorId;

  @NotBlank(message = "Patient id is required")
  private Long patientId;

  @NotBlank(message = "Appointment id is required")
  private Long appointmentId;
}
