package etsii.tfg.DungeonRaiders.game;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import etsii.tfg.DungeonRaiders.roomDungeon.RoomDungeon;
import etsii.tfg.DungeonRaiders.roomDungeon.RoomDungeonService;
import etsii.tfg.DungeonRaiders.card.Card;
import etsii.tfg.DungeonRaiders.card.CardService;
import etsii.tfg.DungeonRaiders.card.CardType;
import etsii.tfg.DungeonRaiders.player.Player;
import etsii.tfg.DungeonRaiders.player.PlayerService;

@RequestMapping("/game")
@Controller
public class GameController {

    private static final String REDIRECT_GAME = "redirect:/game";
    private static final String LIST_GAME_URL = "/list";
    private static final String EXIT_GAME_URL = "/{gameId}/exit";
    private static final String START_GAME_URL = "/{gameId}/start";
    private static final String PLAYING_GAME_URL = "/{gameId}/playing";
    private static final String PLAYING_GAME_VIEW = "games/gamePlaying";
    private static final String PLAY_CARD_GAME_URL = "/{gameId}/card/{cardId}/play";
    private static final String PLAY_TORCH_CARD_GAME_URL = "/{gameId}/room/{roomDungeonId}/reveal";
    private static final String END_GAME_URL = "/{gameId}/finished";
    private static final String END_GAME_VIEW = "games/finishedGame";
    private static final String RELOAD_PLAYING_GAME_URL = "/{gameId}/playing/reload";
    private static final String RELOAD_PLAYING_GAME_VIEW = "games/reloadDataInGame";
    private static final String LOBBY_GAME_URL = "/{gameId}/lobby";

    private GameService gameService;
    private PlayerService playerService;
    private RoomDungeonService roomDungeonService;
    private CardService cardService;

    @Autowired
    public GameController(GameService gameService, PlayerService playerService, RoomDungeonService roomDungeonService,
            CardService cardService) {
        this.gameService = gameService;
        this.playerService = playerService;
        this.roomDungeonService = roomDungeonService;
        this.cardService = cardService;
    }

    @GetMapping(EXIT_GAME_URL)
    public String exitGame(ModelMap modelMap) {
        gameService.exitActiveGame();
        return REDIRECT_GAME + LIST_GAME_URL;
    }

    @GetMapping(START_GAME_URL)
    public String startGame(ModelMap modelMap, @PathVariable("gameId") int gameId) {
        try {
            Game game = gameService.findGameById(gameId);
            Boolean activeUserIsGameCreator = gameService.isActiveUserCreator(game);
            if (activeUserIsGameCreator && game.isInLobby() && game.hasEnoughPlayersToStart()) {
                gameService.startGame(game);
                return REDIRECT_GAME + PLAYING_GAME_URL;
            } else {
                return REDIRECT_GAME + LIST_GAME_URL;
            }
        } catch (NoSuchElementException | NullPointerException e) {
            return REDIRECT_GAME + LIST_GAME_URL;
        }
    }

    @GetMapping(PLAYING_GAME_URL)
    public String playingGame(ModelMap modelMap, @PathVariable("gameId") int gameId) {
        try {
            Game game = gameService.findGameById(gameId);
            if (!game.isActive() || !playerService.activeUserGame().equals(game)) {
                return REDIRECT_GAME + END_GAME_URL;
            } else if (game.isInLobby()) {
                return REDIRECT_GAME + LOBBY_GAME_URL;
            } else {
                Player activePlayer = playerService.activePlayer();
                modelMap.addAttribute("game", game);
                modelMap.addAttribute("activePlayer", activePlayer);
                return PLAYING_GAME_VIEW;
            }
        } catch (NoSuchElementException | NullPointerException e) {
            return REDIRECT_GAME + LIST_GAME_URL;
        }
    }

    @GetMapping(RELOAD_PLAYING_GAME_URL)
    public String reloadDataInGame(ModelMap modelMap, @PathVariable("gameId") int gameId) {
        Game game = gameService.findGameById(gameId);
        Player activePlayer = playerService.activePlayer();
        List<Player> otherPlayers = playerService.otherPlayersInGame(game, activePlayer);
        List<RoomDungeon> floorDungeonRooms = roomDungeonService.actualFloor(game, activePlayer);
        List<CardType> revealedCards = cardService.revealedCards(game.getId());
        modelMap.addAttribute("otherPlayers", otherPlayers);
        modelMap.addAttribute("game", game);
        modelMap.addAttribute("floorDungeonRooms", floorDungeonRooms);
        modelMap.addAttribute("revealedCards", revealedCards);
        modelMap.addAttribute("activePlayer", activePlayer);
        modelMap.addAttribute("actualRoom",
                roomDungeonService.actualFloor(game, activePlayer).get(game.getActualRoomInFloor()).getRoom());
        return RELOAD_PLAYING_GAME_VIEW;

    }

    @GetMapping(END_GAME_URL)
    public String finishedGame(ModelMap modelMap, @PathVariable("gameId") int gameId) {
        try {
            Game game = gameService.findGameById(gameId);
            if (game.isActive()) {
                return REDIRECT_GAME + gameId + PLAYING_GAME_URL;
            } else {
                modelMap.addAttribute("game", game);
                return END_GAME_VIEW;
            }
        } catch (NoSuchElementException | NullPointerException e) {
            return REDIRECT_GAME + LIST_GAME_URL;
        }
    }

    @GetMapping(PLAY_CARD_GAME_URL)
    public String playCard(@PathVariable("gameId") int gameId, @PathVariable("cardId") int cardId) {
        try {
            Game game = gameService.findGameById(gameId);
            Card card = cardService.findCardById(cardId);
            gameService.playCard(game, card);
            return REDIRECT_GAME + PLAYING_GAME_URL;
        } catch (NoSuchElementException | NullPointerException e) {
            return REDIRECT_GAME + LIST_GAME_URL;
        }
    }

    @GetMapping(PLAY_TORCH_CARD_GAME_URL)
    public String playTorchCard(@PathVariable("gameId") int gameId,
            @PathVariable("roomDungeonId") int roomDungeonId) {
        try {
            RoomDungeon roomDungeon = roomDungeonService.findRoomDungeonById(roomDungeonId);
            Player activePlayer = playerService.activePlayer();
            gameService.playTorchCard(activePlayer, roomDungeon);
            return REDIRECT_GAME + PLAYING_GAME_URL;
        } catch (NoSuchElementException | NullPointerException e) {
            return REDIRECT_GAME + LIST_GAME_URL;
        }
    }

}
