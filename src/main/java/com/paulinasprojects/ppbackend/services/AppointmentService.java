package com.paulinasprojects.ppbackend.services;

import com.paulinasprojects.ppbackend.common.PaginatedResponseDto;
import com.paulinasprojects.ppbackend.dtos.AppointmentRequestDto;
import com.paulinasprojects.ppbackend.dtos.AppointmentResponseDto;

public interface AppointmentService {
  PaginatedResponseDto<AppointmentResponseDto> getAppointmentsByDoctor(Long doctorId, Integer page, Integer size, String sortBy, String sortDirection);
  PaginatedResponseDto<AppointmentResponseDto> getAppointmentsByPatient(Long patientId, Integer page, Integer size, String sortBy, String sortDirection);
  AppointmentResponseDto createAppointment(AppointmentRequestDto appointment);
  AppointmentResponseDto getAppointmentById(Long id);
  AppointmentResponseDto updateAppointmentStatus(Long id, String status);
  AppointmentResponseDto updateAppointment(Long id, AppointmentRequestDto request);
  void deleteAppointment(Long id);
}
