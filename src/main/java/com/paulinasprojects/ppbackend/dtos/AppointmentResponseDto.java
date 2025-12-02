package com.paulinasprojects.ppbackend.dtos;

import com.paulinasprojects.ppbackend.entities.AppointmentStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AppointmentResponseDto {
  private Long id;
  private LocalDate appointmentDate;
  private AppointmentStatus status;
  private String notes;

  private DoctorInfoDto doctor;
  private PatientInfoDto patient;
}
