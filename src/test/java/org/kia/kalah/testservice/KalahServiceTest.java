package org.kia.kalah.testservice;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kia.kalah.dto.BoardConstants;
import org.kia.kalah.dto.KalahBoard;
import org.kia.kalah.dto.PlayerInfo;
import org.kia.kalah.exception.KalahEngineException;
import org.kia.kalah.exception.KalahServiceException;
import org.kia.kalah.service.KalahService;

import javax.inject.Inject;
import javax.json.JsonObjectBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class KalahServiceTest {
    @Inject
    KalahService kalahService;

    private Integer gameId = null;

    @BeforeEach
    public void beforeEach() {
        if(gameId == null)
            gameId = kalahService.createNewGame();
    }

    @Test
    public void testCreateNewGame(){
        Integer id = kalahService.createNewGame();
        assertNotNull(id);
    }

    @Test
    public void testAddPlayer() throws KalahServiceException {
        kalahService.addPlayer(gameId,"Kiavash");
        kalahService.addPlayer(gameId,"Alice");
        assertEquals(kalahService.getBoardStatus(gameId).getPlayers().getPlayer1(),"Kiavash");
        assertEquals(kalahService.getBoardStatus(gameId).getPlayers().getPlayer2(),"Alice");
    }
    @Test
    public void testPlay() throws KalahServiceException, KalahEngineException {
        kalahService.play(gameId,1);
        KalahBoard board = kalahService.getBoardStatus(gameId);
        assertEquals(board.getPits()[0].getStones(),0);
        assertEquals(board.getPits()[1].getStones(),0);
        assertEquals(board.getPits()[2].getStones(),7);
        assertEquals(board.getPits()[3].getStones(),7);
        assertEquals(board.getPits()[4].getStones(),7);
        assertEquals(board.getPits()[5].getStones(),7);
        assertEquals(board.getPits()[6].getStones(),7);
        assertEquals(board.getPits()[7].getStones(),1);
        assertEquals(board.getPits()[8].getStones(),6);
        assertEquals(board.getPits()[9].getStones(),6);
        assertEquals(board.getPits()[10].getStones(),6);
        assertEquals(board.getPits()[11].getStones(),6);
        assertEquals(board.getPits()[12].getStones(),6);
        assertEquals(board.getPits()[13].getStones(),6);
        assertEquals(board.isGameOver(),false);
        assertEquals(board.getPlayers().getPlayerNumber(), BoardConstants.FIRST_PLAYER.getValue());
    }

    @Test
    public void testGetGames(){
        JsonObjectBuilder jsonObjectBuilder = kalahService.getGames();
        assertNotNull(jsonObjectBuilder);
    }

}
