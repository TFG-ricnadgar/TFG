package etsii.tfg.DungeonRaiders.room;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import etsii.tfg.DungeonRaiders.card.Card;
import etsii.tfg.DungeonRaiders.card.CardService;
import etsii.tfg.DungeonRaiders.card.CardType;
import etsii.tfg.DungeonRaiders.game.Game;
import etsii.tfg.DungeonRaiders.player.Player;
import etsii.tfg.DungeonRaiders.player.PlayerService;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("TREASURE")
public class Treasure extends Room {

    @NotNull
    private Integer firstChestCoins;

    @NotNull
    private Integer secondChestCoins;

    @Override
    public String getType() {
        return "TREASURE";
    }

    @Override
    public void effect(Game game, List<Card> cardsPlayedThisTurn, PlayerService playerService,
            CardService cardService) {
        List<Player> playersWithHighestValues = new ArrayList<Player>();
        List<Player> playersWithSecondHighestValues = new ArrayList<Player>();
        Integer highestValue = -2;
        Integer secondHighestValue = -1;
        for (Card card : cardsPlayedThisTurn) {
            Integer cardValue = card.getType().getValue();
            if (cardValue > highestValue) {
                playersWithSecondHighestValues = new ArrayList<Player>(playersWithHighestValues);
                playersWithHighestValues.clear();
                playersWithHighestValues.add(card.getPlayer());

                secondHighestValue = highestValue;
                highestValue = cardValue;
            } else if (cardValue == highestValue) {
                playersWithHighestValues.add(card.getPlayer());
            } else if (cardValue < highestValue && cardValue > secondHighestValue) {
                playersWithSecondHighestValues.clear();
                playersWithSecondHighestValues.add(card.getPlayer());
                secondHighestValue = cardValue;

            } else if (cardValue == secondHighestValue) {
                playersWithSecondHighestValues.add(card.getPlayer());
            }
        }

        for (Player player : playersWithHighestValues) {
            Integer coinsForEachPlayer = Math.floorDiv(this.firstChestCoins, playersWithHighestValues.size());
            player.setCoins(player.getCoins() + coinsForEachPlayer);
            playerService.save(player);
        }

        if (this.secondChestCoins > 0) {
            for (Player player : playersWithSecondHighestValues) {
                Integer coinsForEachPlayer = Math.floorDiv(this.secondChestCoins,
                        playersWithSecondHighestValues.size());
                player.setCoins(player.getCoins() + coinsForEachPlayer);
                playerService.save(player);
            }
        }

    }

    @Override
    public Boolean cardIsPlayable(Card card) {
        return card.isBasic() || card.getType().equals(CardType.key) || card.getType().equals(CardType.crystalBall);
    }
}
