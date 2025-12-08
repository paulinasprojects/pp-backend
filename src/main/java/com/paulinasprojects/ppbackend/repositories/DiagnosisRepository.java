package com.paulinasprojects.ppbackend.repositories;

import com.paulinasprojects.ppbackend.entities.Diagnosis;
import com.paulinasprojects.ppbackend.entities.DoctorProfile;
import com.paulinasprojects.ppbackend.entities.PatientProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiagnosisRepository extends JpaRepository<Diagnosis, Long> {
  Page<Diagnosis> findByDoctor(DoctorProfile doctor, Pageable pageable);
  Page<Diagnosis> findByPatient(PatientProfile patient, Pageable pageable);
  List<Diagnosis> findByDoctorAndPatient(DoctorProfile doctor, PatientProfile patient);

  Optional<Diagnosis> findByAppointmentId(Long appointmentId);
}
