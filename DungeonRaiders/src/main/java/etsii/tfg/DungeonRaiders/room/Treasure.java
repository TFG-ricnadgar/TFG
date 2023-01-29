package etsii.tfg.DungeonRaiders.room;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Treasure extends Room {

    @NotNull
    private Integer firstChestCoins;

    @NotNull
    private Integer secondChestCoins;
}
