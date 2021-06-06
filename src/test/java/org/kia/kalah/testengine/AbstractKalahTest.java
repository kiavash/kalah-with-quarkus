package org.kia.kalah.testengine;

import org.junit.jupiter.api.BeforeEach;
import org.kia.kalah.dto.BoardConstants;
import org.kia.kalah.dto.KalahBoard;
import org.kia.kalah.dto.PlayerInfo;


public class AbstractKalahTest {

    protected KalahBoard kalahBoard;

    @BeforeEach
    public void beforeTest(){
        kalahBoard = new KalahBoard();
        kalahBoard.initBoard();
        PlayerInfo players = new PlayerInfo();
        players.setGameId(1);
        players.setPlayer1("Alice");
        players.setPlayer2("Kia");
        players.setPlayerNumber(BoardConstants.FIRST_PLAYER.getValue());
        kalahBoard.setPlayers(players);
    }

    protected void initBoardForTest(KalahBoard kalahBoard, Integer[] stones){
        kalahBoard.getPits()[0].setStones(stones[0]); //Kalah1 for Second Player
        kalahBoard.getPits()[1].setStones(stones[1]);
        kalahBoard.getPits()[2].setStones(stones[2]);
        kalahBoard.getPits()[3].setStones(stones[3]);
        kalahBoard.getPits()[4].setStones(stones[4]);
        kalahBoard.getPits()[5].setStones(stones[5]);
        kalahBoard.getPits()[6].setStones(stones[6]);
        kalahBoard.getPits()[7].setStones(stones[7]); //Kalah2 for First Player
        kalahBoard.getPits()[8].setStones(stones[8]);
        kalahBoard.getPits()[9].setStones(stones[9]);
        kalahBoard.getPits()[10].setStones(stones[10]);
        kalahBoard.getPits()[11].setStones(stones[11]);
        kalahBoard.getPits()[12].setStones(stones[12]);
        kalahBoard.getPits()[13].setStones(stones[13]);
    }
}
