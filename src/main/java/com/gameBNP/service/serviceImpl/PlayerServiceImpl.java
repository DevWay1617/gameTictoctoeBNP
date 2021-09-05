package com.gameBNP.service.serviceImpl;

import com.gameBNP.enums.Piece;
import com.gameBNP.exceptions.PlayerNotFoundException;
import com.gameBNP.model.Player;
import com.gameBNP.service.PlayerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerServiceImpl  implements PlayerService {

    private final List<Player> players;
    /**
     * Two player will be initialized from the beginning
     *
     */
    public PlayerServiceImpl() {
        players = new ArrayList<>(2);
        players.add(new Player("FIRST_PLAYER", Piece.PLAYER_X));
        players.add(new Player("SECOND_PLAYER", Piece.PLAYER_O));
    }
    /**
     * @param piece
     * @return Player
     */
    @Override
    public Player getPlayerByPiece(String piece) {

        Optional<Player> player = players.stream().filter(e -> e.getPiece().getPieceSymbol().equals(piece))
                .findFirst();

        if (player.isPresent()) {

            return player.get();

        }
        throw new PlayerNotFoundException("Player with Symbol "+piece+" is not found");

    }
}
