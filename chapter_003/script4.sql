CREATE DATABASE sixth_db;

CREATE TABLE bodies
(
    id   INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    type varchar(100) NOT NULL
);

INSERT INTO bodies (type)
VALUES ('sedan'),
       ('station wagon'),
       ('hatchback'),
       ('сoupe'),
       ('Limousine');

CREATE TABLE engines
(
    id   INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    type varchar(100) NOT NULL
);

INSERT INTO engines (type)
VALUES ('petrol'),
       ('diesel'),
       ('gas');

CREATE TABLE transmissions
(
    id   INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    type varchar(100) NOT NULL
);

INSERT INTO transmissions (type)
VALUES ('manual'),
       ('automatic'),
       ('robotic'),
       ('variable');

CREATE TABLE cars
(
    id              INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    mark            varchar(255) NOT NULL,
    model           varchar(255) NOT NULL,
    body_id         INT          NOT NULL REFERENCES bodies (id),
    engine_id       INT          NOT NULL REFERENCES engines (id),
    transmission_id INT          NOT NULL REFERENCES transmissions (id)
);

INSERT INTO cars (mark, model, body_id, engine_id, transmission_id)
VALUES ('Ford', 'Focus', 1, 1, 1),
       ('Lada', 'Vesta', 2, 1, 2),
       ('Lada', 'Vesta', 1, 1, 1),
       ('Mazda', '6', 1, 2, 3);

SELECT (c.mark || ' ' || c.model) AS car, b.type AS body, e.type AS engine, t.type AS transmission
FROM cars c
         JOIN bodies b on c.body_id = b.id
         JOIN engines e on c.engine_id = e.id
         JOIN transmissions t on c.transmission_id = t.id;

SELECT b.type
FROM bodies b
         LEFT JOIN cars c on b.id = c.body_id
WHERE c.id IS NULL;

SELECT e.type
FROM engines e
         LEFT JOIN cars c on e.id = c.engine_id
WHERE c.id IS NULL;

SELECT t.type
FROM transmissions t
         LEFT JOIN cars c on t.id = c.transmission_id
WHERE c.id IS NULL;