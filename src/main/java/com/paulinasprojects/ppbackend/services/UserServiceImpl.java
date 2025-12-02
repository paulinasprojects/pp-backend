package com.paulinasprojects.ppbackend.services;

import com.paulinasprojects.ppbackend.dtos.SignupRequest;
import com.paulinasprojects.ppbackend.dtos.UpdateUserRequestDto;
import com.paulinasprojects.ppbackend.dtos.UpdateUserResponseDto;
import com.paulinasprojects.ppbackend.dtos.UserDto;
import com.paulinasprojects.ppbackend.entities.Role;
import com.paulinasprojects.ppbackend.exceptions.DuplicateUserException;
import com.paulinasprojects.ppbackend.exceptions.UserNotFoundException;
import com.paulinasprojects.ppbackend.mappers.UserMapper;
import com.paulinasprojects.ppbackend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final UserMapper userMapper;

  @Override
  public UserDto registerUser(SignupRequest request) {
    if (userRepository.existsByEmail(request.getEmail())) {
      throw new DuplicateUserException("User already exists with this email");
    }

    Role role = request.getRole();
    if (role != Role.DOCTOR && role != Role.PATIENT && role != Role.ADMIN) {
      throw new RuntimeException("Invalid role.");
    }

    var user = userMapper.toEntity(request);
    user.setRole(request.getRole());

    userRepository.save(user);
    return userMapper.toDto(user);
  }

  @Override
  public UpdateUserResponseDto updateUser(Long id, UpdateUserRequestDto dto) {
    var user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
    userMapper.updateUserEntityFromDto(dto, user);

    var updatedUser = userRepository.save(user);
    return userMapper.toUpdateUserDto(updatedUser);
  }

  @Override
  public void deleteUser(Long id) {
    var user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
    userRepository.delete(user);
  }
}
