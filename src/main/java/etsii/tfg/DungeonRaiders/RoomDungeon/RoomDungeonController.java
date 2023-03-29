package etsii.tfg.DungeonRaiders.roomDungeon;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import etsii.tfg.DungeonRaiders.game.Game;
import etsii.tfg.DungeonRaiders.game.GameService;
import etsii.tfg.DungeonRaiders.player.Player;
import etsii.tfg.DungeonRaiders.player.PlayerService;

@RequestMapping("/game")
@Controller
public class RoomDungeonController {

    private static final String ROOMS_GAME_URL = "/{gameId}/playing/rooms";
    private static final String ROOMS_GAME_VIEW = "games/roomsInGame";

    private RoomDungeonService roomDungeonService;
    private PlayerService playerService;
    private GameService gameService;

    @Autowired
    public RoomDungeonController(RoomDungeonService roomDungeonService, PlayerService playerService,
            GameService gameService) {
        this.roomDungeonService = roomDungeonService;
        this.playerService = playerService;
        this.gameService = gameService;
    }

    @GetMapping(ROOMS_GAME_URL)
    public String getRoomDungeonsOfGame(ModelMap modelMap, @PathVariable("gameId") int gameId) {
        Game game = gameService.findGameById(gameId);
        Player activePlayer = playerService.activePlayer();
        List<RoomDungeon> floorDungeonRooms = roomDungeonService.actualFloor(game, activePlayer);
        modelMap.addAttribute("game", game);
        modelMap.addAttribute("floorDungeonRooms", floorDungeonRooms);
        return ROOMS_GAME_VIEW;
    }
}
