CREATE TABLE users (
  id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  name varchar(255) NOT NULL,
  created_at date NOT NULL
);

INSERT INTO users (name, created_at) VALUES ('Ivan', '2020-03-02');
UPDATE users SET name = 'Petr' WHERE name = 'Ivan';
DELETE FROM users WHERE name = 'Petr';
SELECT * FROM users;