package etsii.tfg.DungeonRaiders.player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import etsii.tfg.DungeonRaiders.game.Game;
import etsii.tfg.DungeonRaiders.user.User;
import etsii.tfg.DungeonRaiders.user.UserService;

@Service
public class PlayerService {

    private PlayerRepository playerRepository;
    private UserService userService;

    @Autowired
    public PlayerService(PlayerRepository playerRepository, UserService userService) {
        this.playerRepository = playerRepository;
        this.userService = userService;
    }

    public void joinGame(Game game) {
        // Creates a new player from the authenticated user and gives them all their
        // initial player attributes
        User user = userService.authenticatedUser();

        Character randomCharacter = getRandomCharacterNotUsed(game);
        Player newPlayer = new Player(user, game, randomCharacter.getInitialCoins(),
                randomCharacter.getInitialWounds(), randomCharacter);
        save(newPlayer);
    }

    public void save(Player player) {
        playerRepository.save(player);
    }

    private Character getRandomCharacterNotUsed(Game game) {
        // Returns a random character from the characters not already being used in the
        // given game

        List<Player> players = game.getPlayers();
        List<Character> charactersUsed = new ArrayList<Character>();
        List<Character> freeCharacters = new ArrayList<>(Arrays.asList(Character.class.getEnumConstants()));
        if (players != null) {
            for (Player p : players) {
                charactersUsed.add(p.getCharacter());
            }
            freeCharacters.removeAll(charactersUsed);
        }
        Random rand = new Random();
        Character randomCharacter = freeCharacters.get(rand.nextInt(freeCharacters.size()));
        return randomCharacter;
    }

    public void deleteById(Integer id) {
        playerRepository.deletePlayerById(id);
    }

    public Game activeUserGame() {
        return playerRepository.activeGameByUsername(userService.authenticatedUsername());
    }

    public Player activePlayer() {
        return playerRepository.activePlayerByUsername(userService.authenticatedUsername());
    }

    public List<Player> otherPlayersInGame(Game game, Player activePlayer) {
        List<Player> otherPlayers = new ArrayList<>(game.getPlayers());
        otherPlayers.remove(activePlayer);
        return otherPlayers;
    }
}
