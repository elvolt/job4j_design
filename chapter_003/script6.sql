CREATE TABLE meetings
(
    id   serial PRIMARY KEY,
    name varchar(120) NOT NULL
);

CREATE TABLE users
(
    id   serial PRIMARY KEY,
    name varchar(120) NOT NULL
);

CREATE TYPE status AS ENUM ('confirmed', 'rejected');

CREATE TABLE applications
(
    id          serial PRIMARY KEY,
    meeting_id  integer REFERENCES meetings (id),
    user_id     integer REFERENCES users (id),
    user_status status NOT NULL,
    UNIQUE (meeting_id, user_id)
);

INSERT INTO meetings (name)
VALUES ('meeting 1'),
       ('meeting 2'),
       ('meeting 3'),
       ('meeting 4'),
       ('meeting 5');

INSERT INTO users (name)
VALUES ('user 1'),
       ('user 2'),
       ('user 3'),
       ('user 4'),
       ('user 5'),
       ('user 6'),
       ('user 7'),
       ('user 8'),
       ('user 9'),
       ('user 10');

INSERT INTO applications (meeting_id, user_id, user_status)
VALUES (1, 1, 'rejected'),
       (1, 8, 'confirmed'),
       (1, 9, 'confirmed'),
       (2, 5, 'confirmed'),
       (2, 4, 'rejected'),
       (2, 3, 'confirmed'),
       (3, 1, 'confirmed'),
       (3, 10, 'confirmed'),
       (3, 9, 'confirmed'),
       (3, 8, 'confirmed'),
       (2, 7, 'confirmed'),
       (1, 7, 'confirmed'),
       (1, 4, 'confirmed'),
       (2, 10, 'rejected'),
       (2, 9, 'confirmed'),
       (3, 7, 'rejected'),
       (1, 2, 'confirmed');


--Нужно написать запрос, который получит список всех заявок
-- и количество подтвердивших участников.
SELECT t1.name, t1.all_applications, t2.confirmed_applications
FROM (SELECT m.name, COUNT(a.id) as all_applications
      FROM meetings m
               JOIN applications a on m.id = a.meeting_id
      GROUP BY m.name) t1
         JOIN
     (SELECT m.name, COUNT(a.id) as confirmed_applications
      FROM meetings m
               JOIN applications a on m.id = a.meeting_id
      WHERE a.user_status = 'confirmed'
      GROUP BY m.name) t2 ON t2.name = t1.name;

--Нужно получить все совещания, где не было ни одной заявки на посещения
SELECT meetings.name
FROM meetings
         LEFT JOIN applications ON meetings.id = applications.meeting_id
WHERE applications.id IS NULL;