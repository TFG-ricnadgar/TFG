package etsii.tfg.DungeonRaiders.game;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import etsii.tfg.DungeonRaiders.card.CardService;
import etsii.tfg.DungeonRaiders.player.Player;
import etsii.tfg.DungeonRaiders.player.PlayerService;
import etsii.tfg.DungeonRaiders.user.UserService;

@Service
public class GameService {

    private GameRepository gameRepository;
    private UserService userService;
    private PlayerService playerService;
    private CardService cardService;

    @Autowired
    public GameService(GameRepository gameRepository, UserService userService,
            PlayerService playerService, CardService cardService) {
        this.gameRepository = gameRepository;
        this.userService = userService;
        this.playerService = playerService;
        this.cardService = cardService;
    }

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

    public void exitActiveGame() {
        Game activeGame = playerService.activeUserGame();

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
        Game activeGame = playerService.activeUserGame();
        if (activeGame.getCreatorUsername().equals(userService.authenticatedUsername())) {
            gameRepository.delete(activeGame);
        }
    }

    public Boolean isActiveUserCreator(Game game) {
        return userService.authenticatedUsername().equals(game.getCreatorUsername());
    }

    public void startGame(Game game) {
        game.setTurn(1);
        cardService.givePlayersStartingGameHand(game.getPlayers());
    }

}
