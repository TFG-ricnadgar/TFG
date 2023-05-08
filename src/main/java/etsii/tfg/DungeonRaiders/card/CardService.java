package etsii.tfg.DungeonRaiders.card;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import etsii.tfg.DungeonRaiders.game.Game;
import etsii.tfg.DungeonRaiders.player.Player;
import etsii.tfg.DungeonRaiders.player.PlayerService;
import etsii.tfg.DungeonRaiders.room.Room;
import etsii.tfg.DungeonRaiders.roomDungeon.RoomDungeonService;

@Service
public class CardService {

    private CardRepository cardRepository;
    private RoomDungeonService roomDungeonService;
    private PlayerService playerService;

    @Autowired
    public CardService(CardRepository cardRepository, RoomDungeonService roomDungeonService,
            PlayerService playerService) {
        this.cardRepository = cardRepository;
        this.roomDungeonService = roomDungeonService;
        this.playerService = playerService;
    }

    public void save(Card card) {
        cardRepository.save(card);
    }

    private void saveAll(List<Card> listCard) {
        cardRepository.saveAll(listCard);
    }

    public Card findCardById(int cardId) {
        return cardRepository.findById(cardId).get();
    }

    public void deleteCardById(Integer id) {
        cardRepository.deleteCardById(id);
    }

    public List<Card> findAllCardsPlayedThisTurn(Integer gameId) {
        return cardRepository.findAllCardsPlayedThisTurn(gameId);
    }

    public List<Card> findAllCardsPlayedThisTurnByHumans(Integer gameId) {
        return findAllCardsPlayedThisTurn(gameId).stream().filter(c -> !c.getPlayer().isABot())
                .collect(Collectors.toList());
    }

    private List<Card> findAllCardsPlayedEver(Integer gameId) {
        return cardRepository.findAllCardsPlayedEver(gameId);
    }

    public void givePlayersStartingGameHand(List<Player> players) {
        for (Player player : players) {
            List<Card> playerHand = new ArrayList<Card>();
            Card newCardOne = new Card(CardType.one, player);
            Card newCardTwo = new Card(CardType.two, player);
            Card newCardThree = new Card(CardType.three, player);
            Card newCardFour = new Card(CardType.four, player);
            Card newCardFive = new Card(CardType.five, player);
            playerHand.addAll(List.of(newCardOne, newCardTwo, newCardThree, newCardFour, newCardFive));

            List<CardType> startingHandCharacter = player.getCharacter().getInitialCards();
            for (CardType startingCardType : startingHandCharacter) {
                Card newCard = new Card(startingCardType, player);
                playerHand.add(newCard);
            }
            saveAll(playerHand);
        }
    }

    public void handleCardsPlayedThisTurn(Game game, List<Card> cardsPlayedThisTurn) {
        Room room = roomDungeonService.getExactRoomInGame(game.getActualRoomInFloor(), game.getActualFloor(),
                game.getId());
        room.effect(game, cardsPlayedThisTurn, playerService, this);
    }

    public void newRoomHand(Game game) {
        List<Card> gameCards = findAllCardsPlayedThisTurn(game.getId());
        for (Card card : gameCards) {
            card.setCardState(CardState.PLAYED);
            save(card);
        }

    }

    public void newFloorHand(Game game) {
        List<Card> gameCards = findAllCardsPlayedEver(game.getId());
        for (Card card : gameCards) {
            if (card.isBasic()) {
                card.setCardState(CardState.NOT_PLAYED);
                save(card);
            } else {
                deleteCardById(card.getId());
            }
        }
    }

    public void handleCrystallBall(Game game, List<Card> cardsPlayedThisTurn) {
        for (Card card : cardsPlayedThisTurn) {
            if (card.getType().equals(CardType.crystalBall)) {
                deleteCardById(card.getId());
            } else {
                card.setCardState(CardState.REVEALED);
                save(card);
            }
        }
    }

    public List<CardType> revealedCards(Integer gameId) {
        return cardRepository.findAllRevealedCards(gameId);
    }

}
