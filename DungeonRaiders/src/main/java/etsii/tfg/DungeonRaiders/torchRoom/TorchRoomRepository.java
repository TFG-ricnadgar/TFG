package etsii.tfg.DungeonRaiders.torchRoom;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import etsii.tfg.DungeonRaiders.player.Player;
import etsii.tfg.DungeonRaiders.roomDungeon.RoomDungeon;

@Repository
public interface TorchRoomRepository extends CrudRepository<TorchRoom, Integer> {

    @Query("SELECT tr.roomDungeon FROM TorchRoom tr WHERE tr.roomDungeon.floor = ?1 AND tr.player = ?2")
    List<RoomDungeon> findAllRoomDungeonByPlayerInFloor(Integer actualFloor, Player player);

}
