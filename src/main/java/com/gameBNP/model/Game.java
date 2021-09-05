package com.gameBNP.model;

import com.gameBNP.enums.GameStatus;
import com.gameBNP.enums.MarkerValue;
import com.gameBNP.enums.Piece;
import com.gameBNP.exceptions.FirstPlayerException;
import com.gameBNP.exceptions.NextPlayerIsTheSameOneException;
import com.gameBNP.exceptions.PositionAlreadyOccupied;
import com.gameBNP.exceptions.PositionOutsideBoardException;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

@Data
public class Game implements Serializable {

    private Board board;
    // list of players ordered
    List<Move> movements = new ArrayList<>();
    private GameStatus gameStatus = GameStatus.IN_PROGRESS;
    Player winnerPlayer = null;

    public Game() {
        initialize();
    }

    public void initialize() {
        if (board == null) {
            board = new Board();
        }

    }

    public void play(int positionX, int positionY, Player player) {

        checkFirstPlayer(player);
        checkNextPlayer(player);
        checkMarkerBoardPosition(positionX, positionY);
        fillUpBoard(positionX, positionY, player);

    }

    /*
    Check if the PlayerX is always the first one
     */
    public void checkFirstPlayer(Player player) {

        if (movements.isEmpty() && !player.getPiece().equals(Piece.PLAYER_X)) {
            throw new FirstPlayerException();
        }

    }

    // Alternative Players: Compare current player with last player in movement list
    public void checkNextPlayer(Player player) {

        if (!movements.isEmpty()
                && movements.get(movements.size() - 1).getPlayer().getPiece().equals(player.getPiece())) {

            if (player.getPiece().equals(Piece.PLAYER_X)) {
                throw new NextPlayerIsTheSameOneException(
                        "Next player shouled be O");
            } else {
                throw new NextPlayerIsTheSameOneException(
                        "Next player shouled be X");
            }
        }
    }

    /**
     * Check if the markerBoard positions does not exceed 9 squares
     *
     * @param positionX
     * @param positionY
     */
    public void checkMarkerBoardPosition(int positionX, int positionY) {

        if (positionX < 1 || positionX > 3 || positionY < 1 || positionY > 3) {
            throw new PositionOutsideBoardException("Marker " + positionX + "-" + positionY + " outside the bound");
        }
    }

    /**
     * check if the marker position in the borad is not occupied
     *
     * @param positionX
     * @param positionY
     */
    private void fillUpBoard(int positionX, int positionY, Player player) {

        MarkerBoard[][] marker = board.getMarkerBoards();

        if (!marker[positionX - 1][positionY - 1].isEmpty()) {

            throw new PositionAlreadyOccupied("Position " + positionX + "-" + positionY + " was occupied");
        }
        board.getMarkerBoards()[positionX - 1][positionY - 1]
                .setValue(MarkerValue.valueOf(player.getPiece().getPieceSymbol()));

        movements.add(new Move(player));

    }

    public GameStatus checkGameStatus(Player player) {

        String playerSymbol = player.getPiece().getPieceSymbol();
        MarkerBoard[][] marker = board.getMarkerBoards();

        // Winners Positions
        //First Horizontal line win
        if (marker[0][0].getValue().getText().equals(playerSymbol)
         && marker[0][1].getValue().getText().equals(playerSymbol)
         && marker[0][2].getValue().getText().equals(playerSymbol)) {

            winnerPlayer = player;
            gameStatus = GameStatus.OVER_WITH_WINNER;
            return GameStatus.OVER_WITH_WINNER;
            //Middle Horizontal line Win
        } else if (marker[1][0].getValue().getText().equals(playerSymbol)
                && marker[1][1].getValue().getText().equals(playerSymbol)
                && marker[1][2].getValue().getText().equals(playerSymbol)) {

            winnerPlayer = player;
            gameStatus = GameStatus.OVER_WITH_WINNER;
            return GameStatus.OVER_WITH_WINNER;
            //Last Horizontal line Win
        } else if (marker[2][0].getValue().getText().equals(playerSymbol)
                && marker[2][1].getValue().getText().equals(playerSymbol)
                && marker[2][2].getValue().getText().equals(playerSymbol)) {

            winnerPlayer = player;
            gameStatus = GameStatus.OVER_WITH_WINNER;
            return GameStatus.OVER_WITH_WINNER;
            //First Vertical line Win
        } else if (marker[0][0].getValue().getText().equals(playerSymbol)
                && marker[1][0].getValue().getText().equals(playerSymbol)
                && marker[2][0].getValue().getText().equals(playerSymbol)) {

            winnerPlayer = player;
            gameStatus = GameStatus.OVER_WITH_WINNER;
            return GameStatus.OVER_WITH_WINNER;
            //Middle Vertical line Win
        } else if (marker[0][1].getValue().getText().equals(playerSymbol)
                && marker[1][1].getValue().getText().equals(playerSymbol)
                && marker[2][1].getValue().getText().equals(playerSymbol)) {

            winnerPlayer = player;
            gameStatus = GameStatus.OVER_WITH_WINNER;
            return GameStatus.OVER_WITH_WINNER;
            //Last Vertical line win
        } else if (marker[0][2].getValue().getText().equals(playerSymbol)
                && marker[1][2].getValue().getText().equals(playerSymbol)
                && marker[2][2].getValue().getText().equals(playerSymbol)) {

            winnerPlayer = player;
            gameStatus = GameStatus.OVER_WITH_WINNER;
            return GameStatus.OVER_WITH_WINNER;
            //Diagonal from left line Win
        } else if (marker[0][0].getValue().getText().equals(playerSymbol)
                && marker[1][1].getValue().getText().equals(playerSymbol)
                && marker[2][2].getValue().getText().equals(playerSymbol)) {

            winnerPlayer = player;
            gameStatus = GameStatus.OVER_WITH_WINNER;
            return GameStatus.OVER_WITH_WINNER;
            //Diagonal from right line Win
        } else if (marker[0][2].getValue().getText().equals(playerSymbol)
                && marker[1][1].getValue().getText().equals(playerSymbol)
                && marker[2][0].getValue().getText().equals(playerSymbol)) {

            winnerPlayer = player;
            gameStatus = GameStatus.OVER_WITH_WINNER;
            return GameStatus.OVER_WITH_WINNER;

        }

        if (movements.size() == 9 && winnerPlayer == null) {

            gameStatus = GameStatus.DRAW;
            return GameStatus.DRAW;

        }

        return GameStatus.IN_PROGRESS;

    }
}
