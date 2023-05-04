package etsii.tfg.DungeonRaiders.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.never;

import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import etsii.tfg.DungeonRaiders.card.Card;
import etsii.tfg.DungeonRaiders.card.CardService;
import etsii.tfg.DungeonRaiders.card.CardState;
import etsii.tfg.DungeonRaiders.card.CardType;
import etsii.tfg.DungeonRaiders.player.Character;
import etsii.tfg.DungeonRaiders.player.Player;
import etsii.tfg.DungeonRaiders.player.PlayerService;
import etsii.tfg.DungeonRaiders.room.Room;
import etsii.tfg.DungeonRaiders.room.RoomService;
import etsii.tfg.DungeonRaiders.roomDungeon.RoomDungeon;
import etsii.tfg.DungeonRaiders.roomDungeon.RoomDungeonService;
import etsii.tfg.DungeonRaiders.torchRoom.TorchRoomService;
import etsii.tfg.DungeonRaiders.user.User;
import etsii.tfg.DungeonRaiders.user.UserService;

@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@Transactional
class GameServiceTest {

    @Autowired
    private GameService gameService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private UserService userService;

    @MockBean
    public RoomDungeonService roomDungeonService;

    @MockBean
    public CardService cardService;

    @MockBean
    public TorchRoomService torchRoomService;

    @Autowired
    private RoomService roomService;

    private User userTest1;
    private User userTest2;
    private User userTest3;
    private User userTest4;
    private Game gameTest1;
    private Player player1;
    private Player player2;
    private Player player3;
    List<Player> allPlayers;

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

        gameTest1 = new Game();
        gameTest1.setCreatorUsername("userTest1");
        gameTest1.setMaxPlayers(3);
        gameTest1.setName("Test Game");
        gameService.save(gameTest1);

        player1 = new Player();
        player1.setGame(gameTest1);
        player1.setUser(userTest1);
        player1.setCharacter(Character.explorer);
        player1.setCoins(1);
        player1.setWounds(0);
        playerService.save(player1);

        player2 = new Player();
        player2.setGame(gameTest1);
        player2.setUser(userTest2);
        player2.setCharacter(Character.knight);
        player2.setCoins(2);
        player2.setWounds(1);
        playerService.save(player2);

        player3 = new Player();
        player3.setGame(gameTest1);
        player3.setUser(userTest3);
        player3.setCharacter(Character.sorcerer);
        player3.setCoins(0);
        player3.setWounds(0);
        playerService.save(player3);

        allPlayers = new ArrayList<Player>();
        allPlayers.add(player1);
        allPlayers.add(player2);
        allPlayers.add(player3);
    }

    @Test
    void testSaveAndFindById() {
        assertEquals(gameTest1.getId(), gameService.findGameById(gameTest1.getId()).getId());
    }

    @Test
    void testFindAllInLobby() {
        Game gameTest2 = new Game();
        gameTest2.setCreatorUsername("userTest1");
        gameTest2.setMaxPlayers(3);
        gameTest2.setName("Test Game");
        gameTest2.setTurn(2); // Not in lobby
        gameService.save(gameTest2);
        assertEquals(2, gameService.findAllInLobbyGames().size());
    }

    @Test
    @WithMockUser(username = "userTest4")
    void testNewGame() {
        Game gameTest2 = new Game();
        gameTest2.setMaxPlayers(3);
        gameTest2.setName("Test Game");
        gameService.newGame(gameTest2);
        assertEquals(gameTest2.getCreatorUsername(), "userTest4");
        assertNotNull(gameService.findGameById(gameTest2.getId()));
    }

    @Test
    @WithMockUser(username = "userTest1")
    void testExitActiveGameCreator() {
        gameTest1.setPlayers(allPlayers); // Needed only in tests
        gameService.save(gameTest1);

        assertNotNull(gameService.findGameById(gameTest1.getId()));
        gameService.exitActiveGame();
        assertNull(gameService.findGameById(gameTest1.getId()));
    }

    @Test
    @WithMockUser(username = "userTest1")
    void testIsActiveUserCreator() {
        Game gameTest2 = new Game();
        gameTest2.setMaxPlayers(3);
        gameTest2.setName("Test Game");
        gameService.newGame(gameTest2);
        assertTrue(gameService.isActiveUserCreator(gameTest2));
    }

    @Test
    @WithMockUser(username = "userTest1")
    void testStartGame() {
        playerService.save(player1);
        List<Player> players = new ArrayList<Player>();
        players.add(player1);
        gameTest1.setPlayers(players); // Needed only in tests
        gameService.newGame(gameTest1);
        assertEquals(-1, gameTest1.getTurn());
        gameService.startGame(gameTest1);
        assertEquals(0, gameTest1.getTurn());
    }

    @ParameterizedTest
    @WithMockUser(username = "userTest2")
    @CsvFileSource(resources = "/game/playCard.csv", numLinesToSkip = 1)
    void testPlayCardInTypeOfRoom(Integer roomId, CardType cardType, CardState cardState) {
        gameTest1.setPlayers(allPlayers); // Needed only in tests
        gameService.startGame(gameTest1);

        Card card = new Card(cardType, player2);
        List<Card> cards = new ArrayList<>();
        cards.add(card);
        player2.setCards(cards);

        Room room = roomService.findRoomById(roomId);
        Mockito.when(roomDungeonService.getExactRoomInGame(0, 0, gameTest1.getId())).thenReturn(room);

        gameService.playCard(gameTest1, card);
        assertEquals(cardState, card.getCardState());
    }

    @Test
    @WithMockUser(username = "userTest3")
    void testPlayCardAllPlayers() {
        gameTest1.setPlayers(allPlayers); // Needed only in tests
        gameService.startGame(gameTest1);

        List<Card> cardsPlayed = new ArrayList<>();
        Card card = new Card(CardType.five, player1);
        List<Card> cards = new ArrayList<>();
        cards.add(card);
        cardsPlayed.add(card);
        player1.setCards(cards);

        card = new Card(CardType.five, player2);
        cards = new ArrayList<>();
        cards.add(card);
        cardsPlayed.add(card);
        player2.setCards(cards);

        card = new Card(CardType.five, player3);
        cards = new ArrayList<>();
        cards.add(card);
        cardsPlayed.add(card);
        player3.setCards(cards);

        Room room = roomService.findRoomById(5);
        Mockito.when(roomDungeonService.getExactRoomInGame(0, 0, gameTest1.getId())).thenReturn(room);
        Mockito.when(cardService.findAllCardsPlayedThisTurnByHumans(gameTest1.getId())).thenReturn(cardsPlayed);
        Mockito.when(cardService.findAllCardsPlayedThisTurn(gameTest1.getId())).thenReturn(cardsPlayed);
        gameService.playCard(gameTest1, card);
        Mockito.verify(cardService).handleCardsPlayedThisTurn(gameTest1, cardsPlayed);
    }

    @Test
    void testNewTurnSameFloor() {
        gameTest1.setTurn(0);
        gameService.newTurn(gameTest1);
        Mockito.verify(cardService).newRoomHand(gameTest1);
    }

    @Test
    void testNewTurnDifferentFloor() {
        gameTest1.setTurn(4);
        gameService.newTurn(gameTest1);
        Mockito.verify(cardService).newFloorHand(gameTest1);
    }

    @ParameterizedTest
    @WithMockUser(username = "userTest1")
    @CsvFileSource(resources = "/game/playTorch.csv", numLinesToSkip = 1)
    void testPlayTorch(CardType cardType, Boolean isHidden, Boolean isInGame, Integer position, Integer floor,
            Boolean isPlayed) {
        Room room = roomService.findRoomById(5);

        if (isInGame) {
            gameService.startGame(gameTest1);
        }
        RoomDungeon roomDungeon = new RoomDungeon(gameTest1, room, isHidden, position, floor);
        Card card = new Card(cardType, player1);
        List<Card> cards = new ArrayList<>();
        cards.add(card);
        player1.setCards(cards);

        gameService.playTorchCard(playerService.activePlayer(), roomDungeon);
        if (isPlayed) {
            Mockito.verify(torchRoomService).revealRoom(roomDungeon, player1);
            Mockito.verify(cardService).deleteCardById(card.getId());
        } else {
            Mockito.verify(torchRoomService, never()).revealRoom(roomDungeon, player1);
            Mockito.verify(cardService, never()).deleteCardById(card.getId());
        }
    }
}
