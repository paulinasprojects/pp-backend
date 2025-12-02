package com.paulinasprojects.ppbackend.controllers;

import com.paulinasprojects.ppbackend.dtos.AppointmentRequestDto;
import com.paulinasprojects.ppbackend.dtos.AppointmentResponseDto;
import com.paulinasprojects.ppbackend.services.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/appointments")
public class AppointmentController {

  private final AppointmentService appointmentService;

  @GetMapping("/doctors/{doctorId}")
  public ResponseEntity<List<AppointmentResponseDto>> getAppointmentsByDoctor(
          @PathVariable Long doctorId) {
    List<AppointmentResponseDto> appointments = appointmentService.getAppointmentsByDoctor(doctorId);
    return ResponseEntity.ok(appointments);
  }

  @GetMapping("/patients/{patientId}")
  public ResponseEntity<List<AppointmentResponseDto>> getAppointmentsByPatient(
          @PathVariable Long patientId
  ) {
    List<AppointmentResponseDto> appointments = appointmentService.getAppointmentsByPatient(patientId);
    return ResponseEntity.ok(appointments);
  }

  @PostMapping
  public ResponseEntity<AppointmentResponseDto> createAppointment(
          @RequestBody AppointmentRequestDto request
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
          @RequestParam String status
  ) {
    var appointment = appointmentService.updateAppointmentStatus(id, status);
    return ResponseEntity.ok(appointment);
  }

  @PutMapping("/{id}")
  public ResponseEntity<AppointmentResponseDto> updateAppointment(
          @PathVariable Long id,
          @RequestBody AppointmentRequestDto request
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
