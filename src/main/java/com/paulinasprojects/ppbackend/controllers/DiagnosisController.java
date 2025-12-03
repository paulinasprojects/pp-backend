package com.paulinasprojects.ppbackend.controllers;

import com.paulinasprojects.ppbackend.dtos.DiagnosisRequestDto;
import com.paulinasprojects.ppbackend.dtos.DiagnosisResponseDto;
import com.paulinasprojects.ppbackend.services.DiagnosisService;
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
          @RequestBody DiagnosisRequestDto request
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

  @PutMapping("/{id}")
  public ResponseEntity<DiagnosisResponseDto> updateDiagnosis(
          @PathVariable Long id,
          @RequestBody DiagnosisRequestDto request
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
