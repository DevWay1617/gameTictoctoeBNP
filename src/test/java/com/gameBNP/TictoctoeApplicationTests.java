package com.gameBNP;

import com.gameBNP.enums.Piece;
import com.gameBNP.exceptions.FirstPlayerException;
import com.gameBNP.exceptions.NextPlayerIsTheSameOneException;
import com.gameBNP.exceptions.PositionAlreadyOccupied;
import com.gameBNP.exceptions.PositionOutsideBoardException;
import com.gameBNP.model.Game;
import com.gameBNP.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class TictoctoeApplicationTests {

    @Test
    void contextLoads() {
    }

    private Game game;
    private Player playerX = new Player("Alain", Piece.PLAYER_X);
    private Player playerO = new Player("JeanPaul", Piece.PLAYER_O);

    @BeforeEach
    void init() {
        game = new Game();

    }

    //
    @Test
    void whenStartGameThePlayerXShouldStartFirstTest() {
        // game is empty
        assertThrows(FirstPlayerException.class, () -> game.play(2, 3, playerO));
    }

    //test Players alternate placing X’s and O’s on the board
    @Test
    void whenNextPlayerIsTheSamethanThePreviousOneThenThrowsNextPlayerIsTheSameOneException() {
        game.play(1, 1, playerX); // current Player now is playerX
        assertThrows(NextPlayerIsTheSameOneException.class, () -> game.play(2, 2, playerX));

    }

    //Players cannot play on a played position.
    @Test
    void whenMarkerBoundWasOcuppiedThenThrowsPositionAlreadyOccupiedException() {
        game.play(3, 3, playerX);
        assertThrows(PositionAlreadyOccupied.class, () -> game.play(3, 3, playerO));

    }

    /* Players alternate placing X’s and O’s on the board until either:
      One player has three in a row, All nine squares are filled and.
      This test with 5 rows and columns and 25 squares*/
    @Test
    void whenMarkerBoundWasOutsideOfBoundThenThrowsMarkerOutsideBoardException() {
        assertThrows(PositionOutsideBoardException.class, () -> game.play(5, 5, playerX));
    }

    /* scenario 1:the Payer X win despite the 9 positions are not yet filled
        X O O
        O X .
        X O X
    */
    @Test
    void whenBoardIsFullwithWinnerThenGameisOver() {
        game.play(1, 1, playerX); // the Player X is always the First
        game.play(1, 2, playerO);
        game.play(1, 3, playerO);
        game.play(2, 1, playerO);
        game.play(2, 2, playerX);
        game.play(3, 1, playerX);
        game.play(3, 2, playerO);
        game.play(3, 3, playerX);
        assertEquals(game.getGameStatus(), GameStatus.Win);

    }

    /* scenario 1: the Payer O win and all  positions are  filled
        X O O
        O X O
        X O O
    */
    @Test
    void whenBoardIsFullwithWinnerThenGameisOver() {
        game.play(1, 1, playerX); // the Player X is always the First
        game.play(1, 2, playerO);
        game.play(1, 3, playerO);
        game.play(2, 1, playerO);
        game.play(2, 2, playerX);
        game.play(3, 1, playerX);
        game.play(3, 2, playerO);
        game.play(3, 3, playerX);
        assertEquals(game.getGameStatus(), GameStatus.Win);//

    }

    /* scenario 2:Draw
       O X X
       X O O
       X O X
    */
    @Test
    void whenBoardIsFullwithoutWinnerThenGameisDraw() {
        game.play(1, 2, playerX); // the Player X is always the First
        game.play(1, 1, playerO);
        game.play(1, 3, playerX);
        game.play(2, 2, playerO);
        game.play(2, 1, playerX);
        game.play(2, 3, playerO);
        game.play(3, 1, playerX);
        game.play(3, 2, playerO);
        game.play(3, 3, playerX);
        assertEquals(game.getGameStatus(), GameStatus.DRAW); //All the bord is Fully , there are no winner => Draw

    }

    /* scenario 3 :Over
        X O O
        X . .
        X . .
    */
    @Test
    void whenOneOfPlayerFillUpThreeVerticalSuccessiveMarkersThenGameisOver() {
        game.play(1, 1, playerX); // the Player X is always the First
        game.play(1, 2, playerO);
        game.play(2, 1, playerX);
        game.play(1, 3, playerO);
        game.play(3, 1, playerX);
        assertEquals(game.getGameStatus(), GameStatus.OVER);
    }


    /* scenario 4 :Over
        X O O
        X . .
        X . .
    */
    @Test
    void whenOneOfPlayerFillUpThreeDiagonalyRightSuccessiveMarkersThenGameisOver() {
        game.play(1, 3, playerX); // the Player X is always the First
        game.play(2, 1, playerO);
        game.play(2, 2, playerX);
        game.play(3, 2, playerO);
        game.play(3, 1, playerX);
        assertEquals(game.getGameStatus(), GameStatus.OVER);

    }

}
