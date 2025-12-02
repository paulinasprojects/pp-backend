package com.paulinasprojects.ppbackend.dtos;

import lombok.Data;

@Data
public class DoctorInfoDto {
  private Long id;
  private String name;
  private String email;
  private String specialization;
}
