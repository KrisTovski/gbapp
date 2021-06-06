--liquibase formatted sql
--changeset kris:1
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values
(1, 'mylogin', 'Brandon', 'Howorth', 'bhoworth0@devhub.com', 'mypassword', 0, '2020-01-26T13:14:05', null, 0),
(2, 'tlally1', 'Torrin', 'Lally', 'tlally1@163.com', 'x79tnUhGk', 1, '2020-01-26T13:14:05', null, 0),
(3, 'fdorling2', 'Frederigo', 'Dorling', 'fdorling2@theglobeandmail.com', 'M8gg8PD', 0, '2020-01-26T13:14:05', null, 0),
(4, 'gfalk3', 'Gradey', 'Falk', 'gfalk3@dailymotion.com', 'iQHgtEMczLGr', 0, '2020-01-26T13:14:05', null, 1),
(5, 'mettridge4', 'Melisa', 'Ettridge', 'mettridge4@europa.eu', '5TAS4AFb', 0, '2020-01-26T13:14:05', null, 0),
(6, 'mylog', 'Brandon', 'Howorth', 'mylog0@devhub.com', 'mypassword', 0, '2020-01-26T13:14:05', null, 0),
(7, 'tlal', 'Torrin', 'Lally', 'tlal@163.com', 'x79tnUhGk', 1, '2020-01-26T13:14:05', null, 0),
(8, 'fdor', 'Frederigo', 'Dorling', 'fdor@theglobeandmail.com', 'M8gg8PD', 0, '2020-01-26T13:14:05', null, 0),
(9, 'gfa', 'Gradey', 'Falk', 'gfa@dailymotion.com', 'iQHgtEMczLGr', 0, '2020-01-26T13:14:05', null, 1),
(10, 'mettr', 'Melisa', 'Ettridge', 'ettr@europa.eu', '5TAS4AFb', 0, '2020-01-26T13:14:05', null, 0),
(11, 'myloginlogin', 'Brandon', 'Howorth', 'bhoworthHoworth0@devhub.com', 'mypassword', 0, '2020-01-26T13:14:05', null, 0),
(12, 'tlally11', 'Torrin', 'Lally', 'tlally11@163.com', 'x79tnUhGk', 1, '2020-01-26T13:14:05', null, 0),
(13, 'fdorling22', 'Frederigo', 'Dorling', 'fdorling22@theglobeandmail.com', 'M8gg8PD', 0, '2020-01-26T13:14:05', null, 0),
(14, 'gfalk33', 'Gradey', 'Falk', 'gfalk33@dailymotion.com', 'iQHgtEMczLGr', 0, '2020-01-26T13:14:05', null, 1),
(15, 'mettridge44', 'Melisa', 'Ettridge', 'mettridge44@europa.eu', '5TAS4AFb', 0, '2020-01-26T13:14:05', null, 0);