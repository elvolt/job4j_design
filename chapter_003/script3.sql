CREATE DATABASE fifth_db;

CREATE TABLE departments
(
    id   bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name varchar(255) NOT NULL
);

CREATE TABLE employers
(
    id            bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name          varchar(255) NOT NULL,
    department_id bigint REFERENCES departments (id)
);

INSERT INTO departments (name)
VALUES ('IT'),
       ('accounting'),
       ('technical'),
       ('secret');

INSERT INTO employers (name, department_id)
VALUES ('Ivan Ivanov', 1),
       ('Petr Petrov', 2),
       ('Semen Semenov', 3),
       ('Sergey Sergeev', 1),
       ('Igor Sechin', NULL);

SELECT *
FROM departments d
         LEFT JOIN employers e ON d.id = e.department_id;

SELECT *
FROM departments d
         RIGHT JOIN employers e ON d.id = e.department_id;

SELECT *
FROM departments
         CROSS JOIN employers;

SELECT e.name
FROM employers e
         LEFT JOIN departments d on d.id = e.department_id
WHERE department_id IS NULL;

SELECT e.name, d.name
FROM employers e
         LEFT JOIN departments d on e.department_id = d.id;

SELECT e.name, d.name
FROM departments d
         RIGHT JOIN employers e on e.department_id = d.id;

CREATE TYPE gender AS ENUM ('male', 'female');
CREATE TABLE teens
(
    id     bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name   varchar(255),
    gender gender
);
INSERT INTO teens (name, gender)
VALUES ('Ivan', 'male'),
       ('Sveta', 'female'),
       ('Petr', 'male');

SELECT t1.name, t2.name
FROM teens t1
         CROSS JOIN teens t2
WHERE t1.gender != t2.gender;