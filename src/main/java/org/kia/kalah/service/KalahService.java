package org.kia.kalah.service;

import org.kia.kalah.dto.BoardConstants;
import org.kia.kalah.dto.KalahBoard;
import org.kia.kalah.dto.PlayerInfo;
import org.kia.kalah.engine.KalahEngine;
import org.kia.kalah.exception.KalahEngineException;
import org.kia.kalah.exception.KalahServiceException;

import javax.enterprise.context.ApplicationScoped;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

/**
 * Game Service Manager in order to execute games for lots of different players
 */
@ApplicationScoped
public class KalahService {
    private static final Logger logger = Logger.getLogger(KalahService.class.getName());
    private Map<Integer, KalahBoard> boardsMap = new HashMap<>();
    private final KalahEngine kalahEngine = new KalahEngine();

    /**
     * Creating New Game!
     * @return Created Game Id
     */
    public Integer createNewGame(){
        KalahBoard kalahBoard = new KalahBoard();
        kalahBoard.initBoard();
        PlayerInfo players = new PlayerInfo();
        Random random = new Random();
        players.setGameId(random.nextInt(Integer.MAX_VALUE)); //Generate Not Negative random id
        players.setPlayerNumber(BoardConstants.FIRST_PLAYER.getValue());
        kalahBoard.setPlayers(players);
        boardsMap.put(players.getGameId(),kalahBoard);
        return players.getGameId();
    }

    /**
     * Assign player Name to the board
     * @param gameId
     * @param playerName
     * @return true if we could successfully assign player name otherwise false
     */
    public void addPlayer(Integer gameId,String playerName) throws KalahServiceException {
        PlayerInfo playerInfo = getBoardStatus(gameId).getPlayers();
        if(playerInfo.getPlayer1().isEmpty())
            playerInfo.setPlayer1(playerName);
        else if(playerInfo.getPlayer2().isEmpty())
            playerInfo.setPlayer2(playerName);
    }

    public KalahBoard play(Integer gameId,Integer selectedMove) throws KalahServiceException, KalahEngineException {
        KalahBoard kalahBoard = getBoardStatus(gameId);
        kalahBoard.getPlayers().setSelectedMove(selectedMove);
        kalahEngine.moveStones(kalahBoard);
        logger.fine(kalahBoard.toString());
        return kalahBoard;
    }

    public JsonObjectBuilder getGames(){
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
        AtomicInteger i = new AtomicInteger();
        boardsMap.entrySet().stream()
                .filter(e->e.getValue().getPlayers().isWaitingForPlayer())
                .map(Map.Entry::getKey)
                .sorted()
                .forEach(e->jsonObjectBuilder.add(Integer.toString(i.getAndIncrement()),e));
        return jsonObjectBuilder;
    }

    public KalahBoard getBoardStatus(Integer gameId) throws KalahServiceException {
        return  Optional.ofNullable(boardsMap.get(gameId)).orElseThrow(()->new KalahServiceException("Game Id not found!"));
    }

}
