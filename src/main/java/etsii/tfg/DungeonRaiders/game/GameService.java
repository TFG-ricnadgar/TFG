package etsii.tfg.DungeonRaiders.game;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import etsii.tfg.DungeonRaiders.roomDungeon.RoomDungeon;
import etsii.tfg.DungeonRaiders.roomDungeon.RoomDungeonService;
import etsii.tfg.DungeonRaiders.torchRoom.TorchRoomService;
import etsii.tfg.DungeonRaiders.card.Card;
import etsii.tfg.DungeonRaiders.card.CardService;
import etsii.tfg.DungeonRaiders.card.CardState;
import etsii.tfg.DungeonRaiders.card.CardType;
import etsii.tfg.DungeonRaiders.player.BotTypeEnum;
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
    private TorchRoomService torchRoomService;

    private static final Integer MIN_PLAYER_AMOUNT = 3;

    @Autowired
    public GameService(GameRepository gameRepository, UserService userService,
            PlayerService playerService, CardService cardService, RoomDungeonService roomDungeonService,
            TorchRoomService torchRoomService) {
        this.gameRepository = gameRepository;
        this.userService = userService;
        this.playerService = playerService;
        this.cardService = cardService;
        this.roomDungeonService = roomDungeonService;
        this.torchRoomService = torchRoomService;
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
        return gameRepository.findById(gameId).orElse(null);
    }

    public void exitActiveGame() {
        Game activeGame = playerService.activeUserGame();

        if (activeGame != null) {
            Boolean lessThanMinPlayersInGame = activeGame.isInGame()
                    && activeGame.getPlayers().size() - 1 < MIN_PLAYER_AMOUNT;
            Boolean creatorExitsInLobby = activeGame.isInLobby()
                    && activeGame.getCreatorUsername().equals(userService.authenticatedUsername());
            if (lessThanMinPlayersInGame || creatorExitsInLobby) {
                deleteGame(activeGame.getId());
            } else {
                Player player = playerService.activePlayer();
                playerService.deleteByPlayer(player);
            }
        }
    }

    public void deleteGame(int gameId) {
        gameRepository.deleteById(gameId);

    }

    public Boolean isActiveUserCreator(Game game) {
        return userService.authenticatedUsername().equals(game.getCreatorUsername());
    }

    public void startGame(Game game) {
        game.setTurn(0);
        Calendar currentTime = Calendar.getInstance();
        currentTime.add(Calendar.MINUTE, 1);
        Date nextTurnTime = currentTime.getTime();
        game.setNextTurnTime(nextTurnTime);
        cardService.givePlayersStartingGameHand(game.getPlayers());
        roomDungeonService.generateDungeon(game);

    }

    private Boolean cardIsPlayable(Player activePlayer, Game game, Card card) {

        Boolean playerInGame = game.equals(activePlayer.getGame());

        Boolean cardIsOwnedByPlayer = card.getPlayer().equals(activePlayer);
        Room roomInPlay = roomDungeonService.getExactRoomInGame(game.getActualRoomInFloor(), game.getActualFloor(),
                game.getId());

        return playerInGame && game.isInGame() && cardIsOwnedByPlayer
                && card.getCardState().equals(CardState.NOT_PLAYED)
                && !activePlayer.alreadyPlayedACard()
                && roomInPlay.cardIsPlayable(card);
    }

    public void playCard(Game game, Card card) {
        Player activePlayer = playerService.activePlayer();

        if (cardIsPlayable(activePlayer, game, card)) {
            card.setCardState(CardState.RECENTLY_PLAYED);
            cardService.save(card);

            List<Card> cardsPlayedThisTurnByHumans = cardService.findAllCardsPlayedThisTurnByHumans(game.getId());
            Boolean allPlayersPlayedCards = cardsPlayedThisTurnByHumans.size() >= game.humanPlayersAmount();

            if (allPlayersPlayedCards) {
                handleEndOfTurn(game);
            }

        }
    }

    private void handleEndOfTurn(Game game) {
        List<Player> playersNotPlayingCards = game.getPlayers().stream().filter(p -> !p.alreadyPlayedACard())
                .collect(Collectors.toList());
        for (Player player : playersNotPlayingCards) {
            if (player.isABot()) {
                Card card = player.getBotType().chooseCard(player, roomDungeonService.actualFloor(game, player), game);
                card.setCardState(CardState.RECENTLY_PLAYED);
                cardService.save(card);
            } else {
                Random rand = new Random();
                // Takes a random card from all basic cards in hand
                Card card = player.getBasicPlayableCards().get(rand.nextInt(player.getBasicPlayableCards().size()));
                card.setCardState(CardState.RECENTLY_PLAYED);
                cardService.save(card);
            }
        }

        List<Card> cardsPlayedThisTurn = cardService.findAllCardsPlayedThisTurn(game.getId());
        if (cardsPlayedThisTurn.stream().anyMatch(c -> c.getType() == CardType.crystalBall)) {
            cardService.handleCrystallBall(game, cardsPlayedThisTurn);
            if (cardService.findAllCardsPlayedThisTurnByHumans(game.getId()).size() >= game.humanPlayersAmount()) {
                handleEndOfTurn(game);
            } else {
                Calendar currentTime = Calendar.getInstance();
                currentTime.add(Calendar.SECOND, 20);
                Date nextTurnTime = currentTime.getTime();
                game.setNextTurnTime(nextTurnTime);
                save(game);
            }
        } else {
            cardService.handleCardsPlayedThisTurn(game, cardsPlayedThisTurn);
            newTurn(game);
        }
    }

    public void playTorchCard(Player activePlayer, RoomDungeon roomDungeon) {
        Game game = roomDungeon.getGame();
        Boolean playerInGame = game.equals(activePlayer.getGame());
        Boolean roomIsHidden = roomDungeon.getIsHidden() && game.getActualFloor() == roomDungeon.getFloor()
                && game.getActualRoomInFloor() < roomDungeon.getPosition();
        if (playerInGame && game.isInGame() && roomIsHidden && activePlayer.hasATorch()) {
            Card torch = activePlayer.getCards().stream().filter(c -> c.getType() == CardType.torch).findAny().get();
            torchRoomService.revealRoom(roomDungeon, activePlayer);
            cardService.deleteCardById(torch.getId());
        }
    }

    public void newTurn(Game game) {
        Integer oldFloor = game.getActualFloor();
        game.setTurn(game.getTurn() + 1);

        Calendar currentTime = Calendar.getInstance();
        currentTime.add(Calendar.MINUTE, 1);
        Date nextTurnTime = currentTime.getTime();
        game.setNextTurnTime(nextTurnTime);

        if (game.getActualFloor() >= DungeonRaiderConstants.FLOOR_AMOUNT) {
            handleEndOfGame(game);
        } else if (oldFloor == game.getActualFloor()) {
            cardService.newRoomHand(game);
        } else {
            cardService.newFloorHand(game);
        }
    }

    private List<Player> getRichestPlayers(List<Player> players) {
        List<Player> richest = new ArrayList<Player>();
        Integer highestCoinAmount = -1;
        for (Player player : players) {
            Integer coinAmount = player.getCoins();
            if (coinAmount > highestCoinAmount) {
                richest.clear();
                richest.add(player);
                highestCoinAmount = coinAmount;
            } else if (coinAmount == highestCoinAmount) {
                richest.add(player);
            }
        }
        return richest;
    }

    private Player tieBreakPlayers(List<Player> players) {
        Player winner = new Player();
        List<Player> winners = new ArrayList<Player>();
        Integer highestWoundAmount = -1;
        for (Player player : players) {
            Integer woundAmount = player.getWounds();
            if (woundAmount > highestWoundAmount) {
                winners.clear();
                winners.add(player);
                highestWoundAmount = woundAmount;
            } else if (woundAmount == highestWoundAmount) {
                winners.add(player);
            }
        }
        Random random = new Random();
        winner = winners.get(random.nextInt(winners.size()));
        return winner;
    }

    private void handleEndOfGame(Game game) {
        List<Player> posibleWinners = List.copyOf(game.getPlayers());
        Integer topWounds = posibleWinners.stream().mapToInt(p -> p.getWounds()).max().orElse(0);
        List<Player> playersNotWounded = posibleWinners.stream().filter(p -> p.getWounds() < topWounds)
                .collect(Collectors.toList());
        posibleWinners = playersNotWounded.size() == 0 ? posibleWinners : playersNotWounded;

        List<Player> richestPlayers = getRichestPlayers(posibleWinners);

        Player winner = new Player();
        if (richestPlayers.size() > 1) {
            winner = tieBreakPlayers(richestPlayers);
        } else {
            winner = richestPlayers.get(0);
        }
        game.setWinnerPlayer(winner);
        save(game);
    }

    public void addBot(int gameId, BotTypeEnum botTypeEnum) {
        Game game = findGameById(gameId);
        if (game.isInLobby() && game.getMaxPlayers() >= game.getPlayers().size() + 1) {
            playerService.botJoinGame(game, botTypeEnum);
        }
    }

    public void kickPlayer(int gameId, int playerId) {
        Game game = findGameById(gameId);
        if (game.isInLobby() && isActiveUserCreator(game) && playerService.activePlayer().getId() != playerId) {
            playerService.deleteById(playerId);
        }
    }

    public void checkTurnFinished(int gameId) {
        Game game = findGameById(gameId);
        Date nowDate = new Date();
        if (game.isInGame() && game.getNextTurnTime().before(nowDate)) {
            handleEndOfTurn(game);
        }
    }
}
