package com.paulinasprojects.ppbackend.controllers;

import com.paulinasprojects.ppbackend.common.PaginatedResponseDto;
import com.paulinasprojects.ppbackend.config.AppConstants;
import com.paulinasprojects.ppbackend.dtos.PrescriptionRenewalDto;
import com.paulinasprojects.ppbackend.dtos.PrescriptionRequestDto;
import com.paulinasprojects.ppbackend.dtos.PrescriptionResponseDto;
import com.paulinasprojects.ppbackend.services.PrescriptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@Tag(name = "Prescriptions", description = "API for managing the prescriptions")
@RequestMapping("/api/prescriptions")
public class PrescriptionController {
  private final PrescriptionService prescriptionService;

  @GetMapping("/search")
  @Operation(summary = "Search medication my name")
  public ResponseEntity<PaginatedResponseDto<PrescriptionResponseDto>>searchPrescriptionsByMedication(
          @RequestParam String medicationName,
          @RequestParam(defaultValue = AppConstants.PAGE_NUMBER) Integer page,
          @RequestParam(defaultValue = AppConstants.PAGE_SIZE) Integer size,
          @RequestParam(defaultValue = AppConstants.SORT_PRESCRIPTIONS_BY) String sortBy,
          @RequestParam(defaultValue = AppConstants.SORT_DIRECTION) String sortDirection
  ) {
    var prescriptions = prescriptionService.searchPrescriptionsByMedication(medicationName, page, size, sortBy, sortDirection);
    return ResponseEntity.ok(prescriptions);
  }

  @PostMapping
  @Operation(summary = "Create a new prescription")
  public ResponseEntity<PrescriptionResponseDto> createPrescription(
         @Valid @RequestBody PrescriptionRequestDto request
          ) {
    var prescription = prescriptionService.createPrescription(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(prescription);
  }

  @PatchMapping("/{id}/renew")
  @Operation(summary = "Renew the prescription")
  public ResponseEntity<PrescriptionResponseDto> renewPrescription(
          @PathVariable Long id,
          @Valid @RequestBody PrescriptionRenewalDto request
          ) {
    var prescription = prescriptionService.renewPrescription(id, request);
    return ResponseEntity.ok(prescription);
  }

  @GetMapping("/doctors/{doctorId}")
  @Operation(summary = "Get all prescriptions by doctor Id")
  public ResponseEntity<PaginatedResponseDto<PrescriptionResponseDto>> getPrescriptionsByDoctor(
          @PathVariable Long doctorId,
          @RequestParam(defaultValue = AppConstants.PAGE_NUMBER) Integer page,
          @RequestParam(defaultValue = AppConstants.PAGE_SIZE) Integer size,
          @RequestParam(defaultValue = AppConstants.SORT_PRESCRIPTIONS_BY) String sortBy,
          @RequestParam(defaultValue = AppConstants.SORT_DIRECTION) String sortDirection

  ) {
    var prescriptions = prescriptionService.getPrescriptionsByDoctor(doctorId, page, size, sortBy, sortDirection);
    return ResponseEntity.ok(prescriptions);
  }

  @GetMapping("/patients/{patientId}")
  @Operation(summary = "Get all prescriptions by patient Id")
  public ResponseEntity<PaginatedResponseDto<PrescriptionResponseDto>> getPrescriptionsByPatient(
          @PathVariable Long patientId,
          @RequestParam(defaultValue = AppConstants.PAGE_NUMBER) Integer page,
          @RequestParam(defaultValue = AppConstants.PAGE_SIZE) Integer size,
          @RequestParam(defaultValue = AppConstants.SORT_PRESCRIPTIONS_BY) String sortBy,
          @RequestParam(defaultValue = AppConstants.SORT_DIRECTION) String sortDirection
  ) {
    var prescriptions = prescriptionService.getPrescriptionsByPatient(patientId, page,size, sortBy, sortDirection);
    return ResponseEntity.ok(prescriptions);
  }

  @GetMapping("/patients/{patientId}/active")
  @Operation(summary = "Get all active prescriptions by patient Id")
  public ResponseEntity<PaginatedResponseDto<PrescriptionResponseDto>> getActivePrescriptionsByPatient(
          @PathVariable Long patientId,
          @RequestParam(defaultValue = AppConstants.PAGE_NUMBER) Integer page,
          @RequestParam(defaultValue = AppConstants.PAGE_SIZE) Integer size,
          @RequestParam(defaultValue = AppConstants.SORT_PRESCRIPTIONS_BY) String sortBy,
          @RequestParam(defaultValue = AppConstants.SORT_DIRECTION) String sortDirection
  ) {
    var prescriptions = prescriptionService.getActivePrescriptionsByPatient(patientId, page, size, sortBy, sortDirection);
    return ResponseEntity.ok(prescriptions);
  }

  @GetMapping("/patients/{patientId}/expired")
  @Operation(summary = "Get all expired prescriptions by patient Id")
  public ResponseEntity<PaginatedResponseDto<PrescriptionResponseDto>> getExpiredPrescriptionsByPatientId(
          @PathVariable Long patientId,
          @RequestParam(defaultValue = AppConstants.PAGE_NUMBER) Integer page,
          @RequestParam(defaultValue = AppConstants.PAGE_SIZE) Integer size,
          @RequestParam(defaultValue = AppConstants.SORT_PRESCRIPTIONS_BY) String sortBy,
          @RequestParam(defaultValue = AppConstants.SORT_DIRECTION) String sortDirection
  ) {
    var prescriptions = prescriptionService.getExpiredPrescriptionsByPatientId(patientId, page, size, sortBy, sortDirection);
    return ResponseEntity.ok(prescriptions);
  }

  @GetMapping("/appointments/{appointmentId}")
  @Operation(summary = "Get all prescriptions by appointment Id")
  public ResponseEntity<PaginatedResponseDto<PrescriptionResponseDto>> getPrescriptionsByAppointment(
          @PathVariable Long appointmentId,
          @RequestParam(defaultValue = AppConstants.PAGE_NUMBER) Integer page,
          @RequestParam(defaultValue = AppConstants.PAGE_SIZE) Integer size,
          @RequestParam(defaultValue = AppConstants.SORT_PRESCRIPTIONS_BY) String sortBy,
          @RequestParam(defaultValue = AppConstants.SORT_DIRECTION) String sortDirection
  ) {
    var prescriptions = prescriptionService.getPrescriptionsByAppointment(appointmentId, page, size, sortBy, sortDirection);
    return ResponseEntity.ok(prescriptions);
  }

  @GetMapping("/diagnoses/{diagnosisId}")
  @Operation(summary = "Get all active prescription by diagnosis Id")
  public ResponseEntity<PrescriptionResponseDto> getPrescriptionByDiagnosisId(
          @PathVariable Long diagnosisId
  ) {
    var prescription = prescriptionService.getPrescriptionByDiagnosisId(diagnosisId);
    return ResponseEntity.ok(prescription);
  }

  @GetMapping("/{id}")
  @Operation(summary = "Get prescription by Id")
  public ResponseEntity<PrescriptionResponseDto> getPrescriptionById(@PathVariable Long id) {
   var prescription = prescriptionService.getPrescriptionById(id);
    return ResponseEntity.ok(prescription);
  }

  @PutMapping("/{id}")
  @Operation(summary = "Update the prescription")
  public ResponseEntity<PrescriptionResponseDto> updatePrescription(
          @PathVariable Long id,
         @Valid @RequestBody PrescriptionRequestDto request
  ) {
    var prescription = prescriptionService.updatePrescription(id, request);
    return ResponseEntity.ok(prescription);
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete the prescription")
  public ResponseEntity<Void> deletePrescription(@PathVariable Long id) {
    prescriptionService.deletePrescription(id);
    return ResponseEntity.noContent().build();
  }
}
