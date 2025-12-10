package com.paulinasprojects.ppbackend.controllers;

import com.paulinasprojects.ppbackend.common.PaginatedResponseDto;
import com.paulinasprojects.ppbackend.config.AppConstants;
import com.paulinasprojects.ppbackend.dtos.DoctorProfileDto;
import com.paulinasprojects.ppbackend.dtos.UpdateDoctorProfileReq;
import com.paulinasprojects.ppbackend.services.DoctorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Doctor", description = "API for managing Doctors")
@RequestMapping("/api/doctors")
public class DoctorController {
  private final DoctorService doctorService;

  @GetMapping
  @Operation(summary = "Get all doctors")
  public ResponseEntity<PaginatedResponseDto<DoctorProfileDto>> getAllDoctors(
          @RequestParam(defaultValue = AppConstants.PAGE_NUMBER) Integer page,
          @RequestParam(defaultValue = AppConstants.PAGE_SIZE) Integer size,
          @RequestParam(defaultValue = AppConstants.SORT_PATIENTS_BY) String sortBy,
          @RequestParam(defaultValue = AppConstants.SORT_DIRECTION) String sortDirection
  ) {
    var doctors = doctorService.getAllDoctors(page, size, sortBy, sortDirection);
    return ResponseEntity.ok().body(doctors);
  }

  @PostMapping("/{id}/profile")
  @Operation(summary = "Add a profile to a doctor")
  public ResponseEntity<DoctorProfileDto> addProfile(
          @PathVariable(name = "id") Long id,
          @RequestBody DoctorProfileDto doctorProfileDto
  ) {
    var profile = doctorService.addProfileToDoctor(id, doctorProfileDto);
    return ResponseEntity.ok().body(profile);
  }

  @PutMapping("/{id}/profile")
  @Operation(summary = "Update the doctors profile")
  public DoctorProfileDto updateProfile(
          @PathVariable(name = "id") Long id,
          @RequestBody UpdateDoctorProfileReq req) {
    return doctorService.updateDoctorProfile(id, req);
  }

  @DeleteMapping("/{id}/profile")
  @Operation(summary = "Delete the doctors profile")
  public ResponseEntity<Void> deleteProfile(
          @PathVariable(name = "id") Long id
  ) {
    doctorService.deleteProfile(id);
    return  ResponseEntity.noContent().build();
  }
}
