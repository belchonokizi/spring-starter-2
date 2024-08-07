--liquibase formatted sql

--changeset belchonokizi:1
create table if not exists company(
    id serial primary key ,
    company_name VARCHAR(64) NOT NULL UNIQUE
);


--changeset belchonokizi:2
CREATE TABLE IF NOT EXISTS company_locales(
    company_id INT REFERENCES company (id) ON DELETE CASCADE,
    lang varchar(2),
    description varchar(255) not null ,
    primary key (company_id, lang)
);

--changeset belchonokizi:3
create table if not exists users(
    id bigserial primary key ,
    username varchar(64) not null unique ,
    birth_date date,
    firstname varchar(64),
    lastname varchar(64),
    role varchar(32),
    company_id int references company (id)
);
--rollback DROP TABLE company

--changeset belchonokizi:4
create table if not exists payment(
    id bigserial primary key ,
    amount int not null ,
    receiver_id bigint not null references users (id) ON DELETE CASCADE
);

--changeset belchonokizi:5
create table if not exists chat(
    id bigserial primary key ,
    chat_name VARCHAR(64) NOT NULL UNIQUE
);

--changeset belchonokizi:6
CREATE TABLE IF NOT EXISTS users_chat(
    id BIGSERIAL PRIMARY KEY ,
    user_id bigint not null references users(id) ON DELETE CASCADE,
    chat_id bigint not null references chat (id) ON DELETE CASCADE,
    unique (user_id, chat_id)
);