**REST API application**

A voting system for deciding where to have lunch.

 * 2 types of users: admin and regular users
 * Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
 * Menu changes each day (admins do the updates)
 * Users can vote on which restaurant they want to have lunch at
 * Only one vote counted per user
 * If user votes again the same day:
    - If it is before 11:00 we asume that he changed his mind.
    - If it is after 11:00 then it is too late, vote can't be changed

Each restaurant provides new menu each day.

All changes in the database can only be done by admin. 
Links vary depending on which user requests information.
You can use HAL-Browser for easy navigation through the app.
Simply use `http://localhost:8080/browser/index.html` into your browser.
If you want see real project, go to `https://restaurantvoting.herokuapp.com`
and use curl samples!

CURL samples

Entry point
`curl 'http://localhost:8080/api' -i -X GET`

User entry point
`curl 'http://localhost:8080/api' -i -u 'user:password' -X GET`

Admin entry point
`curl 'http://localhost:8080/api' -i -u 'admin:admin' -X GET`

Receive all actual restaurants for current day without auth
`curl 'http://localhost:8080/api/restaurants' -i -X GET`

with user auth
`curl 'http://localhost:8080/api/restaurants' -i -u 'user:password' -X GET`

with admin auth
`curl 'http://localhost:8080/api/restaurants' -i -u 'admin:admin' -X GET`

Registration new user
`curl 'http://localhost:8080/api/registration' -i -X POST \
     -H 'Content-Type: application/json;charset=UTF-8' \
     -d '{
 	"userName":"user1",
 	"password":"user1",
 	"email":"user1@mail.ru"
 }'`
 
Registration new admin(only by another admin).

`curl 'http://localhost:8080/api/registration/admin' -i -u 'admin:admin' -X POST \
     -H 'Content-Type: application/json;charset=UTF-8' \
     -d '{
 	"userName":"admin1",
 	"password":"admin1",
 	"email":"admin1@mail.ru"
 }'`
 
Add votes.
`curl 'http://localhost:8080/api/votes/1' -i -u 'user:password' -X POST \
     -H 'rank: 10'`

where `rank` - quantity of votes(may be integer)
and `/votes/{id}` - id - number of restaurant.

Auth user can vote many times between 00:00 and 11:00, and one time after 11:00.

Only for Admin.

Get all restaurants
`curl 'http://localhost:8080/api/restaurants/all' -i -u 'admin:admin' -X GET`

Get one restaurant
`curl 'http://localhost:8080/api/restaurants/1' -i -u 'admin:admin' -X GET`

where `/restaurants/{id}` - number of restaurant

Create restaurant
`curl 'http://localhost:8080/api/restaurants' -i -u 'admin:admin' -X POST \
     -H 'Content-Type: application/json;charset=UTF-8' \
     -d '{
 	"name":"New",
 	"location":"ChineMall"
 }'`
 
Update restaurant
`curl 'http://localhost:8080/api/restaurants/1' -i -u 'admin:admin' -X PATCH \
     -H 'Content-Type: application/json;charset=UTF-8' \
     -d '{
 	"name":"Updated"
 }'`
 
Delete Restaurant(Cascade)
`curl 'http://localhost:8080/api/restaurants/1' -i -u 'admin:admin' -X DELETE`

Add dish
`curl 'http://localhost:8080/api/dishes/1' -i -u 'admin:admin' -X POST \
     -H 'Content-Type: application/json;charset=UTF-8' \
     -d '{
 	"name":"meal",
 	"price":100
 }'`
 
where `/dishes/1` - number of restaurant

Update dish
`curl 'http://localhost:8080/api/dishes/1' -i -u 'admin:admin' -X PATCH \
     -H 'Content-Type: application/json;charset=UTF-8' \
     -d '{
 	"name":"update",
 	"price":1000,
 	"restaurant":2
 }'`
 
 where `name` - name of dish,
 `price` - price of dish,
 `restaurant`-number of new restaurant
 
Delete dish
 `curl 'http://localhost:8080/api/dishes/1' -i -u 'admin:admin' -X DELETE`
 
