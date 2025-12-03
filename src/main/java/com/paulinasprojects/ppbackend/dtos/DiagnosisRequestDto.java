package com.paulinasprojects.ppbackend.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DiagnosisRequestDto {
  private String diagnose;
  private String notes;
  private LocalDateTime dateCreated;

  private Long doctorId;
  private Long patientId;

  private Long appointmentId;
}
