CREATE TABLE users
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(255) NOT NULL,
    email    VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE cars
(
    id             SERIAL PRIMARY KEY,
    body           VARCHAR(255)     NOT NULL,
    brand          VARCHAR(255)     NOT NULL,
    color          VARCHAR(255)     NOT NULL,
    engine         VARCHAR(255)     NOT NULL,
    enginecapacity DOUBLE PRECISION NOT NULL,
    mileage        INTEGER          NOT NULL,
    model          VARCHAR(255)     NOT NULL,
    transmission   VARCHAR(255)     NOT NULL,
    wheel          VARCHAR(255)     NOT NULL,
    year           INTEGER          NOT NULL
);

CREATE TABLE selling
(
    id          SERIAL PRIMARY KEY,
    created     TIMESTAMP NOT NULL,
    description VARCHAR(255) NOT NULL,
    header      VARCHAR(255) NOT NULL,
    price       DOUBLE PRECISION NOT NULL,
    sold        BOOLEAN          NOT NULL,
    car_id      INTEGER NOT NULL REFERENCES cars(id),
    user_id     INTEGER NOT NULL REFERENCES users(id)
);