package etsii.tfg.DungeonRaiders.room;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
public class Room extends BaseEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "game_id")
    private Game game;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private RoomType type;

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
