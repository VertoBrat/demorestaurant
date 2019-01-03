DELETE FROM dish;
DELETE FROM vote;
DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM restaurant;

INSERT INTO restaurant (name, location, updated_at) VALUES
('GrandPalace','DownTown',current_date),
('GrandPalace2','DownTown',current_date),
('GrandPalace3','DownTown',current_date-1),
('GrandPalace4','DownTown',current_date),
('GrandPalace5','DownTown',current_date),
('GrandPalace6','DownTown',current_date),
('GrandPalace7','DownTown',current_date),
('GrandPalace8','DownTown',current_date),
('GrandPalace9','DownTown',current_date),
('GrandPalace10','DownTown',current_date),
('GrandPalace11','DownTown',current_date),
('GrandPalace12','DownTown',current_date),
('GrandPalace13','DownTown',current_date),
('GrandPalace14','DownTown',current_date),
('GrandPalace15','DownTown',current_date),
('GrandPalace16','DownTown',current_date),
('GrandPalace17','DownTown',current_date),
('GrandPalace18','DownTown',current_date),
('GrandPalace19','DownTown',current_date),
('GrandPalace20','DownTown',current_date),
('GrandPalace21','DownTown',current_date),
('GrandPalace22','DownTown',current_date),
('GrandPalace23','DownTown',current_date),
('GrandPalace24','DownTown',current_date);

INSERT INTO dish (dish_name, price, created_at, restaurant_id) VALUES
('cake','25',current_date,1),
('juice','15',current_date,1),
('corn','25',current_date,2),
('soup','30',current_date,2),
('cutlet','50',current_date-1,3),
('eggs','5',current_date,4),
('porridge','15',current_date,4),
('cake','25',current_date,5),
('juice','15',current_date,6),
('corn','25',current_date,7),
('soup','30',current_date,8),
('cutlet','50',current_date,9),
('eggs','5',current_date,10),
('porridge','15',current_date,11),
('cake','25',current_date,12),
('juice','15',current_date,13),
('corn','25',current_date,14),
('soup','30',current_date,15),
('cutlet','50',current_date,16),
('eggs','5',current_date,17),
('porridge','15',current_date,18),
('cake','25',current_date,19),
('juice','15',current_date,20),
('corn','25',current_date,21),
('soup','30',current_date,22),
('cutlet','50',current_date,23),
('eggs','5',current_date,24);

INSERT INTO users (user_name, password, email) VALUES
('admin','$2a$10$Dac2SqpvH.RAqJrPqeTB9u4RzxA2bNJNh2InttSLFiVcXD2tYDELW','admin@mail.ru'),
('user','$2a$10$b6plcgopc4UR6AkVjm0HTOCaoaPTL.AiL2AyHED0kAKPyXtfqMQHm','user@mail.ru');

INSERT INTO user_roles (user_id, roles) VALUES
(1,'ROLE_ADMIN'),
(1,'ROLE_USER'),
(2,'ROLE_USER')