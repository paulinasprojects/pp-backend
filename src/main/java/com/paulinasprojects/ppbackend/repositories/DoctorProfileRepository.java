package com.paulinasprojects.ppbackend.repositories;

import com.paulinasprojects.ppbackend.entities.DoctorProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorProfileRepository extends JpaRepository<DoctorProfile, Long> {
}
