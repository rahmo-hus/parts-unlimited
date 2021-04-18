create table if not exists part
(
    id              bigserial not null
        constraint part_pkey
            primary key,
    serial          bigint    not null
        constraint uk6dotkott2kjsp8vw4d0m25fb7
            unique,
    production_date date      not null
);

alter table part
    owner to postgres;

create table if not exists brand
(
    id   bigserial not null
        constraint brand_pkey
            primary key,
    name text      not null
);

alter table brand
    owner to postgres;

create table if not exists car
(
    id       bigserial not null
        constraint car_pkey
            primary key,
    name     text      not null,
    brand_id bigint    not null
        constraint fkhfh9dx7w3ubf1co1vdev94g3f
            references brand
);

alter table car
    owner to postgres;

create table if not exists car_part
(
    part_id bigint not null
        constraint fkh8ciramu9cc9q3qcqiv4ue8a6
            references part,
    car_id  bigint not null
        constraint yi6hmtau8kjmlse7jsgaqipl2h1
            references car,
    constraint car_part_pkey
        primary key (part_id, car_id)
);

alter table car_part
    owner to postgres;


