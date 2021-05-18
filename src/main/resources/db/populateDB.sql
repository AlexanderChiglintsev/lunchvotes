DELETE FROM votes;
DELETE FROM dishes;
DELETE FROM daily_menu;
DELETE FROM restaurants;
DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE USER_SEQ RESTART WITH 100;
ALTER SEQUENCE RESTAURANT_SEQ RESTART WITH 200;
ALTER SEQUENCE VOTES_SEQ RESTART WITH 300;
ALTER SEQUENCE DAILY_MENU_SEQ RESTART WITH 400;
ALTER SEQUENCE DISHES_SEQ RESTART WITH 500;

/*start with 100*/
INSERT INTO users (name, email, password)
VALUES ('user', 'user@user.ru', 'user'),
       ('admin', 'admin@admin.com', 'admin');

INSERT INTO user_roles (user_id, role)
VALUES (100, 'USER'),
       (101, 'ADMIN');

/*start with 200*/
INSERT INTO restaurants (name)
VALUES ('RestaurantOne'),
       ('RestaurantTwo'),
       ('RestaurantThree');

/*start with 400*/
INSERT INTO daily_menu (restaurant_id, date)
VALUES (200, '2021-05-01'),
       (201, '2021-05-01'),
       (202, '2021-05-01'),
       (200, '2021-05-02'),
       (201, '2021-05-02'),
       (202, '2021-05-02');

/*start with 500*/
INSERT INTO dishes (description, cost, daily_menu_id)
VALUES ('Meet', 1000, 400),
       ('Coffee', 250, 400),
       ('Cherry pie', 400, 400),
       ('Fish', 1200, 401),
       ('Black tea', 150, 401),
       ('Vegetables', 800, 402),
       ('Juice', 250, 402),
       ('Pancake', 250, 402),
       ('Beef', 1400, 403),
       ('Beer', 400, 403),
       ('Spaghetti', 900, 404),
       ('Fresh vegetables', 500, 404),
       ('Cold tea', 160, 404),
       ('Chicken', 950, 405),
       ('Potato', 200, 405),
       ('White vine', 450, 405),
       ('Apple', 100, 405);

/*start with 300*/
INSERT INTO votes (date, daily_menu_id, user_id)
VALUES ('2021-05-01', 400, 100),
       ('2021-05-01', 400, 101),
       ('2021-05-02', 404, 100);
