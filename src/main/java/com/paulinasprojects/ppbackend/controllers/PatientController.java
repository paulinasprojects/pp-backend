package com.paulinasprojects.ppbackend.controllers;

import com.paulinasprojects.ppbackend.dtos.PatientProfileDto;
import com.paulinasprojects.ppbackend.dtos.UpdatePatientProfileReq;
import com.paulinasprojects.ppbackend.services.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/patients")
public class PatientController {
  private final PatientService patientService;

  @PostMapping("/{id}/profile")
  public ResponseEntity<PatientProfileDto> addProfile(
          @PathVariable(name = "id") Long id,
          @RequestBody PatientProfileDto patientProfileDto
  ) {
    var profile = patientService.addProfileToPatient(id, patientProfileDto);
    return ResponseEntity.ok().body(profile);
  }

  @PutMapping("/{id}/profile")
  public PatientProfileDto updateProfile(
          @PathVariable(name = "id") Long id,
          @RequestBody UpdatePatientProfileReq req
          ) {
    return patientService.updatePatientProfile(id, req);
  }

  @DeleteMapping("/{id}/profile")
  public ResponseEntity<Void> deleteProfile(
          @PathVariable(name = "id") Long id
  ) {
    patientService.deleteProfile(id);
    return ResponseEntity.noContent().build();
  }
}
