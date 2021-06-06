package org.kia.kalah.testengine;

import org.kia.kalah.dto.KalahBoard;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KalahBoardAssert {

    public static void assertPits(KalahBoard kalahBoard, Integer[] actualPits){
        assertEquals(kalahBoard.getPits()[0].getStones(),actualPits[0]); //Kalah1 for Second Player
        assertEquals(kalahBoard.getPits()[1].getStones(),actualPits[1]);
        assertEquals(kalahBoard.getPits()[2].getStones(),actualPits[2]);
        assertEquals(kalahBoard.getPits()[3].getStones(),actualPits[3]);
        assertEquals(kalahBoard.getPits()[4].getStones(),actualPits[4]);
        assertEquals(kalahBoard.getPits()[5].getStones(),actualPits[5]);
        assertEquals(kalahBoard.getPits()[6].getStones(),actualPits[6]);
        assertEquals(kalahBoard.getPits()[7].getStones(),actualPits[7]); //Kalah2 for First Player
        assertEquals(kalahBoard.getPits()[8].getStones(),actualPits[8]);
        assertEquals(kalahBoard.getPits()[9].getStones(),actualPits[9]);
        assertEquals(kalahBoard.getPits()[10].getStones(),actualPits[10]);
        assertEquals(kalahBoard.getPits()[11].getStones(),actualPits[11]);
        assertEquals(kalahBoard.getPits()[12].getStones(),actualPits[12]);
        assertEquals(kalahBoard.getPits()[13].getStones(),actualPits[13]);
    }

    public static void assertSelectedPlayer(KalahBoard board,Integer actualPlayerNumebr){
        assertEquals(board.getPlayers().getPlayerNumber(),actualPlayerNumebr);
    }
    public static void assertGameOver(KalahBoard board,boolean isGameOver){
        assertEquals(board.isGameOver(),isGameOver);
    }
}
