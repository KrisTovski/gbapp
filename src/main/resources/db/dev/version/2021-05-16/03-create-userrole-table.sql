--liquibase formatted sql
--changeset kris:1
CREATE TABLE USER_ROLE(
                          id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
                          role VARCHAR (50) NOT NULL,
                          description VARCHAR(1000)
);