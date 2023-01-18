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
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private UserService userService;

    public void joinGame(Game game) {
        // Creates a new player from the authenticated user and gives them all their
        // initial player attributes
        User user = userService.authenticatedUser();

        Player newPlayer = new Player();
        Character randomCharacter = getRandomCharacterNotUsed(game);
        newPlayer.setUser(user);
        newPlayer.setGame(game);
        newPlayer.setCoins(randomCharacter.getInitialCoins());
        newPlayer.setWounds(randomCharacter.getInitialWounds());
        newPlayer.setCharacter(randomCharacter);
        save(newPlayer);
    }

    private void save(Player player) {
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

}
