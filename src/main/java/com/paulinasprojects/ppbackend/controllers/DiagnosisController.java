package com.paulinasprojects.ppbackend.controllers;

import com.paulinasprojects.ppbackend.common.PaginatedResponseDto;
import com.paulinasprojects.ppbackend.config.AppConstants;
import com.paulinasprojects.ppbackend.dtos.DiagnosisRequestDto;
import com.paulinasprojects.ppbackend.dtos.DiagnosisResponseDto;
import com.paulinasprojects.ppbackend.services.DiagnosisService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/diagnoses")
public class DiagnosisController {
  private final DiagnosisService diagnosisService;

  @PostMapping
  public ResponseEntity<DiagnosisResponseDto> createDiagnosis(
       @Valid @RequestBody DiagnosisRequestDto request
          ) {
    var diagnosis = diagnosisService.createDiagnosis(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(diagnosis);
  }

  @GetMapping("/{id}")
  public ResponseEntity<DiagnosisResponseDto> getDiagnosisById(
          @PathVariable Long id
  ) {
    var diagnosis = diagnosisService.getDiagnosisById(id);
    return ResponseEntity.ok(diagnosis);
  }

  @GetMapping("/doctors/{doctorId}")
  public ResponseEntity<PaginatedResponseDto<DiagnosisResponseDto>> getDiagnosesByDoctor(
          @PathVariable Long doctorId,
          @RequestParam(defaultValue = AppConstants.PAGE_NUMBER) Integer page,
          @RequestParam(defaultValue = AppConstants.PAGE_SIZE) Integer size,
          @RequestParam(defaultValue = AppConstants.SORT_DIAGNOSIS_BY) String sortBy,
          @RequestParam(defaultValue = AppConstants.SORT_DIRECTION) String sortDirection
  ) {
    var diagnoses = diagnosisService.getDiagnosesByDoctor(doctorId, page, size, sortBy, sortDirection);
    return ResponseEntity.ok(diagnoses);
  }

  @GetMapping("/patients/{patientId}")
  public ResponseEntity<PaginatedResponseDto<DiagnosisResponseDto>> getDiagnosesByPatient(
          @PathVariable Long patientId,
          @RequestParam(defaultValue = AppConstants.PAGE_NUMBER) Integer page,
          @RequestParam(defaultValue = AppConstants.PAGE_SIZE) Integer size,
          @RequestParam(defaultValue = AppConstants.SORT_DIAGNOSIS_BY) String sortBy,
          @RequestParam(defaultValue = AppConstants.SORT_DIRECTION) String sortDirection
  ) {
    var diagnoses = diagnosisService.getDiagnosesByPatient(patientId, page, size, sortBy, sortDirection);
    return ResponseEntity.ok(diagnoses);
  }

  @GetMapping("/appointments/{appointmentId}")
  public ResponseEntity<DiagnosisResponseDto> getDiagnosisByAppointment(
          @PathVariable Long appointmentId
  ) {
    var diagnosis = diagnosisService.getDiagnosisByAppointment(appointmentId);
    return ResponseEntity.ok(diagnosis);
  }

  @PutMapping("/{id}")
  public ResponseEntity<DiagnosisResponseDto> updateDiagnosis(
          @PathVariable Long id,
       @Valid @RequestBody DiagnosisRequestDto request
  ) {
    var diagnosis = diagnosisService.updateDiagnosis(id, request);
    return  ResponseEntity.ok(diagnosis);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteDiagnosis( @PathVariable Long id) {
    diagnosisService.deleteDiagnosis(id);
    return ResponseEntity.noContent().build();
  }
}
