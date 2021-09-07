--liquibase formatted sql
--changeset kris:1
INSERT INTO booking (id, date, start, end, user_id) VALUES
(1,'2021-09-02', '04:00', '05:00', 1);
INSERT INTO booking (id, date, start, end, user_id) VALUES
(2,'2021-09-02', '05:00', '06:00', 2);
INSERT INTO booking (id, date, start, end, user_id) VALUES
(3,'2021-09-02', '12:00', '13:00', 3);
INSERT INTO booking (id, date, start, end, user_id) VALUES
(4,'2021-09-02', '13:00', '14:00', 4);

