--liquibase formatted sql
--changeset kris:1
INSERT INTO ROOM (id, name, description, img_uri, capacity) VALUES
                                                                (1, 'Gym', 'Czas na ciężary','/images/gym-img-button.jpg', 4),
                                                                (2, 'Cardio', 'Dobrze jest się trochę zmęczyć','/images/cardio-img-button.jpg', 3);