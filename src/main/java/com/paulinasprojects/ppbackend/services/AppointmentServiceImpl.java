package com.paulinasprojects.ppbackend.services;

import com.paulinasprojects.ppbackend.dtos.AppointmentRequestDto;
import com.paulinasprojects.ppbackend.dtos.AppointmentResponseDto;
import com.paulinasprojects.ppbackend.entities.Appointment;
import com.paulinasprojects.ppbackend.entities.AppointmentStatus;
import com.paulinasprojects.ppbackend.entities.DoctorProfile;
import com.paulinasprojects.ppbackend.entities.PatientProfile;
import com.paulinasprojects.ppbackend.exceptions.AppointmentNotFoundException;
import com.paulinasprojects.ppbackend.exceptions.DoctorNotFoundException;
import com.paulinasprojects.ppbackend.exceptions.PatientNotFoundException;
import com.paulinasprojects.ppbackend.mappers.AppointmentMapper;
import com.paulinasprojects.ppbackend.repositories.AppointmentRepository;
import com.paulinasprojects.ppbackend.repositories.DoctorProfileRepository;
import com.paulinasprojects.ppbackend.repositories.PatientProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AppointmentServiceImpl implements AppointmentService {
  public final DoctorProfileRepository doctorProfileRepository;
  public final AppointmentRepository appointmentRepository;
  public final PatientProfileRepository patientProfileRepository;
  public final AppointmentMapper appointmentMapper;

  @Override
  @Transactional(readOnly = true)
  public List<AppointmentResponseDto> getAppointmentsByDoctor(Long doctorId) {
    DoctorProfile doctor = getDoctor(doctorId);
    List<Appointment> appointments = appointmentRepository.findByDoctor(doctor);
    return appointmentMapper.toResponseDtoList(appointments);
  }

  @Override
  @Transactional(readOnly = true)
  public List<AppointmentResponseDto> getAppointmentsByPatient(Long patientId) {
    PatientProfile patient = getPatient(patientId);
    List<Appointment> appointments = appointmentRepository.findByPatient(patient);
    return appointmentMapper.toResponseDtoList(appointments);
  }

  @Override
  public AppointmentResponseDto createAppointment(AppointmentRequestDto request) {
    DoctorProfile doctor = getDoctor(request.getDoctorId());
    PatientProfile patient = getPatient(request.getPatientId());
    Appointment appointment = Appointment.builder()
            .appointmentDate(request.getAppointmentDate())
            .notes(request.getNotes())
            .doctor(doctor)
            .patient(patient)
            .status(AppointmentStatus.SCHEDULED)
            .build();
      var saved = appointmentRepository.save(appointment);
      return appointmentMapper.toResponseDto(saved);
  }

  @Override
  @Transactional(readOnly = true)
  public AppointmentResponseDto getAppointmentById(Long id) {
    var appointment = getAppointment(id);
    return appointmentMapper.toResponseDto(appointment);
  }

  @Override
  public AppointmentResponseDto updateAppointmentStatus(Long id, String status) {
    var appointment = getAppointment(id);
    try {
      var appointmentStatus = AppointmentStatus.valueOf(status.toUpperCase());
      appointment.setStatus(appointmentStatus);
      var updatedAppointment = appointmentRepository.save(appointment);
      return appointmentMapper.toResponseDto(updatedAppointment);
    }catch (IllegalArgumentException e) {
        throw new RuntimeException("Invalid appointment status" + status);
    }
  }

  @Override
  public AppointmentResponseDto updateAppointment(Long id, AppointmentRequestDto request) {
    var appointment = getAppointment(id);
    if (request.getDoctorId() != null) {
      DoctorProfile doctor = getDoctor(request.getDoctorId());
      appointment.setDoctor(doctor);
    }
    if (request.getPatientId() != null) {
      PatientProfile patient = getPatient(request.getPatientId());
      appointment.setPatient(patient);
    }
    if (request.getAppointmentDate() != null) {
      appointment.setAppointmentDate(request.getAppointmentDate());
    }

    if (request.getNotes() != null) {
      appointment.setNotes(request.getNotes());
    }
    var updatedAppointment = appointmentRepository.save(appointment);
    return appointmentMapper.toResponseDto(updatedAppointment);
  }

  @Override
  public void deleteAppointment(Long id) {
      if (!appointmentRepository.existsById(id)) {
        throw new AppointmentNotFoundException("Appointment not found");
      }
      appointmentRepository.deleteById(id);
  }

  private DoctorProfile getDoctor(Long doctorId) {
    return doctorProfileRepository.findById(doctorId).orElseThrow(() -> new DoctorNotFoundException("Doctor not found"));
  }

  private PatientProfile getPatient(Long patientId) {
    return patientProfileRepository.findById(patientId).orElseThrow(() -> new PatientNotFoundException("Patient not found"));
  }

  private Appointment getAppointment(Long id) {
    return appointmentRepository.findById(id).orElseThrow(() -> new AppointmentNotFoundException("Appointment not found"));
  }

}
