package etsii.tfg.DungeonRaiders.room;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import etsii.tfg.DungeonRaiders.card.CardType;
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
}
