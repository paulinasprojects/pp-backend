package com.paulinasprojects.ppbackend.dtos;

import com.paulinasprojects.ppbackend.entities.AppointmentStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AppointmentRequestDto {
  @NotBlank(message = "Appointment date is required")
  private LocalDate appointmentDate;

  private AppointmentStatus status;
  private String notes;

  @NotBlank(message = "Doctor id is required")
  private Long doctorId;

  @NotBlank(message = "Patient id is required")
  private Long patientId;
}
