package org.kia.kalah.testengine;

import org.junit.jupiter.api.Test;
import org.kia.kalah.dto.BoardConstants;
import org.kia.kalah.engine.KalahEngine;
import org.kia.kalah.exception.KalahEngineException;


public class KalahEngineTest extends AbstractKalahTest{
    @Test
    public void testFirstPlayFromPitNumberOne() throws KalahEngineException {
        KalahEngine kalahEngine = new KalahEngine();
        kalahBoard.getPlayers().setSelectedMove(1);
        kalahEngine.moveStones(kalahBoard);
        Integer[] actualPits = {0,0,7,7,7,7,7,1,6,6,6,6,6,6};
        KalahBoardAssert.assertPits(kalahBoard,actualPits);
        KalahBoardAssert.assertSelectedPlayer(kalahBoard, BoardConstants.FIRST_PLAYER.getValue());
    }
    @Test
    public void testFirstPlayFromPitNumberThree() throws KalahEngineException {
        KalahEngine kalahEngine = new KalahEngine();
        kalahBoard.getPlayers().setSelectedMove(3);
        kalahEngine.moveStones(kalahBoard);
        Integer[] actualPits = {0,6,6,0,7,7,7,1,7,7,6,6,6,6};
        KalahBoardAssert.assertPits(kalahBoard,actualPits);
        KalahBoardAssert.assertSelectedPlayer(kalahBoard, BoardConstants.SECOND_PLAYER.getValue());
    }
    @Test
    public void testFirstPlayFromPitNumberSix() throws KalahEngineException {
        KalahEngine kalahEngine = new KalahEngine();
        kalahBoard.getPlayers().setSelectedMove(6);
        kalahEngine.moveStones(kalahBoard);
            Integer[] actualPits = {0,6,6,6,6,6,0,1,7,7,7,7,7,6};
        KalahBoardAssert.assertPits(kalahBoard,actualPits);
        KalahBoardAssert.assertSelectedPlayer(kalahBoard, BoardConstants.SECOND_PLAYER.getValue());
    }
    /**
     *                      06  05  04  03  02  01
     * Player 1 Kalah    07                        14  Player 2 Kalah
     *                      08  09  10  11  12  13
     */

    @Test
    public void testPlayRemindsOneStoneInPitForFirstPlayer() throws KalahEngineException {
        KalahEngine kalahEngine = new KalahEngine();
        Integer[] stones =     {0,4,4,4,4,0,0,5,5,5,5,4,4,4};
        initBoardForTest(kalahBoard,stones);
        kalahBoard.getPlayers().setPlayerNumber(BoardConstants.FIRST_PLAYER.getValue());
        kalahBoard.getPlayers().setSelectedMove(1);
        kalahEngine.moveStones(kalahBoard);
        Integer[] actualPits = {0,0,5,5,5,0,0,11,5,0,5,4,4,4};
        KalahBoardAssert.assertPits(kalahBoard,actualPits);
        KalahBoardAssert.assertSelectedPlayer(kalahBoard, BoardConstants.SECOND_PLAYER.getValue());
    }

    /**
     *                      06  05  04  03  02  01
     * Player 1 Kalah    07                        14  Player 2 Kalah
     *                      08  09  10  11  12  13
     */
    @Test
    public void testPlayRemindsOneStoneInPitForSecondPlayer() throws KalahEngineException {
        KalahEngine kalahEngine = new KalahEngine();
        Integer[] stones =     {0,4,4,4,4,4,4,0,4,4,4,4,0,0};
        initBoardForTest(kalahBoard,stones);
        kalahBoard.getPlayers().setSelectedMove(1);
        kalahBoard.getPlayers().setPlayerNumber(BoardConstants.SECOND_PLAYER.getValue());
        kalahEngine.moveStones(kalahBoard);
        Integer[] actualPits = {5,4,0,4,4,4,4,0,0,5,5,5,0,0};
        KalahBoardAssert.assertPits(kalahBoard,actualPits);
        KalahBoardAssert.assertSelectedPlayer(kalahBoard, BoardConstants.FIRST_PLAYER.getValue());
    }

    @Test
    public void testSecondPlayerFromPitNumberOne() throws KalahEngineException {
        KalahEngine kalahEngine = new KalahEngine();
        kalahBoard.getPlayers().setSelectedMove(1);
        kalahBoard.getPlayers().setPlayerNumber(BoardConstants.SECOND_PLAYER.getValue());
        kalahEngine.moveStones(kalahBoard);
        Integer[] actualPits = {1,6,6,6,6,6,6,0,0,7,7,7,7,7};
        KalahBoardAssert.assertPits(kalahBoard,actualPits);
        KalahBoardAssert.assertSelectedPlayer(kalahBoard, BoardConstants.SECOND_PLAYER.getValue());
    }

    @Test
    public void testSecondPlayerFromPitNumberThree() throws KalahEngineException {
        KalahEngine kalahEngine = new KalahEngine();
        kalahBoard.getPlayers().setSelectedMove(3);
        kalahBoard.getPlayers().setPlayerNumber(BoardConstants.SECOND_PLAYER.getValue());
        kalahEngine.moveStones(kalahBoard);
        Integer[] actualPits = {1,7,7,6,6,6,6,0,6,6,0,7,7,7};
        KalahBoardAssert.assertPits(kalahBoard,actualPits);
        KalahBoardAssert.assertSelectedPlayer(kalahBoard, BoardConstants.FIRST_PLAYER.getValue());
    }

    /**
     *                      06  05  04  03  02  01
     * Player 1 Kalah    07                        14  Player 2 Kalah
     *                      08  09  10  11  12  13
     */
    @Test
    public void testPlayRemindsOneStoneInPitForFirstPlayerAndPassOthersKalah() throws KalahEngineException {
        KalahEngine kalahEngine = new KalahEngine();
        Integer[] stones =     {5,0,1,0,2,9,8,11,2,7,3,0,0,0};
        initBoardForTest(kalahBoard,stones);
        kalahBoard.getPlayers().setSelectedMove(5);
        kalahBoard.getPlayers().setPlayerNumber(BoardConstants.FIRST_PLAYER.getValue());
        kalahEngine.moveStones(kalahBoard);
        Integer[] actualPits = {5,0,1,0,2,0,9,14,3,8,4,1,1,0};
        KalahBoardAssert.assertPits(kalahBoard,actualPits);
        KalahBoardAssert.assertSelectedPlayer(kalahBoard, BoardConstants.SECOND_PLAYER.getValue());
    }
    /**
     *                      06  05  04  03  02  01
     * Player 1 Kalah    07                        14  Player 2 Kalah
     *                      08  09  10  11  12  13
     */
    @Test
    public void testGameOver() throws KalahEngineException {
        KalahEngine kalahEngine = new KalahEngine();
        Integer[] stones =     {4,1,4,4,9,8,0,10,0,0,0,0,0,8};
        initBoardForTest(kalahBoard,stones);
        kalahBoard.getPlayers().setSelectedMove(6);
        kalahBoard.getPlayers().setPlayerNumber(BoardConstants.SECOND_PLAYER.getValue());
        kalahEngine.moveStones(kalahBoard);
        Integer[] actualPits = {7,0,0,0,0,0,0,41,0,0,0,0,0,0};
        KalahBoardAssert.assertPits(kalahBoard,actualPits);
        KalahBoardAssert.assertSelectedPlayer(kalahBoard, BoardConstants.FIRST_PLAYER.getValue());
        KalahBoardAssert.assertGameOver(kalahBoard,true);
    }


}
