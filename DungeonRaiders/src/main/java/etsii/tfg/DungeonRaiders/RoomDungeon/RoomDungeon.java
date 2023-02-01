package etsii.tfg.DungeonRaiders.RoomDungeon;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import etsii.tfg.DungeonRaiders.game.Game;
import etsii.tfg.DungeonRaiders.model.BaseEntity;
import etsii.tfg.DungeonRaiders.room.Room;

@Entity
public class RoomDungeon extends BaseEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "game_id")
    private Game game;

    @ManyToOne(optional = false)
    @JoinColumn(name = "room_id")
    private Room room;

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

    public RoomDungeon(Game game, Room room, Boolean isHidden, Integer position, Integer floor) {
        this.game = game;
        this.room = room;
        this.isHidden = isHidden;
        this.position = position;
        this.floor = floor;
    }

}
