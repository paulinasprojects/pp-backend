package com.paulinasprojects.ppbackend.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AppointmentRequestDto {
  private LocalDate appointmentDate;
  private String notes;
  private Long doctorId;
  private Long patientId;
}
