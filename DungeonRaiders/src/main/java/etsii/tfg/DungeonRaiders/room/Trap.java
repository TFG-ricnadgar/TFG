package etsii.tfg.DungeonRaiders.room;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import etsii.tfg.DungeonRaiders.card.Card;
import etsii.tfg.DungeonRaiders.player.PlayerService;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("TRAP")
public class Trap extends Room {

    @NotNull
    private Integer valueTwo;

    @NotNull
    private Integer valueThree;

    @NotNull
    private Integer valueFour;

    @NotNull
    private Integer valueFive;

    @NotNull
    private TrapTarget target;

    @NotNull
    private TrapDamage damageType;

    @Override
    public String getType() {
        return "TRAP";
    }

    @Override
    public void effect(List<Card> cardsPlayedThisTurn, PlayerService playerService) {
        // TODO Auto-generated method stub

    }
}
