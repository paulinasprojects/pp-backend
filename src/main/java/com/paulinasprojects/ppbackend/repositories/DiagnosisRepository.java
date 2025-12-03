package com.paulinasprojects.ppbackend.repositories;

import com.paulinasprojects.ppbackend.entities.Diagnosis;
import com.paulinasprojects.ppbackend.entities.DoctorProfile;
import com.paulinasprojects.ppbackend.entities.PatientProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DiagnosisRepository extends JpaRepository<Diagnosis, Long> {
  List<Diagnosis> findByDoctor(DoctorProfile doctor);
  List<Diagnosis> findByPatient(PatientProfile patient);

  Optional<Diagnosis> findByAppointmentId(Long appointmentId);
}
