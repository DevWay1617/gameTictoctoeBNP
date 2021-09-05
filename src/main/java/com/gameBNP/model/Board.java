package com.gameBNP.model;

import com.gameBNP.utils.Utilities;
import lombok.Data;

import java.io.Serializable;

@Data
public class Board implements Serializable {

    private MarkerBoard[][] markerBoards;

    public Board() {

        markerBoards = new MarkerBoard[Utilities.NUMBER_COLUMNS][Utilities.NUMBER_ROWS];

        for (int rowIndex = 0; rowIndex < Utilities.NUMBER_ROWS; rowIndex++) {
            for (int columnIndex = 0; columnIndex < Utilities.NUMBER_COLUMNS; columnIndex++) {
                markerBoards[rowIndex][columnIndex] = new MarkerBoard(new Position(rowIndex, columnIndex));
            }
        }
    }

}
