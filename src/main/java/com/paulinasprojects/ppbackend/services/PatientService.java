package com.paulinasprojects.ppbackend.services;

import com.paulinasprojects.ppbackend.dtos.PatientProfileDto;
import com.paulinasprojects.ppbackend.dtos.UpdatePatientProfileReq;

public interface PatientService {
  PatientProfileDto addProfileToPatient(Long id, PatientProfileDto dto);
  PatientProfileDto updatePatientProfile(Long id, UpdatePatientProfileReq req);
  void deleteProfile(Long id);
}
