package com.paulinasprojects.ppbackend.repositories;

import com.paulinasprojects.ppbackend.entities.DoctorProfile;
import com.paulinasprojects.ppbackend.entities.PatientProfile;
import com.paulinasprojects.ppbackend.entities.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
  List<Prescription> findByDoctor(DoctorProfile doctor);
  List<Prescription> findByPatient(PatientProfile patient);
  Optional<Prescription> findByDiagnosisId(Long diagnosisId);
//  List<Prescription> findPrescriptionsByDiagnosis(Diagnosis diagnosis);
}
