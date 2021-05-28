--liquibase formatted sql
--changeset kris:1
CREATE TABLE IF NOT EXISTS ROOM (
                                       id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
                                      name VARCHAR(45) NOT NULL,
                                      capacity INT NOT NULL,
                                      description  VARCHAR(255) NULL
);

--changeset kris:2
CREATE TABLE IF NOT EXISTS TIMESLOT (
                                        id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
                                        start_time TIME NOT NULL,
                                        end_time TIME NOT NULL,
                                        description  VARCHAR(255) NULL
);
--changeset kris:3
CREATE TABLE IF NOT EXISTS booking_availability (
                                                    id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
                                                    booking_date DATE NOT NULL,
                                                    timeslot_id INT NOT NULL,
                                                    CONSTRAINT fk_timeslot_id FOREIGN KEY (timeslot_id) REFERENCES timeslot (id)
);
--changeset kris:4
CREATE TABLE IF NOT EXISTS BOOKING (
                                       id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
                                       booking_availability_id BIGINT NOT NULL,
                                       booking_timeslot_id BIGINT NOT NULL,
                                       user_id BIGINT NOT NULL,
                                       startTime DATETIME NOT NULL,
                                       room_id BIGINT NOT NULL,
                                       CONSTRAINT fk_booking_availability_id FOREIGN KEY (booking_availability_id) REFERENCES booking_availability (id),
                                       CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES user (id),
                                       CONSTRAINT fk_room_id FOREIGN KEY (room_id) REFERENCES room (id)
);