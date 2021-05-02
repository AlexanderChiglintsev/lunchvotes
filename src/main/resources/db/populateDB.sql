DELETE FROM votes;
DELETE FROM dishes;
DELETE FROM daily_menu;
DELETE FROM restaurants;
DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE user_seq RESTART WITH 100;
ALTER SEQUENCE RESTAURANT_SEQ RESTART WITH 200;
ALTER SEQUENCE VOTES_SEQ RESTART WITH 300;
ALTER SEQUENCE DAILY_MENU_SEQ RESTART WITH 400;
ALTER SEQUENCE DISHES_SEQ RESTART WITH 500;

INSERT INTO users (id, name, email, password)
VALUES (100, 'user', 'user@user.ru', 'user'),
       (101, 'admin', 'admin@admin.com', 'admin');

INSERT INTO user_roles (user_id, role)
VALUES (100, 'USER'),
       (101, 'ADMIN');

INSERT INTO restaurants (id, name)
VALUES (200, 'RestaurantOne'),
       (201, 'RestaurantTwo'),
       (202, 'RestaurantThree');

INSERT INTO daily_menu (id, restaurant_id, date)
VALUES (400, 200, '2021-05-01'),
       (401, 201, '2021-05-01'),
       (402, 202, '2021-05-01'),
       (403, 200, '2021-05-02'),
       (404, 201, '2021-05-02'),
       (405, 202, '2021-05-02');

INSERT INTO dishes (id, description, cost, daily_menu_id)
VALUES (500, 'Meet', 1000, 400),
       (501, 'Coffee', 250, 400),
       (502, 'Cherry pie', 4000, 400),
       (503, 'Fish', 1200, 401),
       (504, 'Black tea', 150, 401),
       (505, 'Vegetables', 800, 402),
       (506, 'Juice', 250, 402),
       (507, 'Pancake', 250, 402),
       (508, 'Beef', 1400, 403),
       (509, 'Beer', 400, 403),
       (510, 'Spaghetti', 900, 404),
       (511, 'Fresh vegetables', 500, 404),
       (512, 'Cold tea', 160, 404),
       (513, 'Chicken', 950, 405),
       (514, 'Potato', 200, 405),
       (515, 'White vine', 450, 405),
       (516, 'Apple', 100, 405);

INSERT INTO votes (id, date, restaurant_id, user_id)
VALUES (300, '2021-04-01', 200, 100),
       (301, '2021-04-01', 200, 101),
       (302, '2021-04-02', 201, 100),
       (303, '2021-04-02', 202, 101);


