use rest_with_spring_boot;
create table person (
    id         bigint auto_increment primary key,
    address    VARCHAR(100) not null,
    first_name VARCHAR(80)  not null,
    gender     VARCHAR(6)   not null,
    last_name  VARCHAR(80)  not null
);