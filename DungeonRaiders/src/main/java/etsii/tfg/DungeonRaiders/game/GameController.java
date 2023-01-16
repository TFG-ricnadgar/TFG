package etsii.tfg.DungeonRaiders.game;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import etsii.tfg.DungeonRaiders.user.UserService;

@RequestMapping("/game")
@Controller
public class GameController {

    private static final String LIST_GAME_URL = "/list";
    private static final String LIST_GAME_VIEW = "games/gameList";
    private static final String CREATE_GAME_URL = "/new";
    private static final String CREATE_GAME_VIEW = "games/newGameForm";
    private static final String LOBBY_GAME_URL = "/lobby";
    private static final String LOBBY_GAME_VIEW = "games/gameLobby";

    @Autowired
    private GameService gameService;

    @Autowired
    private UserService userService;

    @GetMapping(CREATE_GAME_URL)
    public String getNewGameForm(ModelMap modelMap) {
        Game game = new Game();
        modelMap.addAttribute("game", game);
        return CREATE_GAME_VIEW;
    }

    @PostMapping(CREATE_GAME_URL)
    public String postNewGameForm(ModelMap modelMap, Game game) {

        Boolean gameNameEmpty = game.getGameName().isBlank();
        if (gameNameEmpty) {
            List<String> messages = new ArrayList<String>();
            if (gameNameEmpty) {
                messages.add("El nombre de la partida no es adecuado");
            }
            modelMap.addAttribute("messages", messages);
            modelMap.addAttribute("game", game);
            return CREATE_GAME_VIEW;
        } else {
            gameService.newGame(game);
            return "redirect:/games/" + game.getId() + LOBBY_GAME_URL;
        }
    }

    @GetMapping(LIST_GAME_URL)
    public String gamesList(ModelMap modelMap) {
        List<Game> inLobbyGames = gameService.findAllInLobbyGames();
        modelMap.addAttribute("gamesList", inLobbyGames);
        return LIST_GAME_VIEW;
    }
}
