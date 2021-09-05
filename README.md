Tic Tac Toe Kata

A sample Tic-Tac-Toe application developed using Test Driven Development approach.

As one of the requirement was to use Java/Spring, this application is developed as a Spring Boot REST API.

Test
all tests are in com.game.tictoctoe.TictoctoeApplicationTests.java
cd to the project directory and execute :

mvn test

No client has been developed. But it's possible to interact and test the API through swagger openApi url:

http://localhost:8080/swagger-ui-custom.html or http://localhost:8080/api-docs

Running the project

Download from Git

To download the project, simply open a terminal window and execute the following command :

git clone https://github.com/2021-DEV1-061/Tic-Tac-Toe.git Execute the tests

all tests are in com.game.tictoctoe.TictoctoeApplicationTests.java
cd to the project directory and execute :

mvn test

When all test are executed, a test report will be available in the /target/surfire-report/. Launch the Tic Tac Toe API

In a terminal window, cd to the project directory and execute :

mvn spring-boot:run

Once started, the API is available at the http://localhost:8080/tic-tac-toe/api and an open API swagger is available at http://localhost:8080/swagger-ui-custom.html

Interacting with the game API

Interacting with the game can be as simple as sending CURL commands. The one principal command is for: play the game

Play the game

To place a move, POST to the http://localhost:8080/tic-tac-toe/api/play url with the RequestGamingDto information in the body :

curl --header "Content-Type: application/json" --request POST --data '{"playerSymbol" : "X", "xposition" : 1, "xposition" : 1}' http://localhost:8080/tic-tac-toe/api/play

OR

we can use the open swagger api to put directly the RequestGamingDto { "playerSymbol": "X", "xposition": 1, "yposition": 1 } and see the response of the api

The API will respond with the current status of the game :

{ "board": { "boardMarkers": [ [ { "value": "X", "rowIndex": 0, "columnIndex": 0, "empty": false }, { "value": "BLANK", "rowIndex": 0, "columnIndex": 1, "empty": true }, { "value": "BLANK", "rowIndex": 0, "columnIndex": 2, "empty": true } ], [ { "value": "BLANK", "rowIndex": 1, "columnIndex": 0, "empty": true }, { "value": "BLANK", "rowIndex": 1, "columnIndex": 1, "empty": true }, { "value": "BLANK", "rowIndex": 1, "columnIndex": 2, "empty": true } ], [ { "value": "BLANK", "rowIndex": 2, "columnIndex": 0, "empty": true }, { "value": "BLANK", "rowIndex": 2, "columnIndex": 1, "empty": true }, { "value": "BLANK", "rowIndex": 2, "columnIndex": 2, "empty": true } ] ] }, "gameStatus": "INPROGRESS", "movements": [ { "player": { "name": "FIRST_PLAYER", "playerSymbol": "PLAYER_X" } } ], "winnerPlayer": null }