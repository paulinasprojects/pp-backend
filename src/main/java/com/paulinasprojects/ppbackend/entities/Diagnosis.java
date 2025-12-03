package com.paulinasprojects.ppbackend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "diagnoses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Diagnosis {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "diagnose")
  private String diagnose;

  @Column(name = "notes")
  private String notes;

  @Column(name = "date_created")
  private LocalDateTime dateCreated = LocalDateTime.now();

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "doctor_id")
  private DoctorProfile doctor;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "patient_id")
  private PatientProfile patient;

  @OneToMany(mappedBy = "diagnosis", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Prescription> prescriptions = new HashSet<>();

  @OneToOne
  @JoinColumn(name = "appointment_id")
  private Appointment appointment;
}
