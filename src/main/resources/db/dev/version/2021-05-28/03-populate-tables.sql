--liquibase formatted sql
--changeset kris:1
INSERT INTO room (id, name, capacity, description) VALUES (1, 'Gym', 4, 'siłownia');
INSERT INTO room (id, name, capacity, description) VALUES (2, 'Cardio', 3, 'rowerki');

--liquibase formatted sql
--changeset kris:2
INSERT INTO booking_availability(id, booking_date, timeslot_id) VALUES (1, '2021-06-01', 8);
INSERT INTO booking_availability(id, booking_date, timeslot_id) VALUES (2, '2021-06-01', 9);
INSERT INTO booking_availability(id, booking_date, timeslot_id) VALUES (3, '2021-06-02', 25);
INSERT INTO booking_availability(id, booking_date, timeslot_id) VALUES (4, '2021-06-02', 26);

--liquibase formatted sql
--changeset kris:3
INSERT INTO booking (id, booking_availability_id, booking_timeslot_id, user_id, startTime, room_id) VALUES
(1, 1, 8, 1, PARSEDATETIME('04:00', 'HH:mm'), 1);
INSERT INTO booking (id, booking_availability_id, booking_timeslot_id, user_id, startTime, room_id) VALUES
(2, 2, 9, 1, PARSEDATETIME('04:30', 'HH:mm'), 1);
INSERT INTO booking (id, booking_availability_id, booking_timeslot_id, user_id, startTime, room_id) VALUES
(3, 3, 25, 2, PARSEDATETIME('12:00', 'HH:mm'), 2);
INSERT INTO booking (id, booking_availability_id, booking_timeslot_id, user_id, startTime, room_id) VALUES
(4, 4, 26, 2, PARSEDATETIME('12:30', 'HH:mm'), 2);

