package com.paulinasprojects.ppbackend.services;

import com.paulinasprojects.ppbackend.dtos.DoctorProfileDto;
import com.paulinasprojects.ppbackend.dtos.UpdateDoctorProfileReq;
import com.paulinasprojects.ppbackend.entities.DoctorProfile;
import com.paulinasprojects.ppbackend.entities.Role;
import com.paulinasprojects.ppbackend.entities.User;
import com.paulinasprojects.ppbackend.exceptions.DoctorNotFoundException;
import com.paulinasprojects.ppbackend.exceptions.DoctorProfileAlreadyExistsException;
import com.paulinasprojects.ppbackend.exceptions.NotDoctorRoleException;
import com.paulinasprojects.ppbackend.exceptions.ProfileNotFoundException;
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

    if (doctor.getRole() != Role.DOCTOR) {
      throw new NotDoctorRoleException("This user is not a doctor");
    }

    if (doctor.getDoctorProfile() != null) {
      throw new DoctorProfileAlreadyExistsException("Doctor already has a profile");
    }

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
              .registeredDate(LocalDate.now())
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
      throw new ProfileNotFoundException("Profile not found");
    }
    doctor.setDoctorProfile(null);
    doctorProfileRepository.delete(profile);
  }

  private User getDoctor(Long userId) {
    return userRepository.findById(userId).orElseThrow(() -> new DoctorNotFoundException("Doctor not found"));
  }

}
