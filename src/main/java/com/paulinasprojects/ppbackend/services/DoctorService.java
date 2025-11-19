package com.paulinasprojects.ppbackend.services;

import com.paulinasprojects.ppbackend.dtos.DoctorProfileDto;
import com.paulinasprojects.ppbackend.dtos.UpdateDoctorProfileReq;

public interface DoctorService {
  DoctorProfileDto addProfileToDoctor(Long id, DoctorProfileDto doctorProfileDto);
  DoctorProfileDto updateDoctorProfile(Long id, UpdateDoctorProfileReq req);
  void deleteProfile(Long id);
}
