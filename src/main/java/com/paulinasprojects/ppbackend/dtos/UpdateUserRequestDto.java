package com.paulinasprojects.ppbackend.dtos;

import lombok.Data;

@Data
public class UpdateUserRequestDto {
  private String name;
  private String email;
}
