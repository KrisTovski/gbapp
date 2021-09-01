--liquibase formatted sql
--changeset kris:1
INSERT INTO booking (id, date, start, end, user_id) VALUES
(1,'2021-06-12', '04:00:00.000', '05:00:00.000', 1);
INSERT INTO booking (id, date, start, end, user_id) VALUES
(2,'2021-06-13', '04:30:00.000', '06:00:00.000', 2);
INSERT INTO booking (id, date, start, end, user_id) VALUES
(3,'2021-06-14', '12:00:00.000', '14:00:00.000', 3);
INSERT INTO booking (id, date, start, end, user_id) VALUES
(4,'2021-06-15', '12:30:00.000', '13:30:00.000', 4);

