create table doctor_profiles
(
    id              bigint auto_increment
        primary key,
    user_id         bigint       not null,
    address         varchar(255) null,
    specialization  longtext     not null,
    hospital        longtext     not null,
    bio             longtext     null,
    phone_number    varchar(15)  null,
    date_of_birth   date         null,
    registered_date date         null,
    constraint doctor_profiles_users_id_fk
        foreign key (user_id) references users (id) on delete cascade
);
