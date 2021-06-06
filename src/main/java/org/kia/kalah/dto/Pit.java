package org.kia.kalah.dto;

/**
 * this class is created as a pit to keep the stones
 */
public class Pit {
    private Integer stones = 0;

    public Integer getStones() {
        return stones;
    }

    public void setStones(Integer stones) {
        this.stones = stones;
    }

    public void addStones(int stones)
    {
        this.stones += stones;
    }
    public int removeStones() {
        int stones = this.stones;
        this.stones = 0;
        return stones;
    }

    @Override
    public String toString() {
        return "Pit{" +
                "stones=" + stones +
                '}';
    }
}
