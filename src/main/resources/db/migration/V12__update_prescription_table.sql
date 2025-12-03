alter table prescriptions
drop foreign key prescriptions_doctor_profiles_id_fk;

alter table prescriptions
    add constraint prescriptions_doctor_profiles_user_id_fk
        foreign key (doctor_id) references doctor_profiles (user_id)
            on delete cascade;

alter table prescriptions
drop foreign key prescriptions_patient_profiles_id_fk;

alter table prescriptions
    add constraint prescriptions_patient_profiles_user_id_fk
        foreign key (patient_id) references patient_profiles (user_id)
            on delete cascade;