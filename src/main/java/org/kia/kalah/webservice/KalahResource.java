package org.kia.kalah.webservice;

import org.kia.kalah.dto.BoardConstants;
import org.kia.kalah.dto.KalahBoard;
import org.kia.kalah.dto.Pit;
import org.kia.kalah.exception.KalahEngineException;
import org.kia.kalah.exception.KalahServiceException;
import org.kia.kalah.service.KalahService;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.logging.Logger;

@Path("/")
public class KalahResource {
    private static final Logger logger = Logger.getLogger(KalahResource.class.getName());


    @Inject
    KalahService kalahService;

    @POST
    @Path("/games")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNew(@Context UriInfo uriInfo) {
        Integer gameId = kalahService.createNewGame();
        JsonObject response = Json.createObjectBuilder()
                .add("id", gameId)
                .add("url", uriInfo.getBaseUri() + "games/" + gameId)
                .build();
        return Response.created(uriInfo.getBaseUri()).entity(response).build();
    }


    @PUT
    @Path("/games/add/{gameId}/player/{playerName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPlayer(
            @PathParam("gameId") Integer gameId,
            @PathParam("playerName") String playerName
    ) {
        try {
            kalahService.addPlayer(gameId,playerName);
            KalahBoard board = kalahService.getBoardStatus(gameId);
            JsonObjectBuilder jsonObjectBuilder = getPlayers(board);
            JsonObject response = Json.createObjectBuilder()
                    .add("players", jsonObjectBuilder)
                    .add("playerNumber", board.getPlayers().getPlayerNumber())
                    .build();
            return Response.ok(response).build();
        } catch (KalahServiceException e) {
            return handleBadRequest(e);
        }
    }

    @PUT
    @Path("/games/{gameId}/pits/{pitId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response play(
            @Context UriInfo uriInfo,
            @PathParam("gameId") Integer gameId,
            @PathParam("pitId") Integer selectedMove
    ) {
        try {
            KalahBoard kalahBoard = kalahService.play(gameId,selectedMove);
            return Response.ok(getBoardStatusJsonObject(kalahBoard,uriInfo)).build();
        } catch (KalahServiceException | KalahEngineException exception) {
            return handleBadRequest(exception);
        }
    }

    @GET
    @Path("/games/{gameId}/boardStatus")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBoardStatus(
            @Context UriInfo uriInfo,
            @PathParam("gameId") Integer gameId
    ) {
        try {
            KalahBoard board = kalahService.getBoardStatus(gameId);
            return Response.ok(getBoardStatusJsonObject(board,uriInfo)).build();
        } catch (KalahServiceException exception) {
            return handleBadRequest(exception);
        }
    }

    @GET
    @Path("/games/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGames() {
        JsonObjectBuilder jsonObjectBuilder = kalahService.getGames();
        JsonObject response = Json.createObjectBuilder()
                .add("games", jsonObjectBuilder)
                .build();
        return Response.ok(response).build();
    }

    @POST
    @Path("/games/{gameId}/players")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlayers(
            @PathParam("gameId") Integer gameId
    ) {
        try {
            KalahBoard board = kalahService.getBoardStatus(gameId);
            JsonObjectBuilder jsonObjectBuilder = getPlayers(board);
            JsonObject response = Json.createObjectBuilder()
                    .add("players", jsonObjectBuilder)
                    .build();
            return Response.ok(response).build();
        } catch (KalahServiceException exception) {
            return handleBadRequest(exception);
        }
    }


    private JsonObject getBoardStatusJsonObject(KalahBoard board,UriInfo uriInfo){
        JsonObjectBuilder jsonObjectBoard = boardToJson(board);
        JsonObjectBuilder jsonObjectResponse = Json.createObjectBuilder();
        if(board!=null){
            jsonObjectResponse
                    .add("id", board.getPlayers().getGameId())
                    .add("uri", uriInfo.getBaseUri() + "games/" + board.getPlayers().getGameId())
                    .add("status", jsonObjectBoard)
                    .add("playerNumber", board.getPlayers().getPlayerNumber())
                    .add("players", getPlayers(board))
                    .add("isGameOver", board.isGameOver());
        }
        return jsonObjectResponse.build();
    }

    private JsonObjectBuilder boardToJson(KalahBoard kalahBoard) {
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
        if(kalahBoard != null){
            Pit[] pits = kalahBoard.getPits();
            for (int i = 1; i<BoardConstants.TOTAL_PLAYING_PITs.getValue(); i++) {
                jsonObjectBuilder.add(Integer.toString(i),pits[i].getStones());
            }
            /*
             * Just Consider pit 0 as 14 in output!!
             */
            jsonObjectBuilder.add(Integer.toString(BoardConstants.TOTAL_PLAYING_PITs.getValue()),pits[0].getStones());
        }
        return jsonObjectBuilder;
    }

    private JsonObjectBuilder getPlayers(KalahBoard board){
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
        if(board != null){
            jsonObjectBuilder.add("1",board.getPlayers().getPlayer2());
            jsonObjectBuilder.add("0",board.getPlayers().getPlayer1());
        }
        return jsonObjectBuilder;
    }

    private Response handleBadRequest(Exception exception){
        logger.severe(exception.getMessage());
        JsonObject jsonObject = Json.createObjectBuilder()
                .add("error", exception.getMessage())
                .build();
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(jsonObject)
                .build();
    }
}
