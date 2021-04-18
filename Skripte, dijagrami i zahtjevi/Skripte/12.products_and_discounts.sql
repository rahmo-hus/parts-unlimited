create table if not exists discount
(
    id                  bigserial not null
        constraint discount_pkey
            primary key,
    start_date          date      not null,
    end_date            date      not null,
    discount_percentage integer   not null
);

alter table discount
    owner to postgres;

create table if not exists product
(
    id          bigserial        not null
        constraint product_pkey
            primary key,
    serial      bigint           not null
        constraint product_serial_key
            unique
        constraint uk4fp0iep4apkdxxggya35q11bq
            unique,
    price       double precision not null,
    discount_id bigint
        constraint product_discount_id_fkey
            references discount
);

alter table product
    owner to postgres;


