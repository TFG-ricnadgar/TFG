package etsii.tfg.DungeonRaiders.room;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import etsii.tfg.DungeonRaiders.game.Game;
import etsii.tfg.DungeonRaiders.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "room_type", discriminatorType = DiscriminatorType.STRING)
public class Room extends BaseEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "game_id")
    private Game game;

    @NotNull
    private Boolean isHidden;

    @NotNull
    @Min(0)
    @Max(5)
    private Integer position;

    @NotNull
    @Min(0)
    @Max(5)
    private Integer floor;

    @NotNull
    private String name;

}
