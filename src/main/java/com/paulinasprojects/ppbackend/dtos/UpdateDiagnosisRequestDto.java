package com.paulinasprojects.ppbackend.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateDiagnosisRequestDto {
  private String diagnose;
  private String notes;
  private LocalDateTime dateCreated;

}
