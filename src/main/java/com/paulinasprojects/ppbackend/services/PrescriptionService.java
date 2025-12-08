package com.paulinasprojects.ppbackend.services;

import com.paulinasprojects.ppbackend.common.PaginatedResponseDto;
import com.paulinasprojects.ppbackend.dtos.PrescriptionRenewalDto;
import com.paulinasprojects.ppbackend.dtos.PrescriptionRequestDto;
import com.paulinasprojects.ppbackend.dtos.PrescriptionResponseDto;


public interface PrescriptionService {
  PaginatedResponseDto<PrescriptionResponseDto> getPrescriptionsByDoctor(Long doctorId, Integer page, Integer size, String sortBy, String sortDirection);
  PaginatedResponseDto<PrescriptionResponseDto> getPrescriptionsByPatient(Long patientId, Integer page, Integer size, String sortBy, String sortDirection);
  PaginatedResponseDto<PrescriptionResponseDto> getActivePrescriptionsByPatient(Long patientId, Integer page, Integer size, String sortBy, String sortDirection);
  PaginatedResponseDto<PrescriptionResponseDto> getExpiredPrescriptionsByPatientId(Long patientId, Integer page, Integer size, String sortBy, String sortDirection);
  PaginatedResponseDto<PrescriptionResponseDto> searchPrescriptionsByMedication(String medicationName, Integer page, Integer size, String sortBy, String sortDirection);
  PaginatedResponseDto<PrescriptionResponseDto> getPrescriptionsByAppointment(Long appointmentId, Integer page, Integer size, String sortBy, String sortDirection);
  PrescriptionResponseDto renewPrescription(Long id, PrescriptionRenewalDto request);
  PrescriptionResponseDto getPrescriptionByDiagnosisId(Long diagnosisId);
  PrescriptionResponseDto createPrescription(PrescriptionRequestDto request);
  PrescriptionResponseDto updatePrescription(Long id, PrescriptionRequestDto request);
  PrescriptionResponseDto getPrescriptionById(Long id);
  void deletePrescription(Long id);
}
