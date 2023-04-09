package etsii.tfg.DungeonRaiders.card;

import java.util.ArrayList;
import java.util.List;

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

    public void deleteCard(Card card) {
        cardRepository.delete(card);
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

    public Card findCardPlayedInTurn(Integer playerId) {
        return cardRepository.findCardPlayedThisTurn(playerId);
    }

    public List<Card> findAllCardsPlayedThisTurn(Integer gameId) {
        return cardRepository.findAllCardsPlayedThisTurn(gameId);
    }

    private List<Card> findAllCardsPlayed(Integer gameId) {
        return cardRepository.findAllCardsPlayed(gameId);
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

    private void setStateBasicCards(List<Card> cards, CardState cardState) {
        for (Card card : cards) {
            if (card.isBasic()) {
                card.setCardState(cardState);
                save(card);
            } else {
                deleteCard(card);
            }
        }
    }

    public void newRoomHand(Game game) {
        List<Card> gameCards = findAllCardsPlayedThisTurn(game.getId());
        setStateBasicCards(gameCards, CardState.PLAYED);
    }

    public void newFloorHand(Game game) {
        List<Card> gameCards = findAllCardsPlayed(game.getId());
        setStateBasicCards(gameCards, CardState.NOT_PLAYED);
    }

    public void handleCrystallBall(Game game, List<Card> cardsPlayedThisTurn) {
        for (Card card : cardsPlayedThisTurn) {
            if (card.getType().equals(CardType.crystalBall)) {
                deleteCard(card);
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
