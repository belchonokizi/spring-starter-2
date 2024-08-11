--liquibase formatted sql

--changeset belchonokizi:1
ALTER TABLE users
ADD COLUMN image VARCHAR(64);

--changeset belchonokizi:2
ALTER TABLE users_aud
ADD COLUMN image VARCHAR(64);

