package com.paulinasprojects.ppbackend.mappers;

import com.paulinasprojects.ppbackend.dtos.DiagnosisResponseDto;
import com.paulinasprojects.ppbackend.dtos.DoctorInfoDto;
import com.paulinasprojects.ppbackend.dtos.PatientInfoDto;
import com.paulinasprojects.ppbackend.entities.Diagnosis;
import com.paulinasprojects.ppbackend.entities.DoctorProfile;
import com.paulinasprojects.ppbackend.entities.PatientProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DiagnosisMapper {
  @Mapping(target = "doctor", source = "doctor", qualifiedByName = "toDoctorInfoDto")
  @Mapping(target = "patient", source = "patient", qualifiedByName = "toPatientInfoDto")
  @Mapping(target = "appointmentId", source = "appointment.id")
  DiagnosisResponseDto toResponseDto(Diagnosis diagnosis);

  List<DiagnosisResponseDto> toResponseDtoList(List<Diagnosis> diagnoses);

  @Named("toDoctorInfoDto")
  @Mapping(target = "name", source = "user.name")
  @Mapping(target = "email", source = "user.email")
  @Mapping(target = "specialization", source = "specialization")
  DoctorInfoDto toDoctorInfoDto(DoctorProfile doctor);

  @Named("toPatientInfoDto")
  @Mapping(target = "name", source = "user.name")
  @Mapping(target = "email", source = "user.email")
  PatientInfoDto toPatientInfoDto(PatientProfile patient);
}
