package com.paulinasprojects.ppbackend.services;

import com.paulinasprojects.ppbackend.common.PaginatedResponseDto;
import com.paulinasprojects.ppbackend.dtos.AppointmentRequestDto;
import com.paulinasprojects.ppbackend.dtos.AppointmentResponseDto;
import com.paulinasprojects.ppbackend.dtos.UpdateAppointmentRequestDto;

public interface AppointmentService {
  PaginatedResponseDto<AppointmentResponseDto> getAppointmentsByDoctor(Long doctorId, Integer page, Integer size, String sortBy, String sortDirection);
  PaginatedResponseDto<AppointmentResponseDto> getAppointmentsByPatient(Long patientId, Integer page, Integer size, String sortBy, String sortDirection);
  AppointmentResponseDto createAppointment(AppointmentRequestDto request);
  AppointmentResponseDto getAppointmentById(Long id);
  AppointmentResponseDto updateAppointmentStatus(Long id, String status);
  AppointmentResponseDto updateAppointment(Long id, UpdateAppointmentRequestDto request);
  void deleteAppointment(Long id);
}
