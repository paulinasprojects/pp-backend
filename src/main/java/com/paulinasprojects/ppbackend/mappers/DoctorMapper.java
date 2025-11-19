package com.paulinasprojects.ppbackend.mappers;

import com.paulinasprojects.ppbackend.dtos.DoctorProfileDto;
import com.paulinasprojects.ppbackend.dtos.UpdateDoctorProfileReq;
import com.paulinasprojects.ppbackend.entities.DoctorProfile;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy =  NullValuePropertyMappingStrategy.IGNORE)
public interface DoctorMapper {
  void updateDoctorProfile(UpdateDoctorProfileReq req, @MappingTarget DoctorProfile doctorProfile);
  DoctorProfileDto toDoctorProfileDto(DoctorProfile doctorProfile);
  DoctorProfile toEntity(DoctorProfileDto doctorProfileDto);
}
