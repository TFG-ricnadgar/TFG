package etsii.tfg.DungeonRaiders.room;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import etsii.tfg.DungeonRaiders.card.Card;
import etsii.tfg.DungeonRaiders.card.CardType;
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
    public void effect(List<Card> cardsPlayedThisTurn, PlayerService playerService) {
        // TODO Auto-generated method stub

    }
}
