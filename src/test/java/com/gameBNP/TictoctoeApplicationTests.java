package com.gameBNP;

import com.gameBNP.enums.Piece;
import com.gameBNP.exceptions.FirstPlayerException;
import com.gameBNP.model.Game;
import com.gameBNP.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

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
        assertThrows(FirstPlayerException.class, () -> game.play(playerO));
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
}
