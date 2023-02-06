package etsii.tfg.DungeonRaiders.room;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

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
}
