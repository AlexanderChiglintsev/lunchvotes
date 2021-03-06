[![Build Status](https://travis-ci.com/AlexanderChiglintsev/lunchvotes.svg?branch=main)](travis-ci.com/AlexanderChiglintsev/lunchvotes)

# Lunchvotes 
Voting system for help choose restaurant for lunch.

## Formulation of the problem
Build a voting system for deciding where to have lunch.

 * 2 types of users: admin and regular users
 * Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
 * Menu changes each day (admins do the updates)
 * Users can vote on which restaurant they want to have lunch at
 * Only one vote counted per user
 * If user votes again the same day:
   - If it is before 11:00 we assume that he changed his mind.
   - If it is after 11:00 then it is too late, vote can't be changed

Each restaurant provides a new menu each day.

## Running
- You need download and install [Apache Maven 3.6.3](https://maven.apache.org/download.cgi) or higher, [Java 15](https://www.oracle.com/ru/java/technologies/javase-downloads.html) or higher.
- Download project from repository and go to project root path in your CLI.
- Execute command: **mvn clean package -DskipTests=true org.codehaus.cargo:cargo-maven3-plugin:1.9.2:run**
- Application will deployed at application context `lunchvotes` and available at following link: [http://localhost:8080/lunchvotes](http://localhost:8080/lunchvotes) (you see index.jsp page)

## API
Application has API in REST style (without frontend).  
Application contain SwaggerUI, after running documentation is available at following link: [http://localhost:8080/lunchvotes/swagger-ui.html](http://localhost:8080/lunchvotes/swagger-ui.html)  
After starting database consist two users:
* user (username=user@user.ru, password=user)
* admin (username=admin@admin.com, password=admin)

### Curl samples

#### Get all restaurants (accessed for admin only)
`curl -s http://localhost:8080/lunchvotes/restaurants --user admin@admin.com:admin`

#### Get restaurant 200 (accessed for admin only)
`curl -s http://localhost:8080/lunchvotes/restaurants/200 --user admin@admin.com:admin`

#### Save restaurant TestRestaurant (accessed for admin only)
`curl -s -X "POST" http://localhost:8080/lunchvotes/restaurants -H "Content-Type: application/json" -d "{"id":"","name":"TestRestaurant"}" --user admin@admin.com:admin`

#### Create daily menu for a restaurant with id = 202 (accessed for admin only)
`curl -s -X "POST" http://localhost:8080/lunchvotes/dailyMenus -H "Content-Type: application/x-www-form-urlencoded" -d "restaurantId=202" --user admin@admin.com:admin`

#### Add the dish for daily menu with an id = 406 (accessed for admin only)
`curl -s -X "PATCH" http://localhost:8080/lunchvotes/dailyMenus/406 -H "Content-Type: application/json" -d "{"id":"","description":"test description","cost":777}" --user admin@admin.com:admin`

#### Get all daily menus
`curl -s http://localhost:8080/lunchvotes/dailyMenus --user admin@admin.com:admin`

#### Get daily menu with an id = 406
`curl -s http://localhost:8080/lunchvotes/dailyMenus/406 --user admin@admin.com:admin`

#### Save vote for daily menu with an id = 406
`curl -s -X "POST" http://localhost:8080/lunchvotes/votes -H "Content-Type: application/x-www-form-urlencoded" -d "dmId=406" --user user@user.ru:user`

#### Get vote with an id = 303
`curl -s http://localhost:8080/lunchvotes/votes/303 --user user@user.ru:user`

#### Get count votes for restaurants today
`curl -s http://localhost:8080/lunchvotes/votes/today --user user@user.ru:user`

#### Get all users (accessed for admin only)
`curl -s http://localhost:8080/lunchvotes/users --user admin@admin.com:admin`

#### Get user with an id = 100 (admin may access to any account, user only to own)
`curl -s http://localhost:8080/lunchvotes/users/100 --user admin@admin.com:admin`

#### Register new user
`curl -s -X "POST" http://localhost:8080/lunchvotes/users -H "Content-Type: application/json" -d "{"id":"","name":"test_name","email":"test@test.tt","password\":\"password\"}"`

#### Grants admin rules to user with id = 100 (accessed for admin only)
`curl -s -X "PATCH" http://localhost:8080/lunchvotes/users/100 -H "Content-Type: application/json" --user admin@admin.com:admin`
