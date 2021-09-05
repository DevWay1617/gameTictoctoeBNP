package com.gameBNP.service;

import com.gameBNP.model.Game;
import com.gameBNP.model.Player;

public interface GameService {
    Game play(int positionX, int PositionY, Player player);
}
