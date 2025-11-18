package com.paulinasprojects.ppbackend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "doctor_profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorProfile {

  @Id
  @Column(name = "id")
  private Long id;

  @Column(name = "address")
  private String address;

  @Column(name = "specialization")
  private String specialization;

  @Column(name = "hospital")
  private String hospital;

  @Column(name = "bio")
  private String bio;

  @Column(name = "phone_number")
  private String phoneNumber;

  @Column(name = "date_of_birth")
  private LocalDate dateOfBirth;

  @Column(name = "registered_date")
  private LocalDate registeredDate;

  @ManyToMany
  @JoinTable(
          name = "doctor_patients",
          joinColumns = @JoinColumn(name = "doctor_id"),
          inverseJoinColumns = @JoinColumn(name = "patient_id")
  )
  @Builder.Default
  private Set<PatientProfile> patients = new HashSet<>();

  @OneToOne
  @MapsId
  @JoinColumn(name = "user_id")
  private User user;

  @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Appointment> appointments = new HashSet<>();

  @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Prescription> prescriptions = new HashSet<>();

  public void addPatient(PatientProfile patient) {
    patients.add(patient);
    patient.getDoctors().add(this);
  }

  public void removePatient(PatientProfile patient) {
    patients.remove(patient);
    patient.getDoctors().remove(this);
  }
}
