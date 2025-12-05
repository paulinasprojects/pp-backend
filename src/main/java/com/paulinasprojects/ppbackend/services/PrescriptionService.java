package com.paulinasprojects.ppbackend.services;

import com.paulinasprojects.ppbackend.dtos.PrescriptionRenewalDto;
import com.paulinasprojects.ppbackend.dtos.PrescriptionRequestDto;
import com.paulinasprojects.ppbackend.dtos.PrescriptionResponseDto;

import java.util.List;

public interface PrescriptionService {
  List<PrescriptionResponseDto> getPrescriptionsByDoctor(Long doctorId);
  List<PrescriptionResponseDto> getPrescriptionsByPatient(Long patientId);
  List<PrescriptionResponseDto> getActivePrescriptionsByPatient(Long patientId);
  List<PrescriptionResponseDto> getExpiredPrescriptionsByPatientId(Long patientId);
  List<PrescriptionResponseDto> searchPrescriptionsByMedication(String medicationName);
  List<PrescriptionResponseDto> getPrescriptionsByAppointment(Long appointmentId);
  PrescriptionResponseDto renewPrescription(Long id, PrescriptionRenewalDto request);
  PrescriptionResponseDto getPrescriptionByDiagnosisId(Long diagnosisId);
  PrescriptionResponseDto createPrescription(PrescriptionRequestDto request);
  PrescriptionResponseDto updatePrescription(Long id, PrescriptionRequestDto request);
  PrescriptionResponseDto getPrescriptionById(Long id);
  void deletePrescription(Long id);
}
