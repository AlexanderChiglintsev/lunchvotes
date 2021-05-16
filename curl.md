### curl samples (application deployed at application context `lunchvotes`).

#### Get all restaurants
`curl -s http://localhost:8080/lunchvotes/restaurants --user admin@admin.com:admin`

#### Get restaurant 200
`curl -s http://localhost:8080/lunchvotes/restaurants/200 --user admin@admin.com:admin`

#### Save restaurant TestRestaurant
`curl -s -X "POST" http://localhost:8080/lunchvotes/restaurants -H "Content-Type: application/json" -d "{\"id\":\"\",\"name\":\"TestRestaurant\"}" --user admin@admin.com:admin`

#### Create daily menu for a restaurant with id = 202
`curl -s -X "POST" http://localhost:8080/lunchvotes/dailyMenus -H "Content-Type: application/x-www-form-urlencoded" -d "restaurantId=202" --user admin@admin.com:admin`

#### Add the dish for daily menu with an id = 406
`curl -s -X "PATCH" http://localhost:8080/lunchvotes/dailyMenus/406 -H "Content-Type: application/json" -d "{\"id\":\"\",\"description\":\"test description\",\"cost\":777}" --user admin@admin.com:admin`

#### Get all daily menus
`curl -s http://localhost:8080/lunchvotes/dailyMenus --user admin@admin.com:admin`

#### Get daily menu with an id = 406
`curl -s http://localhost:8080/lunchvotes/dailyMenus/406 --user admin@admin.com:admin`

#### Save vote for daily menu with an id = 406
`curl -s -X "POST" http://localhost:8080/lunchvotes/votes -H "Content-Type: application/x-www-form-urlencoded" -d "dmId=406" --user user@user.ru:user`

#### Get vote with an id = 303
`curl -s http://localhost:8080/lunchvotes/votes/303 --user admin@admin.com:admin`

#### Get count votes for restaurants today
`curl -s http://localhost:8080/lunchvotes/votes/today --user admin@admin.com:admin`