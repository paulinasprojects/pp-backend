package com.paulinasprojects.ppbackend.services;

import com.paulinasprojects.ppbackend.dtos.DiagnosisRequestDto;
import com.paulinasprojects.ppbackend.dtos.DiagnosisResponseDto;

import java.util.List;

public interface DiagnosisService {
  List<DiagnosisResponseDto> getDiagnosesByDoctor(Long doctorId);
  List<DiagnosisResponseDto> getDiagnosesByPatient(Long patientId);
  DiagnosisResponseDto getDiagnosisByAppointment(Long appointmentId);
  DiagnosisResponseDto createDiagnosis(DiagnosisRequestDto request);
  DiagnosisResponseDto updateDiagnosis(Long id, DiagnosisRequestDto request);
  DiagnosisResponseDto getDiagnosisById(Long id);
  void deleteDiagnosis(Long id);
}
