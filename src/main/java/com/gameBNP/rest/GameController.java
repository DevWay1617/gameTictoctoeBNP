package com.gameBNP.rest;

import com.gameBNP.dto.RequestGamingDto;
import com.gameBNP.model.Game;
import com.gameBNP.model.Player;
import com.gameBNP.service.GameService;
import com.gameBNP.service.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("tic-tac-toe/api")
public class GameController {

    private GameService gameService;
    private PlayerService playerService;

    public GameController(GameService gameService, PlayerService playerService) {

        this.gameService = gameService;
        this.playerService = playerService;

    }

    /**
     * A player should fill up the RequestGamingDto with his symbol [X/O], marker
     * xPosition [1-2-3] and marker yPosition  [1-2-3]
     *
     */
    @RequestMapping(value = "/play", produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity<Game> updateGameState(@RequestBody RequestGamingDto requestGamingDto) {

        Player player = null;
        Game game = null;


        try {
            player = this.playerService.getPlayerByPiece(requestGamingDto.getPlayerSymbol());

        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }

        try {
            game = this.gameService.play(requestGamingDto.getPositionX(), requestGamingDto.getPositionY(), player);
        } catch (Exception e) {

            return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);

        }
        return new ResponseEntity<Game>(game, HttpStatus.OK);

    }


}