package com.paulinasprojects.ppbackend.repositories;

import com.paulinasprojects.ppbackend.entities.Diagnosis;
import com.paulinasprojects.ppbackend.entities.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
  List<Prescription> findByDiagnosis(Diagnosis diagnosis);
}
