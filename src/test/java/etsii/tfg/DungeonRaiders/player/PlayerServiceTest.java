package etsii.tfg.DungeonRaiders.player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import etsii.tfg.DungeonRaiders.game.Game;
import etsii.tfg.DungeonRaiders.game.GameService;
import etsii.tfg.DungeonRaiders.user.User;
import etsii.tfg.DungeonRaiders.user.UserService;

@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
class PlayerServiceTest {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private GameService gameService;

    @SpyBean
    private UserService userService;

    private User userTest1;
    private User userTest2;
    private User userTest3;
    private User userTest4;
    private User userTest5;
    private Game gameTest1;

    @BeforeEach
    void createInitialGameAndUsers() {
        userTest1 = new User();
        userTest1.setUsername("userTest1");
        userTest1.setDecryptedPassword("userTest1");
        userService.save(userTest1);

        userTest2 = new User();
        userTest2.setUsername("userTest2");
        userTest2.setDecryptedPassword("userTest2");
        userService.save(userTest2);

        userTest3 = new User();
        userTest3.setUsername("userTest3");
        userTest3.setDecryptedPassword("userTest3");
        userService.save(userTest3);

        userTest4 = new User();
        userTest4.setUsername("userTest4");
        userTest4.setDecryptedPassword("userTest4");
        userService.save(userTest4);

        userTest5 = new User();
        userTest5.setUsername("userTest5");
        userTest5.setDecryptedPassword("userTest5");
        userService.save(userTest5);

        gameTest1 = new Game();
        gameTest1.setCreatorUsername("userTest1");
        gameTest1.setMaxPlayers(3);
        gameTest1.setName("Test Game");
        gameService.save(gameTest1);

    }

    @Test
    void testJoinGameCreatesPlayer() {
        doReturn(userTest1).when(userService).authenticatedUser();
        doReturn(userTest1.getUsername()).when(userService).authenticatedUsername();
        playerService.joinGame(gameTest1);
        assertNotNull(playerService.activePlayer());
    }

    @Test
    void testJoinGameCreatesAllDifferentCharacters() {
        List<Player> allPlayers = new ArrayList<Player>();
        doReturn(userTest1).when(userService).authenticatedUser();
        doReturn(userTest1.getUsername()).when(userService).authenticatedUsername();
        playerService.joinGame(gameTest1);
        allPlayers.add(playerService.activePlayer());

        gameTest1.setPlayers(allPlayers);
        doReturn(userTest2).when(userService).authenticatedUser();
        doReturn(userTest2.getUsername()).when(userService).authenticatedUsername();
        playerService.joinGame(gameTest1);
        allPlayers.add(playerService.activePlayer());

        gameTest1.setPlayers(allPlayers);
        doReturn(userTest3).when(userService).authenticatedUser();
        doReturn(userTest3.getUsername()).when(userService).authenticatedUsername();
        playerService.joinGame(gameTest1);
        allPlayers.add(playerService.activePlayer());

        gameTest1.setPlayers(allPlayers);
        doReturn(userTest4).when(userService).authenticatedUser();
        doReturn(userTest4.getUsername()).when(userService).authenticatedUsername();
        playerService.joinGame(gameTest1);
        allPlayers.add(playerService.activePlayer());

        gameTest1.setPlayers(allPlayers);
        doReturn(userTest5).when(userService).authenticatedUser();
        doReturn(userTest5.getUsername()).when(userService).authenticatedUsername();
        playerService.joinGame(gameTest1);
        allPlayers.add(playerService.activePlayer());

        Boolean allDifferentCharacters = allPlayers.stream()
                .map(p -> p.getCharacter())
                .distinct()
                .count() == allPlayers.size();
        assertEquals(true, allDifferentCharacters);
    }

    @Test
    @WithMockUser(username = "userTest1")
    void testSave() {
        Player newPlayer = new Player(userTest1, gameTest1, 0, 0, Character.explorer);
        playerService.save(newPlayer);
        assertNotNull(playerService.activePlayer());

    }

    @Test
    @WithMockUser(username = "userTest1")
    void testDeleteById() {
        Player newPlayer = new Player(userTest1, gameTest1, 0, 0, Character.explorer);
        playerService.save(newPlayer);
        assertNotNull(playerService.activePlayer());
        playerService.deleteById(newPlayer.getId());
        assertNull(playerService.activePlayer());

    }

    @Test
    @WithMockUser(username = "userTest1")
    void testActiveUserGame() {
        Player newPlayer = new Player(userTest1, gameTest1, 0, 0, Character.explorer);
        playerService.save(newPlayer);
        assertEquals(gameTest1.getId(), playerService.activeUserGame().getId());
    }

    @Test
    @WithMockUser(username = "userTest1")
    void testActivePlayer() {
        Player newPlayer = new Player(userTest1, gameTest1, 0, 0, Character.explorer);
        playerService.save(newPlayer);
        assertEquals(newPlayer.getId(), playerService.activePlayer().getId());
    }

}
