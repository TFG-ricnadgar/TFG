package etsii.tfg.DungeonRaiders.room;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Enemy extends Room {

    @NotNull
    private Integer healthThree;

    @NotNull
    private Integer healthFour;

    @NotNull
    private Integer healthFive;

    @NotNull
    private Integer damage;
}
