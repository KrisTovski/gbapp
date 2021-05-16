--liquibase formatted sql
--changeset kris:1
CREATE TABLE ROLE(
                          id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
                          role VARCHAR (50) NOT NULL,
                          description VARCHAR(1000)
);

--changeset kris:2
CREATE TABLE user_role(
  user_id BIGINT NOT NULL,
  role_id BIGINT NOT NULL,
  CONSTRAINT fk_user_role_user_id FOREIGN KEY (user_id) REFERENCES USER (id),
  CONSTRAINT fk_user_role_role_id FOREIGN KEY (role_id) REFERENCES ROLE (id)
);