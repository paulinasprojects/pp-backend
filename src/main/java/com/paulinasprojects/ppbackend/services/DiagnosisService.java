package com.paulinasprojects.ppbackend.services;

import com.paulinasprojects.ppbackend.dtos.DiagnosisRequestDto;
import com.paulinasprojects.ppbackend.dtos.DiagnosisResponseDto;

public interface DiagnosisService {
  DiagnosisResponseDto createDiagnosis(DiagnosisRequestDto request);
  DiagnosisResponseDto updateDiagnosis(Long id, DiagnosisRequestDto request);
  DiagnosisResponseDto getDiagnosisById(Long id);
  void deleteDiagnosis(Long id);
}
