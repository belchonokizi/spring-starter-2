create table if not exists company(
    id serial primary key ,
    company_name VARCHAR(64) NOT NULL UNIQUE
);



CREATE TABLE IF NOT EXISTS company_locales(
    company_id INT REFERENCES company (id),
    lang varchar(2),
    description varchar(255) not null ,
    primary key (company_id, lang)
);

create table if not exists users(
    id bigserial primary key ,
    username varchar(64) not null unique ,
    birth_date date,
    firstname varchar(64),
    lastname varchar(64),
    role varchar(32),
    company_id int references company (id)
);

create table if not exists payment(
    id bigserial primary key ,
    amount int not null ,
    receiver_id bigint not null references users (id)
);

create table if not exists chat(
    id bigserial primary key ,
    chat_name VARCHAR(64) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS users_chat(
    id BIGSERIAL PRIMARY KEY ,
    user_id bigint not null references users(id),
    chat_id bigint not null references chat (id),
    unique (user_id, chat_id)
);