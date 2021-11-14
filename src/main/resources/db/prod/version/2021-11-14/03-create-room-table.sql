--liquibase formatted sql
--changeset kris:1
CREATE TABLE IF NOT EXISTS ROOM (
                                    id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
                                    name VARCHAR(50),
                                    description VARCHAR (250),
                                    img_uri VARCHAR(100),
                                    capacity INT

);