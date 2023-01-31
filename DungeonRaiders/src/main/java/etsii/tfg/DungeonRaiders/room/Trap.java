package etsii.tfg.DungeonRaiders.room;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("TRAP")
public class Trap extends Room {
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
}
