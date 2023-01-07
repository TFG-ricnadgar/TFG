package etsii.tfg.DungeonRaiders.room;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import etsii.tfg.DungeonRaiders.game.Game;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="rooms")
public class Room {

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


}
