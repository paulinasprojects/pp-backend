package com.paulinasprojects.ppbackend.repositories;

import com.paulinasprojects.ppbackend.entities.Diagnosis;
import com.paulinasprojects.ppbackend.entities.PatientProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiagnosisRepository extends JpaRepository<Diagnosis, Long> {
  List<Diagnosis> findByPatient(PatientProfile patient);
}
