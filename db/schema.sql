create database selling;

create table cars (
                      id serial primary key,
                      brand varchar,
                      model varchar,
                      transmission varchar,
                      body varchar,
                      engine varchar,
                      year int,
                      price int,
                      status boolean
);

create table photos (
                        id serial primary key,
                        is_available boolean,
                        path varchar
);

create table users (
                       id serial primary key,
                       name varchar
);

alter table cars add column created timestamp;