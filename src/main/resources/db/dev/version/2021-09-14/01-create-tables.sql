--liquibase formatted sql
--changeset kris:1
CREATE TABLE IF NOT EXISTS USER (
    id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    login VARCHAR (100) NOT NULL,
    first_name VARCHAR (50) NOT NULL,
    last_name VARCHAR (50) NOT NULL,
    email VARCHAR (255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    enable TINYINT(0),
    create_time DATETIME,
    update_time DATETIME,
    locked TINYINT(0)
);

--changeset kris:2
CREATE TABLE IF NOT EXISTS ROLE(
                                   id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
                                   role VARCHAR (50) NOT NULL,
                                   description VARCHAR(1000)
);

--changeset kris:3
CREATE TABLE IF NOT EXISTS USER_ROLE(
                                        user_id BIGINT NOT NULL,
                                        role_id BIGINT NOT NULL,
                                        CONSTRAINT fk_user_role_user_id FOREIGN KEY (user_id) REFERENCES USER (id),
                                        CONSTRAINT fk_user_role_role_id FOREIGN KEY (role_id) REFERENCES ROLE (id)
);

--changeset kris:4
CREATE TABLE IF NOT EXISTS ROOM (
                                    id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
                                    name VARCHAR(50),
                                    description VARCHAR (250),
                                    img_uri VARCHAR(100),
                                    capacity INT

);


--changeset kris:5
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

