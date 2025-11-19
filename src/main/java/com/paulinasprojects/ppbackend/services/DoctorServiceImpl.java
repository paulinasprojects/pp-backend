package com.paulinasprojects.ppbackend.services;

import com.paulinasprojects.ppbackend.dtos.DoctorProfileDto;
import com.paulinasprojects.ppbackend.dtos.UpdateDoctorProfileReq;
import com.paulinasprojects.ppbackend.entities.DoctorProfile;
import com.paulinasprojects.ppbackend.entities.User;
import com.paulinasprojects.ppbackend.mappers.DoctorMapper;
import com.paulinasprojects.ppbackend.repositories.DoctorProfileRepository;
import com.paulinasprojects.ppbackend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional
public class DoctorServiceImpl implements DoctorService {
  private final UserRepository userRepository;
  private final DoctorProfileRepository doctorProfileRepository;
  private final DoctorMapper doctorMapper;

  @Override
  public DoctorProfileDto addProfileToDoctor(Long userId, DoctorProfileDto doctorProfileDto) {
    var doctor = getDoctor(userId);

    var profile = doctorMapper.toEntity(doctorProfileDto);
    profile.setUser(doctor);

    var savedProfile = doctorProfileRepository.save(profile);
    return doctorMapper.toDoctorProfileDto(savedProfile);
  }

  @Override
  public DoctorProfileDto updateDoctorProfile(Long userId, UpdateDoctorProfileReq req) {
    var doctor = getDoctor(userId);
    var profile = doctor.getDoctorProfile();
    if (profile == null) {
      profile = DoctorProfile.builder()
              .user(doctor)
              .bio(req.getBio())
              .address(req.getAddress())
              .hospital(req.getHospital())
              .specialization(req.getSpecialization())
              .phoneNumber(req.getPhoneNumber())
              .dateOfBirth(req.getDateOfBirth())
              .registeredDate(req.getRegisteredDate() != null ? req.getRegisteredDate() : LocalDate.now())
              .build();
      doctor.setDoctorProfile(profile);
    } else {
      doctorMapper.updateDoctorProfile(req, profile);

      if (req.getRegisteredDate() != null) {
        profile.setRegisteredDate(req.getRegisteredDate());
      }
    }
    doctorProfileRepository.save(profile);
    return doctorMapper.toDoctorProfileDto(profile);
  }

  @Override
  public void deleteProfile(Long userId) {
    var doctor = getDoctor(userId);
    var profile = doctor.getDoctorProfile();
    if (profile == null) {
      throw new RuntimeException("Profile not found");
    }
    doctor.setDoctorProfile(null);
    doctorProfileRepository.delete(profile);
  }

  private User getDoctor(Long userId) {
    return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Doctor not found"));
  }
}
