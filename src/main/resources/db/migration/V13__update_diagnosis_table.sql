alter table diagnoses
drop foreign key diagnoses_doctor_profiles_id_fk;

alter table diagnoses
    add constraint diagnoses_doctor_profiles_user_id_fk
        foreign key (doctor_id) references doctor_profiles (user_id);

alter table diagnoses
drop foreign key diagnoses_patient_profiles_id_fk;

alter table diagnoses
    add constraint diagnoses_patient_profiles_user_id_fk
        foreign key (patient_id) references patient_profiles (user_id);
