package etsii.tfg.DungeonRaiders.game;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import etsii.tfg.DungeonRaiders.roomDungeon.RoomDungeonService;
import etsii.tfg.DungeonRaiders.card.Card;
import etsii.tfg.DungeonRaiders.card.CardService;
import etsii.tfg.DungeonRaiders.card.CardType;
import etsii.tfg.DungeonRaiders.player.Player;
import etsii.tfg.DungeonRaiders.player.PlayerService;
import etsii.tfg.DungeonRaiders.room.Room;
import etsii.tfg.DungeonRaiders.user.UserService;
import etsii.tfg.DungeonRaiders.util.DungeonRaiderConstants;

@Service
public class GameService {

    private GameRepository gameRepository;
    private UserService userService;
    private PlayerService playerService;
    private CardService cardService;
    private RoomDungeonService roomDungeonService;

    @Autowired
    public GameService(GameRepository gameRepository, UserService userService,
            PlayerService playerService, CardService cardService, RoomDungeonService roomDungeonService) {
        this.gameRepository = gameRepository;
        this.userService = userService;
        this.playerService = playerService;
        this.cardService = cardService;
        this.roomDungeonService = roomDungeonService;
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
        game.setTurn(0);
        cardService.givePlayersStartingGameHand(game.getPlayers());
        roomDungeonService.generateDungeon(game);
    }

    public void playCard(Game game, Card card) {
        Boolean cardNotPlayedInTurn = cardService.findCardPlayedInTurn(card.getPlayer().getId()) == null;
        Room roomInPlay = roomDungeonService.getExactRoomInGame(game.getActualRoomInFloor(), game.getActualFloor(),
                game.getId());
        Boolean swordWithEnemy = roomInPlay.getType() == "ENEMY" && card.getType() == CardType.sword;
        Boolean keyWithTreasure = roomInPlay.getType() == "TREASURE" && card.getType() == CardType.key;
        Boolean cardIsPlayable = swordWithEnemy || keyWithTreasure || card.isBasic();

        if (game.isInGame() && !card.getIsUsed() && cardNotPlayedInTurn && cardIsPlayable) {
            card.setIsUsed(true);
            card.setIsRecentlyUsed(true);
            cardService.save(card);

            List<Card> cardsPlayedThisTurn = cardService.findAllCardsPlayedThisTurn(game.getId());
            if (cardsPlayedThisTurn.size() >= game.getPlayers().size()) {
                cardService.handleCardsPlayedThisTurn(game, cardsPlayedThisTurn);
                newTurn(game);
            }
        }
    }

    public void newTurn(Game game) {
        Integer oldFloor = game.getActualFloor();
        game.setTurn(game.getTurn() + 1);
        if (game.getActualFloor() >= DungeonRaiderConstants.ROOMS_PER_FLOOR_AMOUNT) {
            handleEndOfGame();
        } else if (oldFloor == game.getActualFloor()) {
            cardService.newRoomHand(game);
        } else {
            cardService.newFloorHand(game);
        }
    }

    private void handleEndOfGame() {
        // TODO
    }

}
