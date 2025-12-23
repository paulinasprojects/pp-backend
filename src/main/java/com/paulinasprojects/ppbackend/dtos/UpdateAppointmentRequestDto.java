package com.paulinasprojects.ppbackend.dtos;

import com.paulinasprojects.ppbackend.entities.AppointmentStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateAppointmentRequestDto {
  private LocalDate appointmentDate;

  private AppointmentStatus status;
  private String notes;

}
