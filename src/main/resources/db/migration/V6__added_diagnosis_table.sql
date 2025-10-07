create table diagnoses
(
    id           bigint auto_increment
        primary key,
    diagnose     varchar(255) not null,
    notes        varchar(255) null,
    date_created date         not null,
    doctor_id    bigint       not null,
    patient_id   bigint       not null,
    constraint diagnoses_doctor_profiles_user_id_fk
        foreign key (doctor_id) references doctor_profiles (user_id),
    constraint diagnoses_patient_profiles_user_id_fk
        foreign key (patient_id) references patient_profiles (user_id)
);