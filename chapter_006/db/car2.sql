CREATE TABLE IF NOT EXISTS mark
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS model
(
    id      SERIAL PRIMARY KEY,
    name    VARCHAR(100),
    mark_id INT REFERENCES mark (id)
);