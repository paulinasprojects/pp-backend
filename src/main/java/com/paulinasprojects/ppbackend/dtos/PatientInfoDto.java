package com.paulinasprojects.ppbackend.dtos;

import lombok.Data;

@Data
public class PatientInfoDto {
  private Long id;
  private String name;
  private String email;
}
