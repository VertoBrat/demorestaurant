DELETE FROM dish;
DELETE FROM vote;
DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM restaurant;

INSERT INTO restaurant (name, location, updated_at) VALUES
('GrandPalace','DownTown','2018-12-20'),
('GrandPalace2','DownTown','2018-12-20'),
('GrandPalace3','DownTown','2018-12-19'),
('GrandPalace4','DownTown','2018-12-20');

INSERT INTO dish (dish_name, price, created_at, restaurant_id) VALUES
('cake','25','2018-12-20',1),
('juice','15','2018-12-20',1),
('corn','25','2018-12-20',2),
('soup','30','2018-12-20',2),
('cutlet','50','2018-12-19',3),
('eggs','5','2018-12-20',4),
('porridge','15','2018-12-20',4);

INSERT INTO users (user_name, password, email) VALUES
('admin','$2a$10$Dac2SqpvH.RAqJrPqeTB9u4RzxA2bNJNh2InttSLFiVcXD2tYDELW','admin@mail.ru'),
('user','$2a$10$b6plcgopc4UR6AkVjm0HTOCaoaPTL.AiL2AyHED0kAKPyXtfqMQHm','user@mail.ru');

INSERT INTO user_roles (user_id, roles) VALUES
(1,'ROLE_ADMIN'),
(1,'ROLE_USER'),
(2,'ROLE_USER')