CREATE TABLE company
(
    id   integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
    id         integer NOT NULL,
    name       character varying,
    company_id integer references company (id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

INSERT INTO company
VALUES (1, 'company1'),
       (2, 'company2'),
       (3, 'company3'),
       (4, 'company4'),
       (5, 'company5'),
       (6, 'company6');

INSERT INTO person
VALUES (1, 'name1', 3),
       (2, 'name2', 5),
       (3, 'name3', 6),
       (4, 'name4', 5),
       (5, 'name5', 1),
       (6, 'name6', 1),
       (7, 'name7', 2),
       (8, 'name8', 3);

SELECT person.name as person, company.name as company
FROM person
         JOIN company ON person.company_id = company.id
WHERE company_id != 5;

WITH CTE AS (
    SELECT company.name, COUNT(person.id) as count
    FROM company
             JOIN person on company.id = person.company_id
    GROUP BY company.name)
SELECT *
FROM CTE
WHERE count IN (SELECT MAX(count) FROM CTE);