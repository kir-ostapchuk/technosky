-- liquibase formatted sql

-- changeset kostapchuk:1
INSERT INTO users (id, serial, document_type)
VALUES (1, 'user1_serial', 'PASSPORT'),
       (2, 'user2_serial', 'DRIVER_LICENSE');

-- changeset kostapchuk:2
INSERT INTO account (id, balance, client_id, currency)
VALUES (1, 1000.00, 1, 'USD'),
       (2, 500.00, 2, 'USD');