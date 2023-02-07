package etsii.tfg.DungeonRaiders.room;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import etsii.tfg.DungeonRaiders.card.Card;
import etsii.tfg.DungeonRaiders.card.CardType;
import etsii.tfg.DungeonRaiders.player.Player;
import etsii.tfg.DungeonRaiders.player.PlayerService;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("ENEMY")
public class Enemy extends Room {

    @NotNull
    private Integer healthThree;

    @NotNull
    private Integer healthFour;

    @NotNull
    private Integer healthFive;

    @NotNull
    private Integer damage;

    @Override
    public String getType() {
        return "ENEMY";
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
    public void effect(List<Card> cardsPlayedThisTurn, PlayerService playerService) {
        Integer totalDamage = 0;
        Integer lowestValue = 99;
        List<Player> playersWithLowestValues = new ArrayList<Player>();
        for (Card card : cardsPlayedThisTurn) {
            Integer cardValue = card.getType().getValue();
            totalDamage += cardValue;
            if (cardValue < lowestValue) {
                playersWithLowestValues.add(card.getPlayer());
                lowestValue = cardValue;
            } else if (cardValue == lowestValue) {
                playersWithLowestValues.clear();
                playersWithLowestValues.add(card.getPlayer());
            }
        }
        if (this.getHealth(cardsPlayedThisTurn.size()) > totalDamage) {
            for (Player player : playersWithLowestValues) {
                player.setWounds(player.getWounds() + this.getDamage());
                playerService.save(player);
            }
        }
    }
}
