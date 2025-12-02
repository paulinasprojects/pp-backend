alter table appointments
drop foreign key appointments_doctor_profiles_id_fk;

alter table appointments
    add constraint appointments_doctor_profiles_user_id_fk
        foreign key (doctor_id) references doctor_profiles (user_id)
            on delete cascade;

alter table appointments
drop foreign key appointments_patient_profiles_id_fk;

alter table appointments
    add constraint appointments_patient_profiles_user_id_fk
        foreign key (patient_id) references patient_profiles (user_id)
            on delete cascade;
