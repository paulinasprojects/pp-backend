package com.paulinasprojects.ppbackend.controllers;

import com.paulinasprojects.ppbackend.dtos.DoctorProfileDto;
import com.paulinasprojects.ppbackend.dtos.UpdateDoctorProfileReq;
import com.paulinasprojects.ppbackend.services.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/doctors")
public class DoctorController {
  private final DoctorService doctorService;

  @PostMapping("/{id}/profile")
  public ResponseEntity<DoctorProfileDto> addProfile(
          @PathVariable(name = "id") Long id,
          @RequestBody DoctorProfileDto doctorProfileDto
  ) {
    var profile = doctorService.addProfileToDoctor(id, doctorProfileDto);
    return ResponseEntity.ok().body(profile);
  }

  @PutMapping("/{id}/profile")
  public DoctorProfileDto updateProfile(
          @PathVariable(name = "id") Long id,
          @RequestBody UpdateDoctorProfileReq req) {
    return doctorService.updateDoctorProfile(id, req);
  }

  @DeleteMapping("/{id}/profile")
  public ResponseEntity<Void> deleteProfile(
          @PathVariable(name = "id") Long id
  ) {
    doctorService.deleteProfile(id);
    return  ResponseEntity.noContent().build();
  }
}
