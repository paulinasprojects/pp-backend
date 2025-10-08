create table appointments
(
    id               bigint auto_increment
        primary key,
    appointment_date date        not null,
    status           varchar(30) not null,
    notes            text        null,
    doctor_id        bigint      not null,
    patient_id       bigint      not null,

    constraint appointments_doctor_profiles_id_fk
        foreign key (doctor_id) references doctor_profiles (id)
            on delete cascade,
    constraint appointments_patient_profiles_id_fk
        foreign key (patient_id) references patient_profiles (id)
            on delete cascade
);


alter table diagnoses
    add appointment_id bigint not null;

alter table diagnoses
    modify notes text null;

alter table diagnoses
    add constraint diagnoses_appointments_id_fk
        foreign key (appointment_id) references appointments (id);

alter table diagnoses
drop foreign key diagnoses_doctor_profiles_user_id_fk;

alter table diagnoses
    add constraint diagnoses_doctor_profiles_id_fk
        foreign key (doctor_id) references doctor_profiles (id);

alter table diagnoses
drop foreign key diagnoses_patient_profiles_user_id_fk;

alter table diagnoses
    add constraint diagnoses_patient_profiles_id_fk
        foreign key (patient_id) references patient_profiles (id);

