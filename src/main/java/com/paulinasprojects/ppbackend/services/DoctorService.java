package com.paulinasprojects.ppbackend.services;

import com.paulinasprojects.ppbackend.common.PaginatedResponseDto;
import com.paulinasprojects.ppbackend.dtos.DoctorProfileDto;
import com.paulinasprojects.ppbackend.dtos.UpdateDoctorProfileReq;

public interface DoctorService {
  PaginatedResponseDto<DoctorProfileDto> getAllDoctors(Integer page, Integer size, String sortBy, String sortDirection);
  DoctorProfileDto addProfileToDoctor(Long id, DoctorProfileDto doctorProfileDto);
  DoctorProfileDto updateDoctorProfile(Long id, UpdateDoctorProfileReq req);
  void deleteProfile(Long id);
}
