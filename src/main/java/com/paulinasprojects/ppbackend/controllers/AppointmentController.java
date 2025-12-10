package com.paulinasprojects.ppbackend.controllers;

import com.paulinasprojects.ppbackend.common.PaginatedResponseDto;
import com.paulinasprojects.ppbackend.config.AppConstants;
import com.paulinasprojects.ppbackend.dtos.AppointmentRequestDto;
import com.paulinasprojects.ppbackend.dtos.AppointmentResponseDto;
import com.paulinasprojects.ppbackend.dtos.UpdateAppointmentStatusDto;
import com.paulinasprojects.ppbackend.services.AppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Appointments", description = "API for managing the appointments")
@RequestMapping("/api/appointments")
public class AppointmentController {

  private final AppointmentService appointmentService;

  @GetMapping("/doctors/{doctorId}")
  @Operation(summary = "Get all appointments by doctor Id")
  public ResponseEntity<PaginatedResponseDto<AppointmentResponseDto>> getAppointmentsByDoctor(
          @PathVariable Long doctorId,
          @RequestParam(defaultValue = AppConstants.PAGE_NUMBER) Integer page,
          @RequestParam(defaultValue = AppConstants.PAGE_SIZE) Integer size,
          @RequestParam(defaultValue = AppConstants.SORT_APPOINTMENTS_BY) String sortBy,
          @RequestParam(defaultValue = AppConstants.SORT_DIRECTION) String sortDirection
  ) {
    var appointments = appointmentService.getAppointmentsByDoctor(doctorId, page, size, sortBy, sortDirection);
    return ResponseEntity.ok(appointments);
  }

  @GetMapping("/patients/{patientId}")
  @Operation(summary = "Get all appointments by patient Id")
  public ResponseEntity<PaginatedResponseDto<AppointmentResponseDto>> getAppointmentsByPatient(
          @PathVariable Long patientId,
          @RequestParam(defaultValue = AppConstants.PAGE_NUMBER) Integer page,
          @RequestParam(defaultValue = AppConstants.PAGE_SIZE) Integer size,
          @RequestParam(defaultValue = AppConstants.SORT_APPOINTMENTS_BY) String sortBy,
          @RequestParam(defaultValue = AppConstants.SORT_DIRECTION) String sortDirection
  ) {
    var appointments = appointmentService.getAppointmentsByPatient(patientId, page, size, sortBy, sortDirection);
    return ResponseEntity.ok(appointments);
  }

  @PostMapping
  @Operation(summary = "Create an appointment")
  public ResponseEntity<AppointmentResponseDto> createAppointment(
        @Valid @RequestBody AppointmentRequestDto request
          ) {
    var appointment = appointmentService.createAppointment(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(appointment);
  }

  @GetMapping("/{id}")
  @Operation(summary = "Get an appointment by Id")
  public ResponseEntity<AppointmentResponseDto> getAppointmentById(
          @PathVariable Long id
  ) {
    var appointment = appointmentService.getAppointmentById(id);
    return ResponseEntity.ok(appointment);
  }

  @PutMapping("/{id}/status")
  @Operation(summary = "Update the appointment status")
  public ResponseEntity<AppointmentResponseDto> updateAppointmentStatus(
          @PathVariable Long id,
          @RequestBody UpdateAppointmentStatusDto request
          ) {
    var appointment = appointmentService.updateAppointmentStatus(id, request.getStatus());
    return ResponseEntity.ok(appointment);
  }

  @PutMapping("/{id}")
  @Operation(summary = "Update the appointment details")
  public ResponseEntity<AppointmentResponseDto> updateAppointment(
          @PathVariable Long id,
       @Valid @RequestBody AppointmentRequestDto request
  ) {
    var appointment = appointmentService.updateAppointment(id, request);
    return ResponseEntity.ok(appointment);
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete the appointment")
  public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
    appointmentService.deleteAppointment(id);
    return ResponseEntity.noContent().build();
  }
}
