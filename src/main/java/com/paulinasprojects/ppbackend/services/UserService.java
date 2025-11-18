package com.paulinasprojects.ppbackend.services;

import com.paulinasprojects.ppbackend.dtos.SignupRequest;
import com.paulinasprojects.ppbackend.dtos.UpdateUserRequestDto;
import com.paulinasprojects.ppbackend.dtos.UpdateUserResponseDto;
import com.paulinasprojects.ppbackend.dtos.UserDto;

public interface UserService {
  UserDto registerUser(SignupRequest request);
  UpdateUserResponseDto updateUser(Long id, UpdateUserRequestDto dto);
  void deleteUser(Long id);
}
