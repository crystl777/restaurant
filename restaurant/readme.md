The task is: Build a voting system for deciding where to have lunch.
====
Design and implement a REST API using Hibernate/Spring/SpringMVC (or Spring-Boot) without frontend.

2 types of users: admin and regular users
Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
Menu changes each day (admins do the updates)
Users can vote on which restaurant they want to have lunch at
Only one vote counted per user
If user votes again the same day:
If it is before 11:00 we asume that he changed his mind.
If it is after 11:00 then it is too late, vote can't be changed
Each restaurant provides new menu each day.

The implement:
===
The project uses the HSQL database. Five tables are created at startup: users, users_role, restaurants, votes.

Running the application
==
Clone application or download and unzip zip-archive from github: https://github.com/crystl777/restaurant.git

In the terminal, go to the folder with the application.
Run the command "mvn clean package".
After message "BUILD SUCCESS" run command "mvn cargo:run".


Examples of curl commands for admin:
-----------------------------------

create new user

    curl -s -X POST -d '{"name" : "New User", "email" : "newuser@ya.ru", "password" : "newuser", "roles" : ["USER"]}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/restaurant/rest/admin/users --user admin@gmail.com:admin

create new restaurant

    curl -s -X POST -d '{"name" : "FrogHeaven"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/restaurant/rest/admin/restaurants --user admin@gmail.com:admin

get all restaurants

    curl -s http://localhost:8080/restaurant/rest/admin/restaurants --user admin@gmail.com:admin

get all today's dishes for restaurant

    curl -s http://localhost:8080/restaurant/rest/admin/dishes/100002 --user admin@gmail.com:admin

get all votes

    curl -s http://localhost:8080/restaurant/rest/admin/restaurants/votes --user admin@gmail.com:admin

Examples of curl commands for general users:
--------------------------------------------

register

    curl -s -i -X POST -d '{"name":"New User","email":"test@mail.ru","password":"test-password"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/restaurant/rest/profile/register

update user information

    curl -s -X PUT -d '{"name":"newUser","email":"admin@gmail.com","password":"newPassword"}' -H 'Content-Type: application/json' http://localhost:8080/restaurant/rest/profile/ --user user@yandex.ru:password

vote for restaurant

    curl -s -X POST -d http://localhost:8080/restaurant/rest/profile/restaurants/100002 --user user@yandex.ru:password

get all restaurants with dishes

    curl -s http://localhost:8080/restaurant/rest/profile/restaurants?withDishes=true --user user@yandex.ru:password
