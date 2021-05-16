--liquibase formatted sql
--changeset kris:1
INSERT INTO ROLE(id, role, description) VALUES
(1, 'ROLE_USER', 'może korzystać z aplikacji'),
(2, 'ROLE_ADMIN', 'rządzi i dzieli');

--liquibase formatted sql
--changeset kris:2
INSERT INTO USER_ROLE(user_id, role_id) VALUES
(1,1),
(1,2),
(2,1),
(3,1),
(4,1),
(5,1);