DELETE FROM dish;
DELETE FROM vote;
DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM restaurant;

INSERT INTO restaurant (name, location, updated_at) VALUES
('GrandPalace','DownTown',current_date),
('GrandPalace2','DownTown',current_date),
('GrandPalace3','DownTown',current_date-1),
('GrandPalace4','DownTown',current_date);

INSERT INTO dish (dish_name, price, created_at, restaurant_id) VALUES
('cake','25',current_date,1),
('juice','15',current_date,1),
('corn','25',current_date,2),
('soup','30',current_date,2),
('cutlet','50',current_date-1,3),
('eggs','5',current_date,4),
('porridge','15',current_date,4);

INSERT INTO users (user_name, password, email) VALUES
('admin','$2a$10$Dac2SqpvH.RAqJrPqeTB9u4RzxA2bNJNh2InttSLFiVcXD2tYDELW','admin@mail.ru'),
('user','$2a$10$b6plcgopc4UR6AkVjm0HTOCaoaPTL.AiL2AyHED0kAKPyXtfqMQHm','user@mail.ru');

INSERT INTO user_roles (user_id, roles) VALUES
(1,'ROLE_ADMIN'),
(1,'ROLE_USER'),
(2,'ROLE_USER')