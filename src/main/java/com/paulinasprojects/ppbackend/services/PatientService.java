package com.paulinasprojects.ppbackend.services;

import com.paulinasprojects.ppbackend.common.PaginatedResponseDto;
import com.paulinasprojects.ppbackend.dtos.PatientProfileDto;
import com.paulinasprojects.ppbackend.dtos.UpdatePatientProfileReq;

public interface PatientService {
  PaginatedResponseDto<PatientProfileDto> getAllPatients(Integer page, Integer size, String sortBy, String sortDirection);
  PatientProfileDto addProfileToPatient(Long id, PatientProfileDto dto);
  PatientProfileDto updatePatientProfile(Long id, UpdatePatientProfileReq req);
  void deleteProfile(Long id);
}
