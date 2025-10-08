package com.paulinasprojects.ppbackend.repositories;

import com.paulinasprojects.ppbackend.entities.PatientProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientProfileRepository extends JpaRepository<PatientProfile, Long> {
}
