package etsii.tfg.DungeonRaiders.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/game")
@RestController
public class GameRestController {
    private static final String TURN_GAME_URL = "/{gameId}/playing/turn";
    private GameService gameService;

    @Autowired
    public GameRestController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping(TURN_GAME_URL)
    public Integer getGameTurn(@PathVariable("gameId") int gameId) {
        return gameService.findGameById(gameId).getTurn();
    }

}
