package com.paulinasprojects.ppbackend.services;

import com.paulinasprojects.ppbackend.dtos.DiagnosisRequestDto;
import com.paulinasprojects.ppbackend.dtos.DiagnosisResponseDto;
import com.paulinasprojects.ppbackend.entities.Appointment;
import com.paulinasprojects.ppbackend.entities.Diagnosis;
import com.paulinasprojects.ppbackend.entities.DoctorProfile;
import com.paulinasprojects.ppbackend.entities.PatientProfile;
import com.paulinasprojects.ppbackend.exceptions.AppointmentNotFoundException;
import com.paulinasprojects.ppbackend.exceptions.DoctorNotFoundException;
import com.paulinasprojects.ppbackend.exceptions.PatientNotFoundException;
import com.paulinasprojects.ppbackend.mappers.DiagnosisMapper;
import com.paulinasprojects.ppbackend.repositories.AppointmentRepository;
import com.paulinasprojects.ppbackend.repositories.DiagnosisRepository;
import com.paulinasprojects.ppbackend.repositories.DoctorProfileRepository;
import com.paulinasprojects.ppbackend.repositories.PatientProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DiagnosisServiceImpl implements DiagnosisService {
  public final DoctorProfileRepository doctorProfileRepository;
  public final PatientProfileRepository patientProfileRepository;
  public final DiagnosisRepository diagnosisRepository;
  public final AppointmentRepository appointmentRepository;
  public final DiagnosisMapper diagnosisMapper;

  @Override
  public DiagnosisResponseDto createDiagnosis(DiagnosisRequestDto request) {
    var doctor = getDoctor(request.getDoctorId());
    var appointment = getAppointment(request.getAppointmentId());

    var patient = getPatient(request.getPatientId());
    var diagnosis = Diagnosis.builder()
            .appointment(appointment)
            .notes(request.getNotes())
            .dateCreated(request.getDateCreated())
            .patient(patient)
            .doctor(doctor)
            .diagnose(request.getDiagnose())
            .build();
    var saved = diagnosisRepository.save(diagnosis);
    return diagnosisMapper.toResponseDto(saved);
  }

  @Override
  public DiagnosisResponseDto updateDiagnosis(Long id, DiagnosisRequestDto request) {
    var diagnosis = getDiagnosis(id);

    if (request.getAppointmentId() != null) {
      var appointment = getAppointment(request.getAppointmentId());
      diagnosis.setAppointment(appointment);
    }

    if (request.getDoctorId() != null) {
      var doctor = getDoctor(request.getDoctorId());
      diagnosis.setDoctor(doctor);
    }
    if (request.getPatientId() != null) {
      var patient = getPatient(request.getPatientId());
      diagnosis.setPatient(patient);
    }
    if (request.getDateCreated() != null) {
      diagnosis.setDateCreated(request.getDateCreated());
    }
    if (request.getNotes() != null) {
      diagnosis.setNotes(request.getNotes());
    }

    var updatedDiagnosis = diagnosisRepository.save(diagnosis);
    return diagnosisMapper.toResponseDto(updatedDiagnosis);
  }

  @Override
  @Transactional(readOnly = true)
  public DiagnosisResponseDto getDiagnosisById(Long id) {
    var diagnosis = getDiagnosis(id);
    return diagnosisMapper.toResponseDto(diagnosis);
  }

  @Override
  public void deleteDiagnosis(Long id) {
    if (!diagnosisRepository.existsById(id)) {
      throw new RuntimeException("Diagnosis not found");
    }
    diagnosisRepository.deleteById(id);
  }

  private Diagnosis getDiagnosis(Long id) {
    return diagnosisRepository.findById(id).orElseThrow(() ->  new RuntimeException("Diagnosis not found"));
  }

  private DoctorProfile getDoctor(Long doctorId) {
    return doctorProfileRepository.findById(doctorId).orElseThrow(() -> new DoctorNotFoundException("Doctor not found"));
  }

  private PatientProfile getPatient(Long patientId) {
    return patientProfileRepository.findById(patientId).orElseThrow(()-> new PatientNotFoundException("Patient not found"));
  }

  private Appointment getAppointment(Long id) {
    return appointmentRepository.findById(id).orElseThrow(() -> new AppointmentNotFoundException("Appointment not found"));
  }

}
