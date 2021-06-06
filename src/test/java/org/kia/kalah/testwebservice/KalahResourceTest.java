package org.kia.kalah.testwebservice;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.notNullValue;

@QuarkusTest
public class KalahResourceTest {
    private Integer gameId;
    @BeforeEach
    public void beforeEach() {
        if(gameId == null){
            gameId = given()
                    .when().post("/games")
                    .then()
                    .statusCode(Response.Status.CREATED.getStatusCode())
                    .extract()
                    .path("id");
        }
    }
    @Test
    public void testCreateNewGame(){
        given()
                .when().post("/games")
                .then()
                .statusCode(Response.Status.CREATED.getStatusCode())
                .assertThat()
                .body("id", notNullValue())
                .body("url", notNullValue())
        ;
    }
    @Test
    public void testAddPlayer(){
        given()
                .when().put("/games/add/"+gameId+"/player/Kiavash")
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .assertThat()
                .body("players.0",equalTo("Kiavash"))
                .body("playerNumber",equalTo(0))
                ;
    }


    @Test
    public void testGetBoardStatus(){
        given()
                .when().get("/games/"+gameId+"/boardStatus")
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .assertThat()
                .body("id",equalTo(gameId))
                .body("uri",notNullValue())
                .body("status.1",equalTo(6))
                .body("status.2",equalTo(6))
                .body("status.3",equalTo(6))
                .body("status.4",equalTo(6))
                .body("status.5",equalTo(6))
                .body("status.6",equalTo(6))
                .body("status.7",equalTo(0))//Kalah
                .body("status.8",equalTo(6))
                .body("status.9",equalTo(6))
                .body("status.10",equalTo(6))
                .body("status.11",equalTo(6))
                .body("status.12",equalTo(6))
                .body("status.13",equalTo(6))
                .body("status.14",equalTo(0))
                .body("playerNumber",equalTo(0))
                .body("players.0",equalTo(""))
                .body("players.1",equalTo(""))
                .body("isGameOver",equalTo(false))
        ;
    }


    @Test
    public void testPlay(){
        given()
                .when().put("/games/"+gameId+"/pits/1")
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .assertThat()
                .body("id",equalTo(gameId))
                .body("uri",notNullValue())
                .body("status.1",equalTo(0))
                .body("status.2",equalTo(7))
                .body("status.3",equalTo(7))
                .body("status.4",equalTo(7))
                .body("status.5",equalTo(7))
                .body("status.6",equalTo(7))
                .body("status.7",equalTo(1))//Kalah
                .body("status.8",equalTo(6))
                .body("status.9",equalTo(6))
                .body("status.10",equalTo(6))
                .body("status.11",equalTo(6))
                .body("status.12",equalTo(6))
                .body("status.13",equalTo(6))
                .body("status.14",equalTo(0))
                .body("playerNumber",equalTo(0))
                .body("players.0",equalTo(""))
                .body("players.1",equalTo(""))
                .body("isGameOver",equalTo(false))
        ;
    }

    @Test
    public void testPlayWithWrongPitId(){
        given()
                .when().put("/games/"+gameId+"/pits/15")
                .then()
                .statusCode(Response.Status.BAD_REQUEST.getStatusCode())
                .assertThat()
                .body("error",equalTo("PitId is not in Range!"))
        ;
    }

    @Test
    public void testPlayWithWrongGameId(){
        given()
                .when().put("/games/-1/pits/1")
                .then()
                .statusCode(Response.Status.BAD_REQUEST.getStatusCode())
                .assertThat()
                .body("error",equalTo("Game Id not found!"))
        ;
    }

    @Test
    public void testGetGames() {
        given()
                .when().get("/games/list")
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .assertThat()
                .body("games.0",notNullValue());
        ;
    }
    @Test
    public void testGetPlayers() {
        given()
                .when().post("/games/"+gameId+"/players")
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .assertThat()
                .body("players",notNullValue());
        ;
    }
    @Test
    public void testGetPlayersWithWrongGameId(){
        given()
                .when().post("/games/-1/players")
                .then()
                .statusCode(Response.Status.BAD_REQUEST.getStatusCode())
                .assertThat()
                .body("error",equalTo("Game Id not found!"))
        ;
    }


}
