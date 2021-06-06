# kalah Game with quarkus project

Kalah, also called Kalaha or Mancala, is a game in the mancala family invented in the United States by William Julius Champion, Jr. in 1940. 
This game is sometimes also called "Kalahari", possibly by false etymology from the Kalahari desert in Namibia.

The project is a *​Java RESTful Web Service* ​that runs a game of 6-stone Kalah. 
The general rules of the game are explained on Wikipedia: https://en.wikipedia.org/wiki/Kalah and also below in this document. 
Please note that the Wikipedia article explains 3 and 4-stone Kalah; our implementation is 6-stone game.

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

#### Kalah Rules
Each of the two players has **​six pits** ​in front of him/her. To the right of the six pits, each player has a larger pit, his Kalah or house.
At the start of the game, six stones are put in each pit.
The player who begins picks up all the stones in any of their own pits, and sows the stones on to the right, one in each of the following pits, including his own Kalah. No stones are put in the opponent's' Kalah. If the players last stone lands in his own Kalah, he gets another turn. This can be repeated any number of times before it's the other player's turn.
When the last stone lands in an own empty pit, the player captures this stone and all stones in the opposite pit (the other players' pit) and puts them in his own Kalah.
The game is over as soon as one of the sides run out of stones. The player who still has stones in his/her pits keeps them and puts them in his/hers Kalah. The winner of the game is the player who has the most stones in his Kalah.
#### Endpoint design specification
1. ​Creation of the game should be possible with the command:
   curl --header "Content-Type: application/json" \
   --request POST \
   http://<host>:<port>/games
   Response:
   HTTP code: 201
   Response Body: { "id": "1234", "uri": "http://<host>:<port>/games/1234" }
   id: unique identifier of a game
   url: link to the game created
2. ​Make a move:
   curl --header "Content-Type: application/json" \
   --request PUT \
   http://<host>:<port>/games/{gameId}/pits/{pitId}
   gameId: unique identifier of a game
   pitId: id of the pit selected to make a move. Pits are numbered from 1 to 14 where 7 and 14 are the kalah (or house) of each player
   Response:
   HTTP code: 200
   Response Body:
   {"id":"1234","url":"http://<host>:<port>/games/1234","status":{"1":"4","2":"4","3":"4","4":"4","5":"4","6":"4","7":"0","8":"4"," 9":"4","10":"4","11":"4","12":"4","13":"4","14":"0"}}
   status: json object key-value, where key is the pitId and value is the number of stones in the pit


## Running the application in dev mode

You can run the application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.


# Endpoint Design Specification
> **_NOTE:_**  REST API is available in Swagger UI , which is available in dev mode only at http://localhost:8080/q/swagger-ui/

The API specification yml file is located at: 
`src/main/resources/META-INF/openapi.yml`

or when application is started you may download it:
```
curl http://localhost:8080/openapi
```

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/kalah-with-quarkus-1.0.0-SNAPSHOT-runner`

