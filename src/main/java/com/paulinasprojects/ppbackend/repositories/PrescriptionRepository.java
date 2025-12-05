package com.paulinasprojects.ppbackend.repositories;

import com.paulinasprojects.ppbackend.entities.DoctorProfile;
import com.paulinasprojects.ppbackend.entities.PatientProfile;
import com.paulinasprojects.ppbackend.entities.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
  List<Prescription> findByDoctor(DoctorProfile doctor);
  List<Prescription> findByPatient(PatientProfile patient);
  Optional<Prescription> findByDiagnosisId(Long diagnosisId);
  @Query("SELECT p FROM Prescription p WHERE p.patient = :patient " +
          "AND p.startDate <= :date " +
          "AND (p.endDate IS NULL OR p.endDate >= :date)")
  List<Prescription> findActiveByPatientAndDate(
          @Param("patient") PatientProfile patient,
          @Param("date") LocalDate date);

  @Query("SELECT p FROM Prescription p WHERE p.patient.user.id = :patientId " +
          "AND p.endDate < :today")
  List<Prescription> findExpiredByPatientId(@Param("patientId") Long patientId, @Param("today") LocalDate today);

  @Query("SELECT p FROM Prescription p WHERE " +
          "LOWER(p.medicationName) LIKE LOWER(CONCAT('%', :medicationName, '%'))")
  List<Prescription> findByMedicationNameContainingIgnoreCase(@Param("medicationName") String medicationName);

  @Query("SELECT p FROM Prescription p WHERE p.diagnosis.appointment.id = :appointmentId")
  List<Prescription> findByAppointmentId(@Param("appointmentId") Long appointmentId);

  //  List<Prescription> findPrescriptionsByDiagnosis(Diagnosis diagnosis);
}
