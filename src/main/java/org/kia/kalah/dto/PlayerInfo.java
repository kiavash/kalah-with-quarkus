package org.kia.kalah.dto;

import java.util.Objects;

/**
 * This class keeps the player and game info
 */
public class PlayerInfo {
    private String player1="";
    private String player2="";
    private Integer gameId;
    private Integer playerNumber;
    private Integer selectedMove;

    public String getPlayer1() {
        return player1;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public Integer getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(Integer playerNumber) {
        this.playerNumber = playerNumber;
    }

    public Integer getSelectedMove() {
        return selectedMove;
    }

    public void setSelectedMove(Integer selectedMove) {
        this.selectedMove = selectedMove;
    }

    public void changePlayer(){
        playerNumber = (playerNumber==BoardConstants.FIRST_PLAYER.getValue()) ? BoardConstants.SECOND_PLAYER.getValue() : BoardConstants.FIRST_PLAYER.getValue();
    }

    public boolean isWaitingForPlayer(){
        return player1.isEmpty() || player2.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerInfo that = (PlayerInfo) o;
        return Objects.equals(player1, that.player1) && Objects.equals(player2, that.player2) && Objects.equals(gameId, that.gameId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player1, player2, gameId);
    }

    @Override
    public String toString() {
        return "PlayerInfo{" +
                "player1='" + player1 + '\'' +
                ", player2='" + player2 + '\'' +
                ", gameId=" + gameId +
                ", playerNumber=" + playerNumber +
                ", selectedMove=" + selectedMove +
                '}';
    }
}
