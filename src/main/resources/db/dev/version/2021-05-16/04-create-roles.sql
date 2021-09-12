--liquibase formatted sql
--changeset kris:1
INSERT INTO ROLE(id, role, description) VALUES
(1, 'ROLE_USER', 'default role for user'),
(2, 'ROLE_ADMIN', 'administration');

--liquibase formatted sql
--changeset kris:2
INSERT INTO USER_ROLE(user_id, role_id) VALUES
(1,1),
(1,2),
(2,1),
(3,1),
(4,1),
(5,1),
(101,1),
(101,2);