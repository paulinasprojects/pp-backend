alter table prescriptions
    add doctor_id bigint not null;

alter table prescriptions
    add patient_id bigint not null;

alter table prescriptions
    add constraint prescriptions_doctor_profiles_id_fk
        foreign key (doctor_id) references doctor_profiles (id)
            on delete cascade;

alter table prescriptions
    add constraint prescriptions_patient_profiles_id_fk
        foreign key (patient_id) references patient_profiles (id)
            on delete cascade;