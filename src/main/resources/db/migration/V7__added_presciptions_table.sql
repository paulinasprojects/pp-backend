create table prescriptions
(
    id              bigint auto_increment
        primary key,
    medication_name varchar(255) null,
    dosage          varchar(255) null,
    instructions    text         null,
    start_date      date         null,
    end_date        date         null,
    diagnosis_id    bigint       not null,
    constraint prescriptions_diagnoses_id_fk
        foreign key (diagnosis_id) references diagnoses (id)
            on delete cascade
);