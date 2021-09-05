package com.gameBNP.enums;

public enum GameStatus {
    IN_PROGRESS("IN POGRESS"),
    OVER_WITH_WINNER("OVER WITH WINNER"), // game over with winner
    DRAW("DRAW"); // game over without winner

    private String value;

    private GameStatus(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
