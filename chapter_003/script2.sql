CREATE TABLE types
(
    id   bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name varchar(255) NOT NULL
);
CREATE TABLE products
(
    id           bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name         varchar(255) NOT NULL,
    type_id      bigint       NOT NULL REFERENCES types (id),
    expired_date timestamp    NOT NULL,
    price        decimal(10, 2)
);
INSERT INTO types (name)
VALUES ('Молоко'),
       ('Колбаса'),
       ('Сыр');
INSERT INTO products (name, type_id, expired_date, price)
VALUES ('Простоквашино', 1, '2020-11-11', 70.80),
       ('Краковская', 2, '2020-12-02', 250.30),
       ('Сливочный', 3, '2020-11-24', 180.50),
       ('Домик в деревне', 1, '2020-12-11', 75.80),
       ('Докторская', 2, '2020-11-02', 230.30),
       ('Пармезан', 3, '2021-01-24', 450.50);

SELECT *
FROM products
         JOIN types ON products.type_id = types.id
WHERE types.name = 'Сыр';

INSERT INTO products (name, type_id, expired_date, price)
VALUES ('Молоко замороженное', 1, '2021-03-02', 100.00);

SELECT *
FROM products
WHERE name LIKE '%мороженное%';

SELECT *
FROM products
WHERE EXTRACT(MONTH FROM expired_date) = EXTRACT(MONTH FROM (current_timestamp + INTERVAL '1 month'));

SELECT *
FROM products
ORDER BY price DESC
LIMIT 1;

SELECT count(id)
FROM products
WHERE type_id = 2;

SELECT *
FROM products
         JOIN types t on products.type_id = t.id
WHERE t.name IN ('Сыр', 'Молоко');

SELECT t.name, COUNT(p.id)
FROM products as p
         JOIN types as t on p.type_id = t.id
GROUP BY t.name
HAVING COUNT(p.id) < 10;

SELECT products.name, types.name
FROM products
         LEFT JOIN types ON products.type_id = types.id;