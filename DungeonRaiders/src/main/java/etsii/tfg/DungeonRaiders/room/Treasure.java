package etsii.tfg.DungeonRaiders.room;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

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
}
