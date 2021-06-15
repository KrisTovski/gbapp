--liquibase formatted sql
--changeset kris:1

CREATE TABLE IF NOT EXISTS BOOKING (
                                       id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
                                       start DATETIME NOT NULL,
                                       end DATETIME NOT NULL,
                                       user_id BIGINT NOT NULL,
                                       CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES user (id)

);