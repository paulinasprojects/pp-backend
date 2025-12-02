package com.paulinasprojects.ppbackend.services;

import com.paulinasprojects.ppbackend.dtos.PatientProfileDto;
import com.paulinasprojects.ppbackend.dtos.UpdatePatientProfileReq;
import com.paulinasprojects.ppbackend.entities.PatientProfile;
import com.paulinasprojects.ppbackend.entities.User;
import com.paulinasprojects.ppbackend.exceptions.PatientNotFoundException;
import com.paulinasprojects.ppbackend.mappers.PatientMapper;
import com.paulinasprojects.ppbackend.repositories.PatientProfileRepository;
import com.paulinasprojects.ppbackend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional
public class PatientServiceImpl implements PatientService {
  private final UserRepository userRepository;
  private final PatientProfileRepository patientProfileRepository;
  private final PatientMapper patientMapper;

  @Override
  public PatientProfileDto addProfileToPatient(Long userId, PatientProfileDto patientProfileDto) {
    var patient = getPatient(userId);

    var profile = patientMapper.toEntity(patientProfileDto);
    profile.setUser(patient);

    var savedProfile = patientProfileRepository.save(profile);
    return patientMapper.toPatientProfileDto(savedProfile);
  }

  @Override
  public PatientProfileDto updatePatientProfile(Long userId, UpdatePatientProfileReq req) {
    var patient = getPatient(userId);
    var profile = patient.getPatientProfile();
    if (profile == null) {
      profile = PatientProfile.builder()
              .user(patient)
              .address(req.getAddress())
              .bio(req.getBio())
              .phoneNumber(req.getPhoneNumber())
              .dateOfBirth(req.getDateOfBirth())
              .registeredDate(req.getRegisteredDate() != null ? req.getRegisteredDate() : LocalDate.now())
              .build();
      patient.setPatientProfile(profile);
    } else {
      patientMapper.updatePatientProfile(req, profile);
      if (req.getRegisteredDate() != null) {
        profile.setRegisteredDate(req.getRegisteredDate());
      }
    }
    patientProfileRepository.save(profile);

    return patientMapper.toPatientProfileDto(profile);
  }

  @Override
  public void deleteProfile(Long userId) {
    var patient = getPatient(userId);
    var profile = patient.getPatientProfile();
    if (profile == null) {
      throw new RuntimeException("Profile not found");
    }
    patient.setPatientProfile(null);
    patientProfileRepository.delete(profile);
  }


  private User getPatient(Long userId) {
    return userRepository.findById(userId).orElseThrow(() -> new PatientNotFoundException("Patient not found"));
  }
}
