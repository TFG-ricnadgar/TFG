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
@DiscriminatorValue("FINAL_BOSS")
public class FinalBoss extends Room {

    @NotNull
    private DamageType damageType;

    @NotNull
    private Integer damageAmount;

    private CardType escapeCard;

    @NotNull
    private Integer healthThree;

    @NotNull
    private Integer healthFour;

    @NotNull
    private Integer healthFive;

    @NotNull
    private Integer topCardCoinReward;

    @Override
    public String getType() {

        return "FINAL_BOSS";
    }

    public Integer getHealth(int playersAmount) {
        Integer health = 0;
        switch (playersAmount) {
            case 3:
                health = this.healthThree;
                break;
            case 4:
                health = this.healthFour;
                break;
            case 5:
                health = this.healthFive;
                break;
            default:
                health = 99;
                break;
        }
        return health;
    }

    @Override
    public void effect(Game game, List<Card> cardsPlayedThisTurn, PlayerService playerService,
            CardService cardService) {
        Integer totalDamage = 0;
        Integer lowestValue = 99;
        Integer highestValue = -1;
        List<Player> playersWithLowestValues = new ArrayList<Player>();
        List<Player> playersWithHighestValues = new ArrayList<Player>();
        for (Card card : cardsPlayedThisTurn) {
            Integer cardValue = card.isBasic() || card.getType().equals(CardType.sword) ? card.getType().getValue() : 0;
            totalDamage += cardValue;

            if (card.getType().equals(this.escapeCard)) {
                continue;
            } else if (cardValue > highestValue) {
                playersWithHighestValues.clear();
                playersWithHighestValues.add(card.getPlayer());
                highestValue = cardValue;
            } else if (cardValue == highestValue) {
                playersWithHighestValues.add(card.getPlayer());
            }

            if (cardValue < lowestValue) {
                playersWithLowestValues.clear();
                playersWithLowestValues.add(card.getPlayer());
                lowestValue = cardValue;
            } else if (cardValue == lowestValue) {
                playersWithLowestValues.add(card.getPlayer());
            }
        }

        if (this.getHealth(cardsPlayedThisTurn.size()) > totalDamage) {
            for (Player player : playersWithLowestValues) {
                player = this.damageType.damage(player, this.damageAmount);
                playerService.save(player);
            }
        } else {
            for (Player player : playersWithHighestValues) {
                player.setCoins(player.getCoins() + this.topCardCoinReward);
            }
        }
    }

    @Override
    public Boolean cardIsPlayable(Card card) {
        return card.isBasic() || card.getType().equals(CardType.sword) || card.getType().equals(this.escapeCard)
                || card.getType().equals(CardType.crystalBall);
    }
}
