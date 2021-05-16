--liquibase formatted sql
--changeset kris:1
CREATE TABLE USER (
    id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    login VARCHAR (50) NOT NULL,
    first_name VARCHAR (50) NOT NULL,
    last_name VARCHAR (50) NOT NULL,
    email VARCHAR (255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    enable TINYINT(0),
    create_time DATETIME,
    update_time DATETIME,
    locked TINYINT(0)
);