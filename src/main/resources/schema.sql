DROP TABLE IF EXISTS dish;
DROP TABLE IF EXISTS vote;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS restaurant;
DROP TYPE IF EXISTS enum;

CREATE TYPE enum AS ENUM ('ROLE_ADMIN','ROLE_USER');

create table restaurant
(
  id serial not null
    constraint restaurant_pkey
    primary key,
  name varchar(255) not null,
  location varchar(255) not null,
  updated_at date not null
)
;

create table dish
(
  id serial not null
    constraint dish_pkey
    primary key,
  dish_name varchar(255) not null,
  price integer not null,
  created_at date not null,
  restaurant_id integer not null
    constraint dish_restaurant_id_fk
    references restaurant
    on delete cascade
)
;

create table users
(
  id serial not null
    constraint user_pkey
    primary key,
  user_name varchar(100) not null,
  password varchar(100) not null,
  email varchar(100) not null
)
;

create unique index user_user_name_uindex
  on users (user_name)
;

create unique index user_email_uindex
  on users (email)
;

create table user_roles
(
  user_id integer not null
    constraint user_roles_users_id_fk
    references users
    on delete cascade,
  roles enum
)
;

create table vote
(
  id serial not null
    constraint vote_pkey
    primary key,
  rang integer not null,
  restaurant_id integer not null
    constraint vote_restaurant_id_fk
    references restaurant
    on delete cascade,
  user_id integer not null
    constraint vote_users_id_fk
    references users
    on delete cascade,
  created_at timestamp not null
)
;