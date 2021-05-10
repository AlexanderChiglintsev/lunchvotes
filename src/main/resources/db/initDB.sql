DROP TABLE user_roles IF EXISTS;
DROP TABLE votes IF EXISTS;
DROP TABLE users IF EXISTS;
DROP TABLE dishes IF EXISTS;
DROP TABLE daily_menu IF EXISTS;
DROP TABLE restaurants IF EXISTS;
DROP SEQUENCE USER_SEQ IF EXISTS;
DROP SEQUENCE RESTAURANT_SEQ IF EXISTS;
DROP SEQUENCE VOTES_SEQ IF EXISTS;
DROP SEQUENCE DAILY_MENU_SEQ IF EXISTS;
DROP SEQUENCE DISHES_SEQ IF EXISTS;

CREATE SEQUENCE USER_SEQ AS INTEGER START WITH 100;
CREATE SEQUENCE RESTAURANT_SEQ AS INTEGER START WITH 200;
CREATE SEQUENCE VOTES_SEQ AS INTEGER START WITH 300;
CREATE SEQUENCE DAILY_MENU_SEQ AS INTEGER START WITH 400;
CREATE SEQUENCE DISHES_SEQ AS INTEGER START WITH 500;

CREATE TABLE users
(
    id       INTEGER GENERATED BY DEFAULT AS SEQUENCE USER_SEQ PRIMARY KEY,
    name     VARCHAR(255) NOT NULL,
    email    VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);
CREATE UNIQUE INDEX users_email_unique_idx ON USERS (email);

CREATE TABLE user_roles
(
    user_id INTEGER      NOT NULL,
    role    VARCHAR(255) NOT NULL,
    CONSTRAINT user_roles_unique_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE restaurants
(
    id   INTEGER GENERATED BY DEFAULT AS SEQUENCE RESTAURANT_SEQ PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);
CREATE UNIQUE INDEX restaurant_name_unique_idx ON restaurants (name);

CREATE TABLE daily_menu
(
    id            INTEGER GENERATED BY DEFAULT AS SEQUENCE DAILY_MENU_SEQ PRIMARY KEY,
    restaurant_id INTEGER                   NOT NULL,
    date          DATE DEFAULT CURRENT_DATE NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE RESTRICT
);

CREATE TABLE votes
(
    id            INTEGER GENERATED BY DEFAULT AS SEQUENCE VOTES_SEQ PRIMARY KEY,
    date          DATE    NOT NULL,
    daily_menu_id INTEGER NOT NULL,
    user_id       INTEGER NOT NULL,
    CONSTRAINT date_user_unique_idx UNIQUE (date, user_id),
    FOREIGN KEY (daily_menu_id) REFERENCES daily_menu (id) ON DELETE RESTRICT,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE RESTRICT
);

CREATE TABLE dishes
(
    id            INTEGER GENERATED BY DEFAULT AS SEQUENCE DISHES_SEQ PRIMARY KEY,
    description   VARCHAR(100) NOT NULL,
    cost          INTEGER      NOT NULL,
    daily_menu_id INTEGER      NOT NULL,
    CONSTRAINT unique_dish_daily_menu_idx UNIQUE (description, cost, daily_menu_id),
    FOREIGN KEY (daily_menu_id) REFERENCES daily_menu (id) ON DELETE CASCADE
);