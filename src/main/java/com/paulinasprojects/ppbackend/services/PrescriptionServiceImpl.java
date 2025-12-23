package com.paulinasprojects.ppbackend.services;

import com.paulinasprojects.ppbackend.common.PaginatedResponseDto;
import com.paulinasprojects.ppbackend.dtos.PrescriptionRenewalDto;
import com.paulinasprojects.ppbackend.dtos.PrescriptionRequestDto;
import com.paulinasprojects.ppbackend.dtos.PrescriptionResponseDto;
import com.paulinasprojects.ppbackend.dtos.UpdatePrescriptionRequestDto;
import com.paulinasprojects.ppbackend.entities.Diagnosis;
import com.paulinasprojects.ppbackend.entities.DoctorProfile;
import com.paulinasprojects.ppbackend.entities.PatientProfile;
import com.paulinasprojects.ppbackend.entities.Prescription;
import com.paulinasprojects.ppbackend.exceptions.DiagnoseNotFoundException;
import com.paulinasprojects.ppbackend.exceptions.DoctorNotFoundException;
import com.paulinasprojects.ppbackend.exceptions.PatientNotFoundException;
import com.paulinasprojects.ppbackend.exceptions.PrescriptionNotFoundException;
import com.paulinasprojects.ppbackend.mappers.PrescriptionMapper;
import com.paulinasprojects.ppbackend.repositories.DiagnosisRepository;
import com.paulinasprojects.ppbackend.repositories.DoctorProfileRepository;
import com.paulinasprojects.ppbackend.repositories.PatientProfileRepository;
import com.paulinasprojects.ppbackend.repositories.PrescriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional
public class PrescriptionServiceImpl implements PrescriptionService {
  public final DoctorProfileRepository doctorProfileRepository;
  public final PatientProfileRepository patientProfileRepository;
  public final PrescriptionRepository prescriptionRepository;
  public final DiagnosisRepository diagnosisRepository;
  public final PrescriptionMapper prescriptionMapper;

  @Override
  @Transactional(readOnly = true)
  public PaginatedResponseDto<PrescriptionResponseDto> getPrescriptionsByDoctor(Long doctorId, Integer page, Integer size, String sortBy, String sortDirection) {
    var doctor = getDoctor(doctorId);
    Sort sort = sortDirection.equalsIgnoreCase("asc")
            ? Sort.by(sortBy).ascending()
            : Sort.by(sortBy).descending();
    Pageable pageable = PageRequest.of(page, size, sort);
    Page<Prescription> prescriptions = prescriptionRepository.findByDoctor(doctor, pageable);

    Page<PrescriptionResponseDto> mapped = prescriptions.map(prescriptionMapper::toResponseDto);
    return toPaginatedResponse(mapped);
  }

  @Override
  @Transactional(readOnly = true)
  public PaginatedResponseDto<PrescriptionResponseDto> getPrescriptionsByPatient(Long patientId, Integer page, Integer size, String sortBy, String sortDirection) {
    var patient = getPatient(patientId);
    Sort sort = sortDirection.equalsIgnoreCase("asc")
            ? Sort.by(sortBy).ascending()
            : Sort.by(sortBy).descending();
    Pageable pageable = PageRequest.of(page, size, sort);
    Page<Prescription> prescriptions = prescriptionRepository.findByPatient(patient, pageable);
    Page<PrescriptionResponseDto> mapped = prescriptions.map(prescriptionMapper::toResponseDto);
    return toPaginatedResponse(mapped);
  }

  @Override
  @Transactional(readOnly = true)
  public PaginatedResponseDto<PrescriptionResponseDto> getActivePrescriptionsByPatient(Long patientId, Integer page, Integer size, String sortBy, String sortDirection) {
    var patient = getPatient(patientId);
    var today = LocalDate.now();
    Sort sort = sortDirection.equalsIgnoreCase("asc")
            ? Sort.by(sortBy).ascending()
            : Sort.by(sortBy).descending();
    Pageable pageable = PageRequest.of(page, size, sort);
    Page<Prescription> prescriptions = prescriptionRepository.findActiveByPatientAndDate(patient, today, pageable);
    Page<PrescriptionResponseDto> mapped = prescriptions.map(prescriptionMapper::toResponseDto);
    return toPaginatedResponse(mapped);
  }

  @Override
  @Transactional(readOnly = true)
  public PaginatedResponseDto<PrescriptionResponseDto> getExpiredPrescriptionsByPatientId(Long patientId, Integer page, Integer size, String sortBy, String sortDirection) {
    var today = LocalDate.now();
    Sort sort = sortDirection.equalsIgnoreCase("asc")
            ? Sort.by(sortBy).ascending()
            : Sort.by(sortBy).descending();
    Pageable pageable = PageRequest.of(page, size, sort);
    Page<Prescription> prescriptions = prescriptionRepository.findExpiredByPatientId(patientId, today, pageable);
    Page<PrescriptionResponseDto> mapped = prescriptions.map(prescriptionMapper::toResponseDto);
    return toPaginatedResponse(mapped);
  }

  @Override
  @Transactional(readOnly = true)
  public PaginatedResponseDto<PrescriptionResponseDto> searchPrescriptionsByMedication(String medicationName, Integer page, Integer size, String sortBy, String sortDirection) {
    Sort sort = sortDirection.equalsIgnoreCase("asc")
            ? Sort.by(sortBy).ascending()
            : Sort.by(sortBy).descending();
    Pageable pageable = PageRequest.of(page, size, sort);
    Page<Prescription> prescriptions = prescriptionRepository.findByMedicationNameContainingIgnoreCase(medicationName, pageable);
    Page<PrescriptionResponseDto> mapped = prescriptions.map(prescriptionMapper::toResponseDto);
    return toPaginatedResponse(mapped);
  }

  @Override
  @Transactional(readOnly = true)
  public PaginatedResponseDto<PrescriptionResponseDto> getPrescriptionsByAppointment(Long appointmentId, Integer page, Integer size, String sortBy, String sortDirection) {
    Sort sort = sortDirection.equalsIgnoreCase("asc")
            ? Sort.by(sortBy).ascending()
            : Sort.by(sortBy).descending();
    Pageable pageable = PageRequest.of(page, size, sort);
    Page<Prescription> prescriptions = prescriptionRepository.findByAppointmentId(appointmentId, pageable);
    Page<PrescriptionResponseDto> mapped = prescriptions.map(prescriptionMapper::toResponseDto);
    return toPaginatedResponse(mapped);
  }

  @Override
  public PrescriptionResponseDto renewPrescription(Long id, PrescriptionRenewalDto request) {
    var prescription = getPrescription(id);
    prescription.setEndDate(request.getNewEndDate());
    prescription.setInstructions(request.getNewInstructions());
    var updatedPrescription = prescriptionRepository.save(prescription);
    return prescriptionMapper.toResponseDto(updatedPrescription);
  }

  @Override
  @Transactional(readOnly = true)
  public PrescriptionResponseDto getPrescriptionByDiagnosisId(Long diagnosisId) {
    var prescription = prescriptionRepository.findByDiagnosisId(diagnosisId).orElseThrow(() -> new PrescriptionNotFoundException("Prescription not found for diagnosis with id " + diagnosisId));
    return prescriptionMapper.toResponseDto(prescription);
  }

  @Override
  public PrescriptionResponseDto createPrescription(PrescriptionRequestDto request) {
    var doctor = getDoctor(request.getDoctorId());
    var patient = getPatient(request.getPatientId());
    var diagnosis = getDiagnosis(request.getDiagnosisId());
    if (!diagnosis.getDoctor().getId().equals(doctor.getId())) {
      throw new IllegalArgumentException("Doctor cannot prescribe for a diagnosis created by another doctor");
    }

    if (!diagnosis.getPatient().getId().equals(patient.getId())) {
      throw new IllegalArgumentException("Diagnosis does not belong to this patient");
    }

    if (prescriptionRepository.findByDiagnosisId(request.getDiagnosisId()).isPresent()) {
      throw new IllegalStateException("A prescription already exists for this diagnosis.");
    }

    var prescription = Prescription.builder()
            .diagnosis(diagnosis)
            .dosage(request.getDosage())
            .medicationName(request.getMedicationName())
            .patient(patient)
            .doctor(doctor)
            .instructions(request.getInstructions())
            .startDate(request.getStartDate())
            .endDate(request.getEndDate())
            .build();
    var saved = prescriptionRepository.save(prescription);
    return prescriptionMapper.toResponseDto(saved);
  }

  @Override
  public PrescriptionResponseDto updatePrescription(Long id, UpdatePrescriptionRequestDto request) {
    var prescription = getPrescription(id);
    prescriptionMapper.updatePrescription(request, prescription);
    var updatedPrescription = prescriptionRepository.save(prescription);
    return prescriptionMapper.toResponseDto(updatedPrescription);
  }

  @Override
  @Transactional(readOnly = true)
  public PrescriptionResponseDto getPrescriptionById(Long id) {
    var prescription = getPrescription(id);
    return prescriptionMapper.toResponseDto(prescription);
  }

  @Override
  public void deletePrescription(Long id) {
    if (!prescriptionRepository.existsById(id)) {
      throw new PrescriptionNotFoundException("Prescription not found");
    }
    prescriptionRepository.deleteById(id);
  }

  private DoctorProfile getDoctor(Long doctorId) {
    return doctorProfileRepository.findById(doctorId).orElseThrow(() -> new DoctorNotFoundException("Doctor not found"));
  }

  private PatientProfile getPatient(Long patientId) {
    return patientProfileRepository.findById(patientId).orElseThrow(()-> new PatientNotFoundException("Patient not found"));
  }

  private Prescription getPrescription(Long id) {
    return prescriptionRepository.findById(id).orElseThrow(() -> new PrescriptionNotFoundException("Prescription not found"));
  }

  private Diagnosis getDiagnosis(Long id) {
    return diagnosisRepository.findById(id).orElseThrow(() -> new DiagnoseNotFoundException("Diagnosis not found"));
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
