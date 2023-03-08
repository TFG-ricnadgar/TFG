package etsii.tfg.DungeonRaiders.torchRoom;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import etsii.tfg.DungeonRaiders.model.BaseEntity;
import etsii.tfg.DungeonRaiders.player.Player;
import etsii.tfg.DungeonRaiders.roomDungeon.RoomDungeon;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class TorchRoom extends BaseEntity {

    public TorchRoom() {
    }

    public TorchRoom(RoomDungeon roomDungeon, Player player) {
        this.roomDungeon = roomDungeon;
        this.player = player;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "room_dungeon_id")
    private RoomDungeon roomDungeon;

    @ManyToOne(optional = false)
    @JoinColumn(name = "player_id")
    private Player player;
}
