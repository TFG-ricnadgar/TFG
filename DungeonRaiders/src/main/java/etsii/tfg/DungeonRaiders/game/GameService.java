package etsii.tfg.DungeonRaiders.game;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import etsii.tfg.DungeonRaiders.user.UserService;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private UserService userService;

    public List<Game> findAllInLobbyGames() {
        return gameRepository.findAllInLobbyGames();
    }

    public void save(Game game) {
        gameRepository.save(game);
    }

    public void newGame(Game game) {
        game.setCreatorUsername(userService.authenticatedUsername());
        save(game);
    }
}
