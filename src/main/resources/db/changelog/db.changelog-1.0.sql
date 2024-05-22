-- liquibase formatted sql

-- changeset kostapchuk:1
CREATE TABLE IF NOT EXISTS users
(
    id            SERIAL PRIMARY KEY,
    serial        VARCHAR(255) NOT NULL UNIQUE,
    document_type VARCHAR(50)  NOT NULL
);
-- rollback DROP TABLE IF EXISTS users;

-- changeset kostapchuk:2
CREATE TABLE IF NOT EXISTS account
(
    id       SERIAL PRIMARY KEY,
    balance  DECIMAL(19, 2) NOT NULL,
    user_id  BIGINT REFERENCES users (id),
    currency VARCHAR(50)    NOT NULL
);
-- rollback DROP TABLE IF EXISTS account;

-- changeset kostapchuk:3
CREATE TABLE IF NOT EXISTS operation
(
    id          SERIAL PRIMARY KEY,
    sender_id   BIGINT REFERENCES account (id),
    receiver_id BIGINT REFERENCES account (id),
    amount      DECIMAL(19, 2) NOT NULL,
    status      VARCHAR(50)    NOT NULL
);
-- rollback DROP TABLE IF EXISTS operation