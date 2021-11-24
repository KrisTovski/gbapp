--liquibase formatted sql
--changeset kris:1

insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (1, 'test', 'Testowy', 'Tester', 'tester0@test.com', '{bcrypt}$2a$10$IHFKibCSJBOZpgo6QqOLpOIUS/jXMvt3/RvOIrwR7DXP8toxQ9u2C', 1, '2020-08-31 16:29:41', null, 1);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (2, 'tverbrugghen1', 'Tobit', 'Verbrugghen', 'tverbrugghen1@weather.com', 'C8zL1VR4O1', 1, '2020-08-28 14:02:38', null, 1);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (3, 'spumfrey2', 'Stephanus', 'Pumfrey', 'spumfrey2@guardian.co.uk', 'IjiTQMB', 0, '2020-10-20 12:28:51', null, 1);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (4, 'blouedey3', 'Boycie', 'Louedey', 'blouedey3@quantcast.com', 'WbvzLY20V91U', 0, '2021-03-07 13:36:46', null, 1);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (5, 'glafoy4', 'Giles', 'Lafoy', 'glafoy4@lulu.com', '1hVjDGZ', 0, '2021-04-22 04:38:41', null, 1);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (6, 'dbowring5', 'Dorice', 'Bowring', 'dbowring5@timesonline.co.uk', 'xeU7H0er', 0, '2020-06-25 20:02:36', null, 0);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (7, 'wmalpas6', 'Winnie', 'Malpas', 'wmalpas6@xing.com', 'QCHddfDhKZ9', 1, '2020-12-24 18:27:45', null, 1);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (8, 'mdahmke7', 'Martina', 'Dahmke', 'mdahmke7@usa.gov', 'ZacL2tK9', 0, '2021-05-19 22:51:46', null, 0);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (9, 'fdrewell8', 'Falito', 'Drewell', 'fdrewell8@vistaprint.com', '8f37cl0', 0, '2020-09-27 06:27:03', null, 1);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (10, 'areally9', 'Ashla', 'Really', 'areally9@imdb.com', '9IUfhQPgRwx1', 0, '2020-12-24 01:40:48', null, 0);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (11, 'jhearsuma', 'Jesselyn', 'Hearsum', 'jhearsuma@tinypic.com', 'shAJsyWrn', 0, '2021-05-30 22:45:19', null, 1);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (12, 'ggerrieb', 'Gael', 'Gerrie', 'ggerrieb@homestead.com', 'QGcyiCo5ZIcV', 1, '2021-03-14 18:28:28', null, 1);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (13, 'rlydiardc', 'Rosaline', 'Lydiard', 'rlydiardc@dmoz.org', 'BpwXbA', 0, '2021-01-05 13:25:32', null, 0);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (14, 'hharmand', 'Honey', 'Harman', 'hharmand@boston.com', 'wgfV0Jt5SILN', 1, '2020-09-20 05:44:45', null, 1);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (15, 'rritche', 'Reinaldo', 'Ritch', 'rritche@oakley.com', 'cFEe7QQEv', 1, '2021-05-21 10:35:31', null, 1);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (16, 'mmacaleesf', 'Madelle', 'MacAlees', 'mmacaleesf@bbb.org', 'Xhh8Oo', 1, '2021-01-24 13:12:11', null, 0);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (17, 'cstuehmeyerg', 'Carmelia', 'Stuehmeyer', 'cstuehmeyerg@amazon.co.uk', 'dJY26Vgk98kP', 0, '2020-11-19 22:34:23', null, 1);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (18, 'kloberh', 'Koralle', 'Lober', 'kloberh@jalbum.net', 'NSyKhzPb', 0, '2020-12-05 17:27:22', null, 0);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (19, 'lcaistori', 'Leodora', 'Caistor', 'lcaistori@plala.or.jp', 'ypO05f0DAor', 0, '2020-11-14 05:40:06', null, 0);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (20, 'bcayetj', 'Bamby', 'Cayet', 'bcayetj@yahoo.com', 'R2HXHsVe', 1, '2021-04-19 21:05:33', null, 1);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (21, 'jorneblowk', 'Jacklyn', 'Orneblow', 'jorneblowk@answers.com', 'CtPM84WvjA', 1, '2020-10-05 16:42:40', null, 0);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (22, 'laloshkinl', 'Linnie', 'Aloshkin', 'laloshkinl@npr.org', 'oJBHAu', 0, '2021-05-06 13:41:09', null, 1);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (23, 'btrowellm', 'Bert', 'Trowell', 'btrowellm@loc.gov', 'EIEy1ZdwSGW', 1, '2020-09-07 02:33:26', null, 0);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (24, 'elambotinn', 'Elisha', 'Lambotin', 'elambotinn@instagram.com', '3GwE0pDG', 1, '2021-05-31 06:29:48', null, 0);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (25, 'rwythillo', 'Ruthi', 'Wythill', 'rwythillo@indiatimes.com', '54kgwSok0', 1, '2021-04-09 00:04:10', null, 1);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (26, 'paugustp', 'Penelope', 'August', 'paugustp@whitehouse.gov', 'y65B9BB0d', 1, '2020-11-29 19:59:18', null, 0);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (27, 'cwillisq', 'Cherish', 'Willis', 'cwillisq@feedburner.com', 'Q8wrJB', 1, '2020-09-17 01:27:56', null, 0);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (28, 'kbenedidickr', 'Keslie', 'Benedidick', 'kbenedidickr@zimbio.com', 'fFFBTPARNd', 0, '2020-09-24 10:16:04', null, 0);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (29, 'lcoggills', 'Lloyd', 'Coggill', 'lcoggills@qq.com', '5b4Pez', 0, '2021-02-21 19:34:16', null, 0);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (30, 'ogutridget', 'Olive', 'Gutridge', 'ogutridget@amazon.co.jp', 'FyAUWMyp16H', 1, '2021-02-25 21:38:00', null, 1);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (31, 'amciloryu', 'Alaster', 'McIlory', 'amciloryu@last.fm', 'qe5TMyeGcC8', 1, '2020-06-28 01:52:27', null, 1);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (32, 'mwhaymandv', 'Mervin', 'Whaymand', 'mwhaymandv@instagram.com', 'Sw4uz7VH9', 0, '2021-05-24 08:58:21', null, 0);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (33, 'fbaskerfieldw', 'Frederik', 'Baskerfield', 'fbaskerfieldw@flavors.me', '5k1letu7ro6', 0, '2020-11-17 21:52:06', null, 1);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (34, 'collivierx', 'Cassondra', 'Ollivier', 'collivierx@squidoo.com', 'LCkFp8ze3y6', 0, '2020-07-11 19:51:51', null, 0);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (35, 'ibarboury', 'Idalia', 'Barbour', 'ibarboury@noaa.gov', 'ZK1wBuujvt', 0, '2020-10-19 02:15:21', null, 1);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (36, 'tcarlinoz', 'Theodore', 'Carlino', 'tcarlinoz@wisc.edu', 'eiSTqQGt', 1, '2020-12-22 08:10:49', null, 0);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (37, 'rrenshaw10', 'Robinson', 'Renshaw', 'rrenshaw10@meetup.com', 'jUNWUn', 1, '2021-05-19 14:52:49', null, 1);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (38, 'aupston11', 'Ailsun', 'Upston', 'aupston11@linkedin.com', 'qSemU97', 0, '2020-12-05 00:03:26', null, 1);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (39, 'hboase12', 'Hartwell', 'Boase', 'hboase12@mtv.com', 'pXSAlA5LMY', 0, '2020-06-26 08:00:01', null, 1);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (40, 'dcampsall13', 'Dana', 'Campsall', 'dcampsall13@yellowbook.com', 'fU0S8Vy3', 1, '2021-04-20 14:46:10', null, 0);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (41, 'hmulder14', 'Haven', 'Mulder', 'hmulder14@flavors.me', 'j67Jdrw64', 0, '2021-06-03 20:23:48', null, 0);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (42, 'kgillease15', 'Kittie', 'Gillease', 'kgillease15@blog.com', '4sZZBb', 1, '2021-06-02 20:32:35', null, 1);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (43, 'selsby16', 'Stanford', 'Elsby', 'selsby16@wordpress.org', 'pIE3Ao58', 1, '2020-07-14 01:54:35', null, 0);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (44, 'klodovichi17', 'Kellina', 'Lodovichi', 'klodovichi17@bandcamp.com', 'nykSdNRv7X', 0, '2020-09-16 16:53:38', null, 1);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (45, 'cschall18', 'Clerkclaude', 'Schall', 'cschall18@mac.com', 'fQnJCAV3kOj5', 0, '2021-03-25 12:34:00', null, 0);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (46, 'ktuckwood19', 'Killy', 'Tuckwood', 'ktuckwood19@usnews.com', 'onJJgKq', 0, '2021-01-08 04:23:15', null, 1);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (47, 'fpenvarden1a', 'Florina', 'Penvarden', 'fpenvarden1a@so-net.ne.jp', 'fUJcyKp5b3', 0, '2020-10-24 04:43:19', null, 1);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (48, 'rbrettel1b', 'Rebeca', 'Brettel', 'rbrettel1b@amazonaws.com', 'Lr4RwC5NuGac', 1, '2020-11-22 08:56:13', null, 0);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (49, 'rkirimaa1c', 'Rosalyn', 'Kirimaa', 'rkirimaa1c@wufoo.com', '4l7RqoSk', 0, '2020-10-02 07:22:01', null, 0);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (50, 'esomner1d', 'Ede', 'Somner', 'esomner1d@intel.com', '6QLE8Q8', 0, '2021-03-25 12:53:08', null, 1);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (51, 'emossdale1e', 'Ellary', 'Mossdale', 'emossdale1e@histats.com', 'GONCUDPFmlIb', 1, '2021-03-17 21:18:13', null, 1);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (52, 'ogunston1f', 'Orlan', 'Gunston', 'ogunston1f@list-manage.com', 'OtPT0jM0Mp', 1, '2021-05-15 23:20:49', null, 0);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (53, 'aspinige1g', 'Allister', 'Spinige', 'aspinige1g@slate.com', 'jFN9bP', 1, '2020-09-30 05:32:08', null, 1);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (54, 'fpammenter1h', 'Faustina', 'Pammenter', 'fpammenter1h@oakley.com', '825ZVmh', 1, '2020-08-08 12:26:51', null, 1);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (55, 'dportriss1i', 'Delaney', 'Portriss', 'dportriss1i@jiathis.com', 'IlrzYMN98', 0, '2020-07-13 07:07:30', null, 0);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (56, 'gmacronald1j', 'Guenna', 'MacRonald', 'gmacronald1j@t-online.de', 'ZspziZ', 0, '2021-03-10 07:11:52', null, 0);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (57, 'cpinfold1k', 'Carlene', 'Pinfold', 'cpinfold1k@gnu.org', 'AQ84FQ', 1, '2021-06-01 13:27:25', null, 1);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (58, 'cdodson1l', 'Charissa', 'Dodson', 'cdodson1l@marriott.com', 'PmwItMviZKK', 0, '2020-12-02 06:02:46', null, 0);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (59, 'pmuncey1m', 'Paddy', 'Muncey', 'pmuncey1m@thetimes.co.uk', 't9369kKBEYS', 1, '2020-10-30 21:33:33', null, 0);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (60, 'jancliffe1n', 'Julius', 'Ancliffe', 'jancliffe1n@chicagotribune.com', '8OQi7NulciC', 0, '2021-01-06 13:28:26', null, 1);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (61, 'mplayhill1o', 'Mariette', 'Playhill', 'mplayhill1o@creativecommons.org', 'uz53LhKhNZE', 1, '2021-03-08 11:09:11', null, 1);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (62, 'pcicculini1p', 'Peggie', 'Cicculini', 'pcicculini1p@nsw.gov.au', 'jdNiWZ', 0, '2020-12-11 09:47:21', null, 1);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (63, 'pfeldberg1q', 'Penny', 'Feldberg', 'pfeldberg1q@un.org', 'bQLbkk8ySaY', 1, '2020-10-07 14:00:42', null, 1);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (64, 'mjeays1r', 'Morgan', 'Jeays', 'mjeays1r@skype.com', 'VvqU10', 0, '2020-11-20 13:58:28', null, 1);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (65, 'kpatroni1s', 'Kathrine', 'Patroni', 'kpatroni1s@samsung.com', '0EMCImt76', 1, '2020-08-12 03:58:37', null, 1);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (66, 'gfoot1t', 'Georgette', 'Foot', 'gfoot1t@cam.ac.uk', 'kPhF8DZTS0wJ', 0, '2021-01-21 09:16:06', null, 0);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (67, 'jmaccaig1u', 'Jodi', 'Maccaig', 'jmaccaig1u@cloudflare.com', 'SZkWzUfX', 0, '2021-04-13 12:47:39', null, 0);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (68, 'xcherm1v', 'Xena', 'Cherm', 'xcherm1v@printfriendly.com', 'no8Ng4wsYSau', 1, '2021-01-06 15:29:14', null, 0);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (69, 'loldcote1w', 'Lisetta', 'Oldcote', 'loldcote1w@mapy.cz', 'RZJ9Fv', 1, '2021-03-12 15:14:02', null, 1);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (70, 'hmcdunlevy1x', 'Hagan', 'McDunlevy', 'hmcdunlevy1x@newyorker.com', 'JynL2xgE', 1, '2021-04-13 05:27:46', null, 1);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (71, 'pguyon1y', 'Priscella', 'Guyon', 'pguyon1y@tripod.com', 'WdstkF3', 1, '2021-01-17 21:52:47', null, 1);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (72, 'hcuberley1z', 'Hasheem', 'Cuberley', 'hcuberley1z@geocities.jp', 'lqDdTAOb8CsC', 0, '2020-08-22 00:18:11', null, 0);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (73, 'iorowane20', 'Isidor', 'O''Rowane', 'iorowane20@washingtonpost.com', '15aRn099HsVf', 1, '2020-07-05 04:13:33', null, 1);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (74, 'gmcmenamin21', 'Giorgia', 'McMenamin', 'gmcmenamin21@sogou.com', 'RgCyTF8', 0, '2020-06-21 04:25:49', null, 1);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (75, 'pangrave22', 'Pearla', 'Angrave', 'pangrave22@hatena.ne.jp', 'JfirzHTJU', 1, '2020-12-25 20:00:58', null, 1);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (76, 'vrosewarne23', 'Van', 'Rosewarne', 'vrosewarne23@amazon.co.uk', 'E3IZw2hSYm7', 1, '2021-02-06 03:10:26', null, 1);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (77, 'mmatusevich24', 'Mariana', 'Matusevich', 'mmatusevich24@ask.com', 'Zk41DQ35aUqe', 0, '2020-07-05 12:05:32', null, 0);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (78, 'rbemwell25', 'Reiko', 'Bemwell', 'rbemwell25@tripadvisor.com', 'Fg5lTzpjgW', 0, '2021-01-31 14:02:05', null, 1);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (79, 'jolivazzi26', 'Jeffry', 'Olivazzi', 'jolivazzi26@people.com.cn', 'Yip6N4EN', 1, '2021-05-30 07:14:12', null, 0);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (80, 'lhuglin27', 'Lexie', 'Huglin', 'lhuglin27@mozilla.org', 'EDTIR9pi3w', 0, '2020-07-29 03:56:03', null, 0);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (81, 'bantoszczyk28', 'Banky', 'Antoszczyk', 'bantoszczyk28@bloglines.com', 'byBrYkYuyA', 1, '2021-02-10 14:18:07', null, 1);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (82, 'nthunder29', 'Nomi', 'Thunder', 'nthunder29@elegantthemes.com', 'jq7BZpd02jHn', 0, '2020-06-30 05:28:39', null, 1);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (83, 'sparnham2a', 'Stinky', 'Parnham', 'sparnham2a@jigsy.com', 'CGl0ofUAG', 0, '2020-07-04 06:06:17', null, 0);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (84, 'sseacombe2b', 'Sondra', 'Seacombe', 'sseacombe2b@macromedia.com', 'GAXKMksj1e', 0, '2020-07-29 08:09:27', null, 1);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (85, 'gabdey2c', 'Grantham', 'Abdey', 'gabdey2c@sciencedaily.com', 'e2ke31', 0, '2021-01-31 21:33:38', null, 1);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (86, 'fstairmond2d', 'Franky', 'Stairmond', 'fstairmond2d@weebly.com', 'NBXIu8H5NF', 1, '2020-09-06 17:41:14', null, 1);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (87, 'ssteddall2e', 'Sherye', 'Steddall', 'ssteddall2e@slate.com', 'HQY7qghyEK', 1, '2021-03-23 21:30:33', null, 1);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (88, 'vlamplugh2f', 'Valaree', 'Lamplugh', 'vlamplugh2f@nymag.com', 'EmVBrng', 1, '2020-08-12 08:15:48', null, 1);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (89, 'nmachans2g', 'Naomi', 'Machans', 'nmachans2g@macromedia.com', 'EZ9ge6N9NRw5', 1, '2021-05-19 05:53:10', null, 1);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (90, 'bhamsson2h', 'Batholomew', 'Hamsson', 'bhamsson2h@angelfire.com', 'kbD431fFOx', 0, '2020-06-09 16:41:27', null, 0);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (91, 'hbrach2i', 'Hillary', 'Brach', 'hbrach2i@google.ru', 'SucViS', 0, '2020-08-23 11:15:01', null, 1);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (92, 'jastill2j', 'Jess', 'Astill', 'jastill2j@illinois.edu', 'ebUU2i21m', 0, '2020-07-13 22:48:24', null, 0);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (93, 'arichings2k', 'Adolf', 'Richings', 'arichings2k@miitbeian.gov.cn', 'qj4GBMynnXZ', 1, '2021-05-01 02:53:41', null, 0);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (94, 'npogosian2l', 'Nadiya', 'Pogosian', 'npogosian2l@moonfruit.com', 'm2OJ2HmYzm', 1, '2020-09-11 22:25:13', null, 1);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (95, 'rfreezer2m', 'Rozamond', 'Freezer', 'rfreezer2m@cnet.com', '9RhtXIt', 1, '2020-09-09 16:29:50', null, 1);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (96, 'adressel2n', 'Asher', 'Dressel', 'adressel2n@dailymail.co.uk', 'VOhCijp', 1, '2020-10-06 12:13:15', null, 1);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (97, 'tboomes2o', 'Tait', 'Boomes', 'tboomes2o@walmart.com', 'bgWag7ufgf', 0, '2020-10-02 23:41:36', null, 0);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (98, 'aparnell2p', 'Alex', 'Parnell', 'aparnell2p@netlog.com', 'WAwhHf', 0, '2020-09-24 02:13:57', null, 0);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (99, 'bnodin2q', 'Brina', 'Nodin', 'bnodin2q@wikipedia.org', 'EceTr03ip2v', 1, '2021-04-29 18:00:34', null, 0);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (100, 'lsherbourne2r', 'Lizzy', 'Sherbourne', 'lsherbourne2r@springer.com', 'Y9aVFM3q', 0, '2021-01-28 14:35:06', null, 0);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (101, 'testAdmin', 'test', 'test', 'testowy@gmail.com', '{bcrypt}$2a$10$DN80wkQZ600Lw1b6tOQ9duVdGfYsqOxH5qRhfhYLKamuFaSL1lEMu', 1, '2021-09-12 17:18:00.268912', null, 0);
insert into user (id, login, first_name, last_name, email, password, enable, create_time, update_time, locked) values (102, 'testUser', 'User', 'User', 'user@gmail.com', '{bcrypt}$2a$10$FQxJoLVJNrSVnFHllqSZUu1RzMLOxEt3Fiydtu8/pdrcJIRcqukPO', 1, '2021-09-26 10:58:04.047992', null, 0);

--changeset kris:2
INSERT INTO ROLE(id, role, description) VALUES
(1, 'ROLE_USER', 'default role for user'),
(2, 'ROLE_ADMIN', 'administration');

--changeset kris:3
INSERT INTO USER_ROLE(user_id, role_id) VALUES
(1,1),(1,2),(2,1),(3,1),(4,1),(5,1),(6,2),(7,1),(8,1),(9,1),(10,1),(11,1),(12,1),(13,1),(14,1),(15,1),(16,2),(17,1),(18,1),
(19,1),(20,1),(21,1),(22,1),(23,1),(24,1),(25,1),(26,2),(27,1),(28,1),(29,1),(30,1),(31,1),(32,1),(33,1),(34,1),(35,1),(36,1),(37,1),(38,1),
(39,1),(40,1),(41,1),(42,1),(43,1),(44,1),(45,1),(46,2),(47,1),(48,1),(49,1),(50,1),(51,1),(52,1),(53,1),(54,1),(55,1),(56,2),(57,1),(58,1),
(59,1),(60,1),(61,1),(62,1),(63,1),(64,1),(65,1),(66,2),(67,1),(68,1),(69,1),(70,1),(71,1),(72,1),(73,1),(74,1),(75,1),(76,1),(77,1),(78,1),
(79,1),(80,1),(81,1),(82,1),(83,1),(84,1),(85,1),(86,2),(87,1),(88,1),(89,1),(90,1),(91,1),(92,1),(93,1),(94,1),(95,1),(96,1),(97,1),(98,1),
(99,1),(100,1),(101,1),(101,2),(102,1);

--changeset kris:4
INSERT INTO ROOM (id, name, description, img_uri, capacity) VALUES
                                   (1, 'Gym', 'Czas na ciężary','/images/gym-img-button.jpg', 4),
                                   (2, 'Cardio', 'Dobrze jest się trochę zmęczyć','/images/cardio-img-button.jpg', 3);

--changeset kris:5
INSERT INTO booking (id, date, start, end, user_id, room_id) VALUES
(1,'2021-09-20', '04:00', '05:00', 1, 1);
INSERT INTO booking (id, date, start, end, user_id, room_id) VALUES
(2,'2021-09-20', '05:00', '06:00', 2, 1);
INSERT INTO booking (id, date, start, end, user_id, room_id) VALUES
(3,'2021-09-20', '05:00', '06:00', 81, 1);
INSERT INTO booking (id, date, start, end, user_id, room_id) VALUES
(4,'2021-09-20', '05:00', '06:00', 86, 1);
INSERT INTO booking (id, date, start, end, user_id, room_id) VALUES
(5,'2021-09-20', '05:00', '06:00', 100, 1);
INSERT INTO booking (id, date, start, end, user_id, room_id) VALUES
(6,'2021-09-20', '12:00', '13:00', 3, 2);
INSERT INTO booking (id, date, start, end, user_id, room_id) VALUES
(7,'2021-09-20', '13:00', '14:00', 4, 2);
INSERT INTO booking (id, date, start, end, user_id, room_id) VALUES
(8,'2021-09-20', '13:00', '14:00', 1, 2);
INSERT INTO booking (id, date, start, end, user_id, room_id) VALUES
(9,'2021-09-20', '13:00', '14:00', 2, 2);
INSERT INTO booking (id, date, start, end, user_id, room_id) VALUES
(10,'2021-09-20', '16:00', '17:00', 3, 1);
INSERT INTO booking (id, date, start, end, user_id, room_id) VALUES
(11,'2021-09-20', '12:00', '13:00', 4, 2);
INSERT INTO booking (id, date, start, end, user_id, room_id) VALUES
(12,'2021-09-20', '16:00', '17:00', 94, 2);
INSERT INTO booking (id, date, start, end, user_id, room_id) VALUES
(13,'2021-09-20', '18:00', '19:00', 95, 2);
INSERT INTO booking (id, date, start, end, user_id, room_id) VALUES
(14,'2021-09-20', '12:00', '13:00', 96, 2);
