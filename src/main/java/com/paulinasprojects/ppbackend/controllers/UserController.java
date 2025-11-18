package com.paulinasprojects.ppbackend.controllers;

import com.paulinasprojects.ppbackend.dtos.SignupRequest;
import com.paulinasprojects.ppbackend.dtos.UpdateUserRequestDto;
import com.paulinasprojects.ppbackend.dtos.UpdateUserResponseDto;
import com.paulinasprojects.ppbackend.dtos.UserDto;
import com.paulinasprojects.ppbackend.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
  private final UserService userService;

  @PostMapping("/users")
  public ResponseEntity<UserDto> registerUser(
          @Valid @RequestBody SignupRequest request,
          UriComponentsBuilder uriBuilder) {
   var userDto = userService.registerUser(request);
   var uri = uriBuilder.path("/users/{id}").buildAndExpand(userDto.getId()).toUri();
   return ResponseEntity.created(uri).body(userDto);
  }

  @PutMapping("/users/{id}")
  public UpdateUserResponseDto updateUser(
          @PathVariable Long id,
          @RequestBody UpdateUserRequestDto request
          ) {
    return userService.updateUser(id, request);
  }

  @DeleteMapping("/users/{id}")
  public ResponseEntity<Void> deleteUser(
          @PathVariable Long id
  ) {
    userService.deleteUser(id);
    return ResponseEntity.notFound().build();
  }
}
