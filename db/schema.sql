CREATE TABLE users
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR(255) NOT NULL,
    email    VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE selling
(
    id          SERIAL PRIMARY KEY,
    description TEXT         NOT NULL,
    model       VARCHAR(255) NOT NULL,
    body        VARCHAR(255) NOT NULL,
    sold        BOOLEAN      NOT NULL,
    created     TIMESTAMP    NOT NULL,
    photo       BOOLEAN      NOT NULL,
    user_id     INT          NOT NULL REFERENCES users (id)
);