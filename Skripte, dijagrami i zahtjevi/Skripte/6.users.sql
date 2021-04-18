create table if not exists roles
(
    id   integer     not null
        constraint roles_pkey
            primary key,
    name varchar(20) not null
);

alter table roles
    owner to postgres;

create table if not exists "user"
(
    id       bigserial not null
        constraint users_pkey
            primary key,
    password varchar(150),
    username varchar(20)
        constraint ukr43af9ap4edm43mmtq01oddj6
            unique
        constraint uksb8bbouer5wak8vyiiy4pf2bx
            unique
);

alter table "user"
    owner to postgres;

create table if not exists user_roles
(
    user_id bigint  not null
        constraint fkhfh9dx7w3ubf1co1vdev94g3f
            references "user",
    role_id integer not null
        constraint fkh8ciramu9cc9q3qcqiv4ue8a6
            references roles,
    constraint user_roles_pkey
        primary key (user_id, role_id)
);

alter table user_roles
    owner to postgres;


