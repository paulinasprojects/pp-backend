package com.paulinasprojects.ppbackend.repositories;

import com.paulinasprojects.ppbackend.entities.Appointment;
import com.paulinasprojects.ppbackend.entities.DoctorProfile;
import com.paulinasprojects.ppbackend.entities.PatientProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
  Page<Appointment> findByDoctor(DoctorProfile doctor, Pageable pageable);
  Page<Appointment> findByPatient(PatientProfile patient, Pageable pageable);
}
