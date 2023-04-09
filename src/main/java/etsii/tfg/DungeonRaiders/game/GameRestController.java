package etsii.tfg.DungeonRaiders.game;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import etsii.tfg.DungeonRaiders.card.CardService;
import etsii.tfg.DungeonRaiders.card.CardState;

@RequestMapping("/game")
@RestController
public class GameRestController {
    private static final String TURN_GAME_URL = "/{gameId}/playing/turn";
    private static final String REVEALED_GAME_URL = "/{gameId}/playing/revealed";
    private static final String RELOAD_NEEDED_PLAYING_GAME_URL = "/{gameId}/playing/reloadNeeded";
    private static final String RELOAD_NEEDED_LOBBY_GAME_URL = "/{gameId}/lobby/reloadNeeded";
    private static final String GAME_AMOUNT_URL = "/list/amount";
    private GameService gameService;
    private CardService cardService;

    @Autowired
    public GameRestController(GameService gameService, CardService cardService) {
        this.gameService = gameService;
        this.cardService = cardService;
    }

    @GetMapping(TURN_GAME_URL)
    public Integer getGameTurn(@PathVariable("gameId") int gameId) {
        return gameService.findGameById(gameId).getTurn();
    }

    @GetMapping(REVEALED_GAME_URL)
    public Boolean getGameRevealedCards(@PathVariable("gameId") int gameId) {
        return cardService.findAllCardsPlayedThisTurn(gameId).stream()
                .anyMatch(c -> c.getCardState().equals(CardState.REVEALED));
    }

    @GetMapping(value = { "/{gameId}/playing/playerAmount",
            "/{gameId}/lobby/playerAmount" })
    public Integer getGamePlayerAmount(@PathVariable("gameId") int gameId) {
        return gameService.findGameById(gameId).getPlayers().size();
    }

    @GetMapping(value = RELOAD_NEEDED_PLAYING_GAME_URL)
    public Boolean getGameReloadNeededActive(@PathVariable("gameId") int gameId) {
        try {
            Game game = gameService.findGameById(gameId);
            return !game.isActive();
        } catch (NoSuchElementException | NullPointerException e) {
            return true;
        }

    }

    @GetMapping(value = RELOAD_NEEDED_LOBBY_GAME_URL)
    public Boolean getGameReloadNeededLobby(@PathVariable("gameId") int gameId) {
        try {
            Game game = gameService.findGameById(gameId);
            return game.isInGame();
        } catch (NoSuchElementException | NullPointerException e) {
            return true;
        }

    }

    @GetMapping(value = GAME_AMOUNT_URL)
    public Integer getGamesAmount() {
        return gameService.findAllInLobbyGames().size();
    }

}
