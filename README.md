#group ->L27(P2)

FootballApp is a web-site that displays real-time data concerning world football. 
Whitch means people can see the results of live games; all teams;all leagues;All players.
For the backend we used Springboot(java) and create a docker which contains a container that runs a mysql database(see the commands below)
For the frontend we used a javascript framework: React js(see the commands below).

Database runs on port: 3306;
Api runs on port: 8080;
Frontend runs on port: 3000;

To run the web-app run the following commands by order:

Commands to CREATE DATABASE and RUN it:
1-Open a terminal and run the following commands:
----------------------------------------
docker pull mysql:latest
sudo docker run --name demo -d -p3306:3306 -e MYSQL_ROOT_PASSWORD=password mysql:latest
sudo docker exec -it demo /bin/bash
mysql -uroot -ppassword
CREATE DATABASE football_db;
USE football_db;
-----------------------------------------------
Commands to RUN KAFKA:
2-Open other terminal and run the following commands:
To make Kafka run:
(MONTEIRO ESCREVE!!!!)
-----------------------------------------------
commands to RUN API:
3-Open other terminal and run the api:
Open the FootballApp folder(Api folder)
Go to the FootballApp/src/main/java/com/example/demo
which contains the FootballAppApplication.java (main class) and run it (right click on the file and RUN)
-------------------------------------------------------------------------------------------------------
4-Finally to RUN the FRONTEND:
Open other terminal
(for running React js you need npm)
if you dont have npm justo type the following commmand:
  $ curl -sL https://deb.nodesource.com/setup_10.x | sudo -E bash -
  $ sudo apt install nodejs
if you have npm just type:
  $ sudo npm start
Open a web browser and type:
localhost:3000
---------------------------------------------------------------------------
Now you are able to see our web app working! :)
Note: In case you don't see info in the live section(home) it's because due to the pandemic all games have been cancelled
and therefore no info is displayed.
