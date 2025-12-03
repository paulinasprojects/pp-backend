package com.paulinasprojects.ppbackend.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DiagnosisResponseDto {
  private Long id;
  private String diagnose;
  private String notes;
  private LocalDateTime dateCreated = LocalDateTime.now();

  private DoctorInfoDto doctor;
  private PatientInfoDto patient;

  private Long appointmentId;
}
