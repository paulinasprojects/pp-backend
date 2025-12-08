package com.paulinasprojects.ppbackend.controllers;

import com.paulinasprojects.ppbackend.common.PaginatedResponseDto;
import com.paulinasprojects.ppbackend.config.AppConstants;
import com.paulinasprojects.ppbackend.dtos.PrescriptionRenewalDto;
import com.paulinasprojects.ppbackend.dtos.PrescriptionRequestDto;
import com.paulinasprojects.ppbackend.dtos.PrescriptionResponseDto;
import com.paulinasprojects.ppbackend.services.PrescriptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/prescriptions")
public class PrescriptionController {
  private final PrescriptionService prescriptionService;

  @GetMapping("/search")
  public ResponseEntity<PaginatedResponseDto<PrescriptionResponseDto>>searchPrescriptionsByMedication(
          @RequestParam String medicationName,
          @RequestParam(defaultValue = AppConstants.PAGE_NUMBER) Integer page,
          @RequestParam(defaultValue = AppConstants.PAGE_SIZE) Integer size,
          @RequestParam(defaultValue = AppConstants.SORT_BY) String sortBy,
          @RequestParam(defaultValue = AppConstants.SORT_DIRECTION) String sortDirection
  ) {
    var prescriptions = prescriptionService.searchPrescriptionsByMedication(medicationName, page, size, sortBy, sortDirection);
    return ResponseEntity.ok(prescriptions);
  }

  @PostMapping
  public ResponseEntity<PrescriptionResponseDto> createPrescription(
         @Valid @RequestBody PrescriptionRequestDto request
          ) {
    var prescription = prescriptionService.createPrescription(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(prescription);
  }

  @PatchMapping("/{id}/renew")
  public ResponseEntity<PrescriptionResponseDto> renewPrescription(
          @PathVariable Long id,
          @Valid @RequestBody PrescriptionRenewalDto request
          ) {
    var prescription = prescriptionService.renewPrescription(id, request);
    return ResponseEntity.ok(prescription);
  }

  @GetMapping("/doctors/{doctorId}")
  public ResponseEntity<PaginatedResponseDto<PrescriptionResponseDto>> getPrescriptionsByDoctor(
          @PathVariable Long doctorId,
          @RequestParam(defaultValue = AppConstants.PAGE_NUMBER) Integer page,
          @RequestParam(defaultValue = AppConstants.PAGE_SIZE) Integer size,
          @RequestParam(defaultValue = AppConstants.SORT_BY) String sortBy,
          @RequestParam(defaultValue = AppConstants.SORT_DIRECTION) String sortDirection

  ) {
    var prescriptions = prescriptionService.getPrescriptionsByDoctor(doctorId, page, size, sortBy, sortDirection);
    return ResponseEntity.ok(prescriptions);
  }

  @GetMapping("/patients/{patientId}")
  public ResponseEntity<PaginatedResponseDto<PrescriptionResponseDto>> getPrescriptionsByPatient(
          @PathVariable Long patientId,
          @RequestParam(defaultValue = AppConstants.PAGE_NUMBER) Integer page,
          @RequestParam(defaultValue = AppConstants.PAGE_SIZE) Integer size,
          @RequestParam(defaultValue = AppConstants.SORT_BY) String sortBy,
          @RequestParam(defaultValue = AppConstants.SORT_DIRECTION) String sortDirection
  ) {
    var prescriptions = prescriptionService.getPrescriptionsByPatient(patientId, page,size, sortBy, sortDirection);
    return ResponseEntity.ok(prescriptions);
  }

  @GetMapping("/patients/{patientId}/active")
  public ResponseEntity<PaginatedResponseDto<PrescriptionResponseDto>> getActivePrescriptionsByPatient(
          @PathVariable Long patientId,
          @RequestParam(defaultValue = AppConstants.PAGE_NUMBER) Integer page,
          @RequestParam(defaultValue = AppConstants.PAGE_SIZE) Integer size,
          @RequestParam(defaultValue = AppConstants.SORT_BY) String sortBy,
          @RequestParam(defaultValue = AppConstants.SORT_DIRECTION) String sortDirection
  ) {
    var prescriptions = prescriptionService.getActivePrescriptionsByPatient(patientId, page, size, sortBy, sortDirection);
    return ResponseEntity.ok(prescriptions);
  }

  @GetMapping("/patients/{patientId}/expired")
  public ResponseEntity<PaginatedResponseDto<PrescriptionResponseDto>> getExpiredPrescriptionsByPatientId(
          @PathVariable Long patientId,
          @RequestParam(defaultValue = AppConstants.PAGE_NUMBER) Integer page,
          @RequestParam(defaultValue = AppConstants.PAGE_SIZE) Integer size,
          @RequestParam(defaultValue = AppConstants.SORT_BY) String sortBy,
          @RequestParam(defaultValue = AppConstants.SORT_DIRECTION) String sortDirection
  ) {
    var prescriptions = prescriptionService.getExpiredPrescriptionsByPatientId(patientId, page, size, sortBy, sortDirection);
    return ResponseEntity.ok(prescriptions);
  }

  @GetMapping("/appointments/{appointmentId}")
  public ResponseEntity<PaginatedResponseDto<PrescriptionResponseDto>> getPrescriptionsByAppointment(
          @PathVariable Long appointmentId,
          @RequestParam(defaultValue = AppConstants.PAGE_NUMBER) Integer page,
          @RequestParam(defaultValue = AppConstants.PAGE_SIZE) Integer size,
          @RequestParam(defaultValue = AppConstants.SORT_BY) String sortBy,
          @RequestParam(defaultValue = AppConstants.SORT_DIRECTION) String sortDirection
  ) {
    var prescriptions = prescriptionService.getPrescriptionsByAppointment(appointmentId, page, size, sortBy, sortDirection);
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
         @Valid @RequestBody PrescriptionRequestDto request
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
