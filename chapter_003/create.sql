CREATE DATABASE second_db;

CREATE TABLE rules (
   id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
   name varchar(255)
);

CREATE TABLE roles (
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name varchar(255) NOT NULL
);

CREATE TABLE rules_roles (
    rule_id bigint NOT NULL REFERENCES rules (id),
    role_id bigint NOT NULL REFERENCES roles (id),
    UNIQUE (rule_id, role_id)
);

CREATE TABLE users(
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    first_name varchar(100) NOT NULL,
    last_name varchar(100) NOT NULL,
    role_id bigint NOT NULL REFERENCES roles (id)
);

CREATE TABLE states (
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name varchar(255) NOT NULL
);

CREATE TABLE categories (
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name varchar(255) NOT NULL
);

CREATE TABLE items (
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    text varchar(255) NOT NULL,
    category_id bigint NOT NULL REFERENCES categories (id),
    user_id bigint NOT NULL REFERENCES users (id),
    state_id bigint NOT NULL REFERENCES states (id)
);

CREATE TABLE comments (
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    text varchar(255) NOT NULL,
    item_id bigint NOT NULL REFERENCES items (id)
);

CREATE TABLE attachs (
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    path varchar(255) NOT NULL,
    item_id bigint NOT NULL REFERENCES items (id)
);

INSERT INTO rules (name) VALUES ('read'), ('edit'), ('write');
INSERT INTO roles (name) VALUES ('admin'), ('user');
INSERT INTO rules_roles VALUES (1, 1), (2, 1), (3, 1), (3, 2);
INSERT INTO second_db.public.users (first_name, last_name, role_id)
VALUES
    ('Ivan', 'Ivanov', 1),
    ('Petr', 'Petrov', 2),
    ('Pavel', 'Pavlov', 2);
INSERT INTO states (name) VALUES ('active'), ('closed');
INSERT INTO categories (name) VALUES ('first category'), ('second category');
INSERT INTO items (text, category_id, user_id, state_id)
VALUES
    ('Item 1', 1, 2, 1),
    ('Item 2', 2, 3, 1);
INSERT INTO comments (text, item_id)
VALUES
    ('Comment for item 1', 1),
    ('Comment for item 2', 2);
INSERT INTO attachs (path, item_id)
VALUES
    ('path\to\attach1', 1),
    ('path\to\attach2', 1);