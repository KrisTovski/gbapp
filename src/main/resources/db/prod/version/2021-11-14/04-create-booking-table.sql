--liquibase formatted sql
--changeset kris:1
CREATE TABLE IF NOT EXISTS BOOKING (
                                       id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
                                       date DATE NOT NULL,
                                       start TIME NOT NULL,
                                       end TIME NOT NULL,
                                       user_id BIGINT NOT NULL,
                                       room_id BIGINT NOT NULL,
                                       CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES user (id),
                                       CONSTRAINT fk_room_id FOREIGN KEY (room_id) REFERENCES room (id)

);