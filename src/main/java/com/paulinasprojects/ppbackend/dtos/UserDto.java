package com.paulinasprojects.ppbackend.dtos;

import com.paulinasprojects.ppbackend.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDto {
  private Long id;
  private String name;
  private String email;
  private Role role;
}
