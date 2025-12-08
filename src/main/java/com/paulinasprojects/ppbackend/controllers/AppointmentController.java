package com.paulinasprojects.ppbackend.controllers;

import com.paulinasprojects.ppbackend.common.PaginatedResponseDto;
import com.paulinasprojects.ppbackend.config.AppConstants;
import com.paulinasprojects.ppbackend.dtos.AppointmentRequestDto;
import com.paulinasprojects.ppbackend.dtos.AppointmentResponseDto;
import com.paulinasprojects.ppbackend.dtos.UpdateAppointmentStatusDto;
import com.paulinasprojects.ppbackend.services.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/appointments")
public class AppointmentController {

  private final AppointmentService appointmentService;

  @GetMapping("/doctors/{doctorId}")
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
  public ResponseEntity<AppointmentResponseDto> createAppointment(
        @Valid @RequestBody AppointmentRequestDto request
          ) {
    var appointment = appointmentService.createAppointment(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(appointment);
  }

  @GetMapping("/{id}")
  public ResponseEntity<AppointmentResponseDto> getAppointmentById(
          @PathVariable Long id
  ) {
    var appointment = appointmentService.getAppointmentById(id);
    return ResponseEntity.ok(appointment);
  }

  @PutMapping("/{id}/status")
  public ResponseEntity<AppointmentResponseDto> updateAppointmentStatus(
          @PathVariable Long id,
          @RequestBody UpdateAppointmentStatusDto request
          ) {
    var appointment = appointmentService.updateAppointmentStatus(id, request.getStatus());
    return ResponseEntity.ok(appointment);
  }

  @PutMapping("/{id}")
  public ResponseEntity<AppointmentResponseDto> updateAppointment(
          @PathVariable Long id,
       @Valid @RequestBody AppointmentRequestDto request
  ) {
    var appointment = appointmentService.updateAppointment(id, request);
    return ResponseEntity.ok(appointment);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
    appointmentService.deleteAppointment(id);
    return ResponseEntity.noContent().build();
  }
}
