package org.kia.kalah.dto;

import java.util.Arrays;

/**
 * This class is representing main game board
 */
public class KalahBoard {
    private Pit[] pits = new Pit[BoardConstants.TOTAL_PLAYING_PITs.getValue()];
    private PlayerInfo players;
    private boolean isGameOver = false;


    public KalahBoard() {
        for (int i=0;i< pits.length;i++)
            pits[i] = new Pit();
    }

    /**
     * Initial the board with 4 stones in each pit
     */
    public void initBoard(){
        for (int i=0;i< pits.length;i++) {
            if(i != BoardConstants.KALAH1.getValue() && i!= BoardConstants.KALAH2.getValue())
                pits[i].setStones(BoardConstants.STARTING_STONES.getValue());
        }
    }

    public void setPlayers(PlayerInfo players) {
        this.players = players;
    }

    public PlayerInfo getPlayers() {
        return players;
    }

    public Pit[] getPits() {
        return pits;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }

    @Override
    public String toString() {
        return "KalahBoard{" +
                "pits=" + Arrays.deepToString(pits) + "\nplayers=" + players.toString()+
                '}';
    }
}
