package com.gameBNP.model;

import com.gameBNP.enums.Piece;
import lombok.Data;


@Data
public class Player  implements Comparable<Player>{
    private final String name;
    private final Piece piece;

    public Player(String name, Piece piece) {
        this.name = name;
        this.piece = piece;
    }

    @Override
    public int compareTo(Player player) {
        return name.compareTo(player.getName());
    }
}
