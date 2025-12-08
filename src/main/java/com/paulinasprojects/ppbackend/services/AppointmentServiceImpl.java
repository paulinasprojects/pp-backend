package com.paulinasprojects.ppbackend.services;

import com.paulinasprojects.ppbackend.common.PaginatedResponseDto;
import com.paulinasprojects.ppbackend.dtos.AppointmentRequestDto;
import com.paulinasprojects.ppbackend.dtos.AppointmentResponseDto;
import com.paulinasprojects.ppbackend.entities.Appointment;
import com.paulinasprojects.ppbackend.entities.AppointmentStatus;
import com.paulinasprojects.ppbackend.entities.DoctorProfile;
import com.paulinasprojects.ppbackend.entities.PatientProfile;
import com.paulinasprojects.ppbackend.exceptions.AppointmentNotFoundException;
import com.paulinasprojects.ppbackend.exceptions.DoctorNotFoundException;
import com.paulinasprojects.ppbackend.exceptions.InvalidStatusException;
import com.paulinasprojects.ppbackend.exceptions.PatientNotFoundException;
import com.paulinasprojects.ppbackend.mappers.AppointmentMapper;
import com.paulinasprojects.ppbackend.repositories.AppointmentRepository;
import com.paulinasprojects.ppbackend.repositories.DoctorProfileRepository;
import com.paulinasprojects.ppbackend.repositories.PatientProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
  public PaginatedResponseDto<AppointmentResponseDto> getAppointmentsByDoctor(Long doctorId, Integer page, Integer size, String sortBy, String sortDirection) {
    var doctor = getDoctor(doctorId);
    Sort sort = sortDirection.equalsIgnoreCase("asc")
            ? Sort.by(sortBy).ascending()
            : Sort.by(sortBy).descending();
    Pageable pageable = PageRequest.of(page, size, sort);
    Page<Appointment> appointments = appointmentRepository.findByDoctor(doctor, pageable);
    Page<AppointmentResponseDto> mapped = appointments.map(appointmentMapper::toResponseDto);
    return  toPaginatedResponse(mapped);
  }

  @Override
  @Transactional(readOnly = true)
  public PaginatedResponseDto<AppointmentResponseDto> getAppointmentsByPatient(Long patientId,  Integer page, Integer size, String sortBy, String sortDirection) {
    var patient = getPatient(patientId);
    Sort sort = sortDirection.equalsIgnoreCase("asc")
            ? Sort.by(sortBy).ascending()
            : Sort.by(sortBy).descending();
    Pageable pageable = PageRequest.of(page, size, sort);
    Page<Appointment> appointments = appointmentRepository.findByPatient(patient, pageable);
    Page<AppointmentResponseDto> mapped = appointments.map(appointmentMapper::toResponseDto);
    return  toPaginatedResponse(mapped);
  }

  @Override
  public AppointmentResponseDto createAppointment(AppointmentRequestDto request) {
    var doctor = getDoctor(request.getDoctorId());
    var patient = getPatient(request.getPatientId());
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
        throw new InvalidStatusException("Invalid appointment status " + status);
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

  private <T> PaginatedResponseDto<T> toPaginatedResponse(Page<T> page) {
    return new PaginatedResponseDto<>(
            page.getContent(),
            page.getNumber(),
            page.getSize(),
            page.getTotalElements(),
            page.getTotalPages(),
            page.isLast()
    );
  }
}
