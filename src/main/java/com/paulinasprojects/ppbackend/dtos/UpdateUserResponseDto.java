package com.paulinasprojects.ppbackend.dtos;

import com.paulinasprojects.ppbackend.entities.Role;
import lombok.Data;

@Data
public class UpdateUserResponseDto {
  private Long id;
  private String name;
  private String email;
  private Role role;
}
