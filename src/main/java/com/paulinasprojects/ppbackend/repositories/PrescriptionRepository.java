package com.paulinasprojects.ppbackend.repositories;

import com.paulinasprojects.ppbackend.entities.DoctorProfile;
import com.paulinasprojects.ppbackend.entities.PatientProfile;
import com.paulinasprojects.ppbackend.entities.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
  Page<Prescription> findByDoctor(DoctorProfile doctor, Pageable pageable);
  Page<Prescription> findByPatient(PatientProfile patient, Pageable pageable);
  Optional<Prescription> findByDiagnosisId(Long diagnosisId);
  @Query("SELECT p FROM Prescription p WHERE p.patient = :patient " +
          "AND p.startDate <= :date " +
          "AND (p.endDate IS NULL OR p.endDate >= :date)")
  Page<Prescription> findActiveByPatientAndDate(
          @Param("patient") PatientProfile patient,
          @Param("date") LocalDate date,
          Pageable pageable
  );

  @Query("SELECT p FROM Prescription p WHERE p.patient.user.id = :patientId " +
          "AND p.endDate < :today")
  Page<Prescription> findExpiredByPatientId(
          @Param("patientId") Long patientId,
          @Param("today") LocalDate today,
          Pageable pageable
  );

  @Query("SELECT p FROM Prescription p WHERE " +
          "LOWER(p.medicationName) LIKE LOWER(CONCAT('%', :medicationName, '%'))")
  Page<Prescription> findByMedicationNameContainingIgnoreCase(
          @Param("medicationName") String medicationName,
          Pageable pageable
  );

  @Query("SELECT p FROM Prescription p WHERE p.diagnosis.appointment.id = :appointmentId")
  Page<Prescription> findByAppointmentId(
          @Param("appointmentId") Long appointmentId,
          Pageable pageable
  );

  //  List<Prescription> findPrescriptionsByDiagnosis(Diagnosis diagnosis);
}
