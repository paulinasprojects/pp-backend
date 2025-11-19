package com.paulinasprojects.ppbackend.mappers;

import com.paulinasprojects.ppbackend.dtos.PatientProfileDto;
import com.paulinasprojects.ppbackend.dtos.UpdatePatientProfileReq;
import com.paulinasprojects.ppbackend.entities.PatientProfile;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PatientMapper {
  void updatePatientProfile(UpdatePatientProfileReq req, @MappingTarget PatientProfile patientProfile);
  PatientProfileDto toPatientProfileDto(PatientProfile patientProfile);
  PatientProfile toEntity(PatientProfileDto patientProfileDto);
}
