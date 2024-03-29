package etsii.tfg.DungeonRaiders.game;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import etsii.tfg.DungeonRaiders.player.BotTypeEnum;
import etsii.tfg.DungeonRaiders.player.Player;
import etsii.tfg.DungeonRaiders.player.PlayerService;
import etsii.tfg.DungeonRaiders.validation.BasicInfo;

@RequestMapping("/game")
@Controller
public class LobbyController {

    private static final String REDIRECT_GAME = "redirect:/game/";
    private static final String REDIRECT_GAME_BASE = "redirect:/game";
    private static final String LIST_GAME_URL = "/list";
    private static final String LIST_GAME_VIEW = "games/gameList";
    private static final String RELOAD_LIST_GAME_URL = LIST_GAME_URL + "/reload";
    private static final String RELOAD_LIST_GAME_VIEW = "games/reloadGameList";
    private static final String CREATE_GAME_URL = "/new";
    private static final String CREATE_GAME_VIEW = "games/newGameForm";
    private static final String LOBBY_BASE_GAME_URL = "/lobby";
    private static final String LOBBY_GAME_URL = "/{gameId}/lobby";
    private static final String LOBBY_GAME_VIEW = "games/gameLobby";
    private static final String RELOAD_LOBBY_GAME_URL = LOBBY_GAME_URL + "/reload";
    private static final String RELOAD_LOBBY_GAME_VIEW = "games/reloadGameLobby";
    private static final String JOIN_LOBBY_GAME_URL = "/{gameId}/join";
    private static final String PLAYING_GAME_BASE_URL = "/playing";
    private static final String KICK_PLAYER_GAME_URL = LOBBY_GAME_URL + "/player/{playerId}/kick";

    private PlayerService playerService;
    private GameService gameService;

    @Autowired
    public LobbyController(GameService gameService, PlayerService playerService) {
        this.gameService = gameService;
        this.playerService = playerService;
    }

    @GetMapping(CREATE_GAME_URL)
    public String getNewGameForm(ModelMap modelMap) {
        Game activeUserGame = playerService.activeUserGame();
        Boolean userInGame = activeUserGame != null;

        if (userInGame && activeUserGame.isInLobby()) {
            return REDIRECT_GAME + activeUserGame.getId() + LOBBY_BASE_GAME_URL;
        } else if (userInGame && activeUserGame.isInGame()) {
            return REDIRECT_GAME + activeUserGame.getId() + PLAYING_GAME_BASE_URL;
        } else {
            Game game = new Game();
            modelMap.addAttribute("game", game);
            return CREATE_GAME_VIEW;
        }
    }

    @PostMapping(CREATE_GAME_URL)
    public String postNewGameForm(ModelMap modelMap, @Validated(BasicInfo.class) Game game, BindingResult result) {
        if (result.hasErrors()) {
            modelMap.addAttribute("game", game);
            return CREATE_GAME_VIEW;
        } else {
            gameService.newGame(game);
            return REDIRECT_GAME + game.getId() + LOBBY_BASE_GAME_URL;
        }
    }

    @GetMapping(LIST_GAME_URL)
    public String gamesList(ModelMap modelMap) {
        Game activeUserGame = playerService.activeUserGame();
        Boolean userInGame = activeUserGame != null;
        if (userInGame && activeUserGame.isInLobby()) {
            return REDIRECT_GAME + activeUserGame.getId() + LOBBY_BASE_GAME_URL;
        } else if (userInGame && activeUserGame.isInGame()) {
            return REDIRECT_GAME + activeUserGame.getId() + PLAYING_GAME_BASE_URL;
        } else {
            List<Game> inLobbyGames = gameService.findAllInLobbyGames();
            modelMap.addAttribute("gamesList", inLobbyGames);
            return LIST_GAME_VIEW;
        }
    }

    @GetMapping(RELOAD_LIST_GAME_URL)
    public String gamesListReload(ModelMap modelMap) {
        List<Game> inLobbyGames = gameService.findAllInLobbyGames();
        modelMap.addAttribute("gamesList", inLobbyGames);
        return RELOAD_LIST_GAME_VIEW;
    }

    @GetMapping(LOBBY_GAME_URL)
    public String gameLobby(ModelMap modelMap, @PathVariable("gameId") int gameId) {
        try {
            Game game = gameService.findGameById(gameId);
            if (game.isInGame()) {
                return REDIRECT_GAME + game.getId() + PLAYING_GAME_BASE_URL;
            } else if (!game.isActive()) {
                return REDIRECT_GAME_BASE + LIST_GAME_URL;
            } else {
                modelMap.addAttribute("botTypes", BotTypeEnum.values());
                modelMap.addAttribute("game", game);
                return LOBBY_GAME_VIEW;
            }

        } catch (NoSuchElementException | NullPointerException e) {
            return REDIRECT_GAME_BASE + LIST_GAME_URL;
        }
    }

    @PostMapping(LOBBY_GAME_URL)
    public String addBotGameUrl(@PathVariable("gameId") int gameId,
            @ModelAttribute("botTypeEnum") BotTypeEnum botTypeEnum, BindingResult result) {
        if (result.hasErrors()) {
            return REDIRECT_GAME_BASE + LOBBY_GAME_URL;
        } else {
            gameService.addBot(gameId, botTypeEnum);
            return REDIRECT_GAME_BASE + LOBBY_GAME_URL;
        }

    }

    @GetMapping(KICK_PLAYER_GAME_URL)
    public String kickPlayerGameUrl(@PathVariable("gameId") int gameId,
            @PathVariable("playerId") int playerId) {
        gameService.kickPlayer(gameId, playerId);
        return REDIRECT_GAME_BASE + LOBBY_GAME_URL;
    }

    @GetMapping(RELOAD_LOBBY_GAME_URL)
    public String gameLobbyReload(ModelMap modelMap, @PathVariable("gameId") int gameId) {

        Game game = gameService.findGameById(gameId);

        List<Player> players = game.getPlayers();
        modelMap.addAttribute("game", game);
        modelMap.addAttribute("players", players);
        return RELOAD_LOBBY_GAME_VIEW;
    }

    @GetMapping(JOIN_LOBBY_GAME_URL)
    public String joinGameLobby(ModelMap modelMap, @PathVariable("gameId") int gameId) {
        try {
            Game game = gameService.findGameById(gameId);
            Game activeUserGame = playerService.activeUserGame();
            Boolean userAlreadyInGame = activeUserGame != null;
            Boolean gameFull = game.isFull();
            if (userAlreadyInGame) {
                return REDIRECT_GAME + activeUserGame.getId() + LOBBY_BASE_GAME_URL;
            } else if (gameFull) {
                return REDIRECT_GAME_BASE + LIST_GAME_URL;
            } else {
                playerService.joinGame(game);
                return REDIRECT_GAME + game.getId() + LOBBY_BASE_GAME_URL;
            }
        } catch (NoSuchElementException | NullPointerException e) {
            return REDIRECT_GAME_BASE + LIST_GAME_URL;
        }
    }

}
