package com.paulinasprojects.ppbackend.services;

import com.paulinasprojects.ppbackend.common.PaginatedResponseDto;
import com.paulinasprojects.ppbackend.dtos.DiagnosisRequestDto;
import com.paulinasprojects.ppbackend.dtos.DiagnosisResponseDto;
import com.paulinasprojects.ppbackend.dtos.UpdateDiagnosisRequestDto;


public interface DiagnosisService {
  PaginatedResponseDto<DiagnosisResponseDto> getDiagnosesByDoctor(Long doctorId, Integer page, Integer size, String sortBy, String sortDirection);
  PaginatedResponseDto<DiagnosisResponseDto> getDiagnosesByPatient(Long patientId, Integer page,  Integer size, String sortBy, String sortDirection);
  DiagnosisResponseDto getDiagnosisByAppointment(Long appointmentId);
  DiagnosisResponseDto createDiagnosis(DiagnosisRequestDto request);
  DiagnosisResponseDto updateDiagnosis(Long id, UpdateDiagnosisRequestDto request);
  DiagnosisResponseDto getDiagnosisById(Long id);
  void deleteDiagnosis(Long id);
}
