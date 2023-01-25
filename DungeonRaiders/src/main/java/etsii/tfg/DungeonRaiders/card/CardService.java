package etsii.tfg.DungeonRaiders.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import etsii.tfg.DungeonRaiders.game.Game;
import etsii.tfg.DungeonRaiders.player.Player;

@Service
public class CardService {

    private CardRepository cardRepository;

    @Autowired
    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
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

    private void save(Card card) {
        cardRepository.save(card);
    }

    private void saveAll(List<Card> listCard) {
        cardRepository.saveAll(listCard);
    }

}
