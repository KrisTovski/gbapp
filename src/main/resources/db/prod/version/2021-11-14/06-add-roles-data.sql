--liquibase formatted sql
--changeset kris:1
INSERT INTO ROLE(id, role, description) VALUES
                                            (1, 'ROLE_USER', 'default role for user'),
                                            (2, 'ROLE_ADMIN', 'administration');