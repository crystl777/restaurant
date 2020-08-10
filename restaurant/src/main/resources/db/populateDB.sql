DELETE FROM votes;
DELETE FROM user_roles;
DELETE FROM dishes;
DELETE FROM restaurants;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO restaurant (name)
VALUES ('Restaurant')

INSERT INTO dishes (name, price)
VALUES ('Dish', 100)