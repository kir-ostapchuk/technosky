INSERT INTO users (id, serial, document_type)
VALUES (1, 'user1_serial', 'PASSPORT'),
       (2, 'user2_serial', 'DRIVER_LICENSE');

INSERT INTO account (id, balance, user_id, currency)
VALUES (1, 1000.00, 1, 'USD'),
       (2, 500.00, 2, 'USD');

INSERT INTO operation (id, sender_id, receiver_id, amount, status)
VALUES (1, 1, 2, 10.00, 'APPLIED'),
       (2, 1, 2, 10.00, 'APPLIED'),
       (3, 1, 2, 10.00, 'APPLIED');