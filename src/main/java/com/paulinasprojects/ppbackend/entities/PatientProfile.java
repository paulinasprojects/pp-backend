package com.paulinasprojects.ppbackend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "patient_profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientProfile {

  @Id
  @Column(name = "id")
  private Long id;

  @Column(name = "address")
  private String address;

  @Column(name = "bio")
  private String bio;

  @Column(name = "phone_number")
  private String phoneNumber;

  @Column(name = "date_of_birth")
  private LocalDate dateOfBirth;

  @Column(name = "registered_date")
  private LocalDate registeredDate;

  @OneToOne
  @MapsId
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToMany(mappedBy = "patients")
  @Builder.Default
  private Set<DoctorProfile> doctors = new HashSet<>();

  @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
  @Builder.Default
  private Set<Prescription> prescriptions = new HashSet<>();

  public void addDoctor(DoctorProfile doctor) {
    doctors.add(doctor);
    doctor.getPatients().add(this);
  }

  public void removeDoctor(DoctorProfile doctor) {
    doctors.remove(doctor);
    doctor.getPatients().remove(this);
  }
}
