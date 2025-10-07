CREATE TABLE doctor_patients (
    doctor_id BIGINT NOT NULL,
    patient_id BIGINT NOT NULL,
    PRIMARY KEY (doctor_id, patient_id),
    CONSTRAINT fk_doctor FOREIGN KEY (doctor_id) REFERENCES doctor_profiles(id),
    CONSTRAINT fk_patient FOREIGN KEY (patient_id) REFERENCES patient_profiles(id)
);
