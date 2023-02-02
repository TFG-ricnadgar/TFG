package etsii.tfg.DungeonRaiders.game;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import etsii.tfg.DungeonRaiders.player.Player;
import etsii.tfg.DungeonRaiders.player.PlayerService;

@RequestMapping("/game")
@Controller
public class GameController {

    private static final String REDIRECT_GAME = "redirect:/game/";
    private static final String REDIRECT_GAME_BASE = "redirect:/game";
    private static final String LIST_GAME_URL = "/list";
    private static final String LIST_GAME_VIEW = "games/gameList";
    private static final String CREATE_GAME_URL = "/new";
    private static final String CREATE_GAME_VIEW = "games/newGameForm";
    private static final String LOBBY_BASE_GAME_URL = "/lobby";
    private static final String LOBBY_GAME_URL = "/{gameId}/lobby";
    private static final String LOBBY_GAME_VIEW = "games/gameLobby";
    private static final String JOIN_LOBBY_GAME_URL = "/{gameId}/join";
    private static final String EXIT_GAME_URL = "/{gameId}/exit";
    private static final String DELETE_GAME_URL = "/{gameId}/delete";
    private static final String START_GAME_URL = "/{gameId}/start";
    private static final String PLAYING_GAME_URL = "/{gameId}/playing";
    private static final String PLAYING_GAME_BASE_URL = "/playing";
    private static final String PLAYING_GAME_VIEW = "games/gamePlaying";

    private GameService gameService;
    private PlayerService playerService;

    @Autowired
    public GameController(GameService gameService, PlayerService playerService) {
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
    public String postNewGameForm(ModelMap modelMap, Game game) {
        Boolean gameNameShortOrLong = game.getName().length() > 20 || game.getName().length() < 3;
        if (gameNameShortOrLong) {
            List<String> messages = new ArrayList<String>();

            if (gameNameShortOrLong) {
                messages.add("El nombre de la partida debe tener mas de 3 caracteres y menos de 15");
            }
            modelMap.addAttribute("messages", messages);
            modelMap.addAttribute("game", game);
            return CREATE_GAME_VIEW;
        } else {
            gameService.newGame(game);
            return REDIRECT_GAME + game.getId() + LOBBY_BASE_GAME_URL;
        }
    }

    @GetMapping(LIST_GAME_URL)
    public String gamesList(ModelMap modelMap) {
        List<Game> inLobbyGames = gameService.findAllInLobbyGames();
        modelMap.addAttribute("gamesList", inLobbyGames);
        return LIST_GAME_VIEW;
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
                List<Player> players = game.getPlayers();
                modelMap.addAttribute("game", game);
                modelMap.addAttribute("players", players);
                return LOBBY_GAME_VIEW;
            }

        } catch (NoSuchElementException e) {
            return REDIRECT_GAME_BASE + LIST_GAME_URL;
        }
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
        } catch (NoSuchElementException e) {
            return REDIRECT_GAME_BASE + LIST_GAME_URL;
        }
    }

    @GetMapping(EXIT_GAME_URL)
    public String exitGame(ModelMap modelMap) {
        gameService.exitActiveGame();
        return REDIRECT_GAME_BASE + LIST_GAME_URL;
    }

    @GetMapping(DELETE_GAME_URL)
    public String deleteGame(ModelMap modelMap) {
        gameService.deleteActiveGame();
        return REDIRECT_GAME_BASE + LIST_GAME_URL;
    }

    @GetMapping(START_GAME_URL)
    public String startGame(ModelMap modelMap, @PathVariable("gameId") int gameId) {
        try {
            Game game = gameService.findGameById(gameId);
            Boolean activeUserIsGameCreator = gameService.isActiveUserCreator(game);
            if (activeUserIsGameCreator && game.isInLobby() && game.hasEnoughPlayersToStart()) {
                gameService.startGame(game);
                return REDIRECT_GAME + gameId + PLAYING_GAME_BASE_URL;
            } else {
                return REDIRECT_GAME_BASE + LIST_GAME_URL;
            }
        } catch (NoSuchElementException e) {
            return REDIRECT_GAME_BASE + LIST_GAME_URL;
        }
    }

    @GetMapping(PLAYING_GAME_URL)
    public String playingGame(ModelMap modelMap, @PathVariable("gameId") int gameId) {
        try {
            Game game = gameService.findGameById(gameId);
            Player activePlayer = playerService.activePlayer();
            List<Player> otherPlayers = playerService.otherPlayersInGame(game, activePlayer);
            modelMap.addAttribute("game", game);
            modelMap.addAttribute("activePlayer", activePlayer);
            modelMap.addAttribute("otherPlayers", otherPlayers);

            return PLAYING_GAME_VIEW;
        } catch (NoSuchElementException e) {
            return REDIRECT_GAME_BASE + LIST_GAME_URL;
        }

    }

}
