package com.gameBNP.model;

import lombok.Data;

@Data
public class Move {
    Player player;

    public Move(Player player) {
        this.player = player;
    }
}
