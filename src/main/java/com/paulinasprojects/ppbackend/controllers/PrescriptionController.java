package com.paulinasprojects.ppbackend.controllers;

import com.paulinasprojects.ppbackend.dtos.PrescriptionRequestDto;
import com.paulinasprojects.ppbackend.dtos.PrescriptionResponseDto;
import com.paulinasprojects.ppbackend.services.PrescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/prescriptions")
public class PrescriptionController {
  private final PrescriptionService prescriptionService;

  @PostMapping
  public ResponseEntity<PrescriptionResponseDto> createPrescription(
          @RequestBody PrescriptionRequestDto request
          ) {
    var prescription = prescriptionService.createPrescription(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(prescription);
  }

  @GetMapping("/doctors/{doctorId}")
  public ResponseEntity<List<PrescriptionResponseDto>> getPrescriptionsByDoctor(
          @PathVariable Long doctorId
  ) {
    List<PrescriptionResponseDto> prescriptions = prescriptionService.getPrescriptionsByDoctor(doctorId);
    return ResponseEntity.ok(prescriptions);
  }

  @GetMapping("/patients/{patientId}")
  public ResponseEntity<List<PrescriptionResponseDto>> getPrescriptionsByPatient(
          @PathVariable Long patientId
  ) {
    List<PrescriptionResponseDto> prescriptions = prescriptionService.getPrescriptionsByPatient(patientId);
    return ResponseEntity.ok(prescriptions);
  }

  @GetMapping("/diagnoses/{diagnosisId}")
  public ResponseEntity<PrescriptionResponseDto> getPrescriptionByDiagnosisId(
          @PathVariable Long diagnosisId
  ) {
    var prescription = prescriptionService.getPrescriptionByDiagnosisId(diagnosisId);
    return ResponseEntity.ok(prescription);
  }

  @GetMapping("/{id}")
  public ResponseEntity<PrescriptionResponseDto> getPrescriptionById(@PathVariable Long id) {
   var prescription = prescriptionService.getPrescriptionById(id);
    return ResponseEntity.ok(prescription);
  }

  @PutMapping("/{id}")
  public ResponseEntity<PrescriptionResponseDto> updatePrescription(
          @PathVariable Long id,
          @RequestBody PrescriptionRequestDto request
  ) {
    var prescription = prescriptionService.updatePrescription(id, request);
    return ResponseEntity.ok(prescription);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePrescription(@PathVariable Long id) {
    prescriptionService.deletePrescription(id);
    return ResponseEntity.noContent().build();
  }
}
