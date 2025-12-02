package com.paulinasprojects.ppbackend.services;

import com.paulinasprojects.ppbackend.dtos.AppointmentRequestDto;
import com.paulinasprojects.ppbackend.dtos.AppointmentResponseDto;

import java.util.List;

public interface AppointmentService {
  List<AppointmentResponseDto> getAppointmentsByDoctor(Long doctorId);
  List<AppointmentResponseDto> getAppointmentsByPatient(Long patientId);
  AppointmentResponseDto createAppointment(AppointmentRequestDto appointment);
  AppointmentResponseDto getAppointmentById(Long id);
  AppointmentResponseDto updateAppointmentStatus(Long id, String status);
  AppointmentResponseDto updateAppointment(Long id, AppointmentRequestDto request);
  void deleteAppointment(Long id);
}
