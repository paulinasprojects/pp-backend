package com.paulinasprojects.ppbackend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "appointments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Appointment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "appointment_date")
  private LocalDate appointmentDate;

  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  private AppointmentStatus status;

  @Column(name = "notes")
  private String notes;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "doctor_id")
  private DoctorProfile doctor;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "patient_id")
  private PatientProfile patient;

  @OneToOne(mappedBy = "appointment", cascade = CascadeType.ALL)
  private Diagnosis diagnosis;
}