package etsii.tfg.DungeonRaiders.game;

import java.util.List;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AcceptAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import etsii.tfg.DungeonRaiders.player.Player;
import etsii.tfg.DungeonRaiders.player.PlayerRepository;
import etsii.tfg.DungeonRaiders.player.PlayerService;
import etsii.tfg.DungeonRaiders.user.User;
import etsii.tfg.DungeonRaiders.user.UserRepository;
import etsii.tfg.DungeonRaiders.user.UserService;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PlayerService playerService;

    public List<Game> findAllInLobbyGames() {
        return gameRepository.findAllInLobbyGames();
    }

    public void save(Game game) {
        gameRepository.save(game);
    }

    public void newGame(Game game) {

        game.setCreatorUsername(userService.authenticatedUsername());
        save(game);
        playerService.joinGame(game);
    }

    public Game findGameById(int gameId) {
        return gameRepository.findById(gameId).orElseThrow();
    }

    public Game activeUserGame() {
        return playerRepository.activeGameByUsername(userService.authenticatedUsername());
    }

    public void exitActiveGame() {
        Game activeGame = activeUserGame();

        if (activeGame != null) {
            if (activeGame.isInLobby()) {
                deleteActiveGame();
            }
            List<Player> playerList = activeGame.getPlayers();
            Player player = playerList.stream()
                    .filter(p -> p.getUser().getUsername().equals(userService.authenticatedUsername())).findAny()
                    .get();

            playerService.deleteById(player.getId());

        }
    }

    public void deleteActiveGame() {
        Game activeGame = activeUserGame();
        if (activeGame.getCreatorUsername().equals(userService.authenticatedUsername())) {
            gameRepository.delete(activeGame);
        }
    }

}
