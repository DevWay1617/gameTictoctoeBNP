package com.gameBNP.model;

import com.gameBNP.enums.Piece;
import com.gameBNP.exceptions.FirstPlayerException;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class Game implements Serializable {

    private Board board;
    // list of players ordered
    List<Move> movements = new ArrayList<>();

    public Game() {
        initialize();
    }

    public void initialize() {
        if (board == null) {
            board = new Board();
        }

    }

    public void play(Player player) {

        checkFirstPlayer(player);
    }

    /*
    Check if the PlayerX is always the first one
     */
    public void checkFirstPlayer(Player player) {

        if (movements.isEmpty() && !player.getPiece().equals(Piece.PLAYER_X)) {
            throw new FirstPlayerException();
        }

    }

}
