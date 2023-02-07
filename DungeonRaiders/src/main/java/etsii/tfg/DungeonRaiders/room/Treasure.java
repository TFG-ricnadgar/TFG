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
    public void effect(List<Card> cardsPlayedThisTurn, PlayerService playerService) {
        // TODO Auto-generated method stub

    }
}
