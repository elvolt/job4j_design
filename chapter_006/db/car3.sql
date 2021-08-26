CREATE TABLE IF NOT EXISTS engine
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS driver
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS car
(
    id        SERIAL PRIMARY KEY,
    name      VARCHAR(100),
    engine_id INT NOT NULL REFERENCES engine (id)
);

CREATE TABLE IF NOT EXISTS history_owner
(
    id        SERIAL PRIMARY KEY,
    driver_id INT NOT NULL REFERENCES driver (id),
    car_id    INT NOT NULL REFERENCES car (id),
    UNIQUE (driver_id, car_id)
);