package com.gameBNP.service.serviceImpl;

import com.gameBNP.model.Game;
import com.gameBNP.model.Player;
import com.gameBNP.service.GameService;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {
    private Game game;
    @Override
    public Game play(int positionX, int PositionY, Player player) {
        this.game.play(positionX, PositionY, player);
        return this.game;
    }
}
