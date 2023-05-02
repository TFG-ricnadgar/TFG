package etsii.tfg.DungeonRaiders.roomDungeon;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import etsii.tfg.DungeonRaiders.room.Room;

@Repository
public interface RoomDungeonRepository extends JpaRepository<RoomDungeon, Integer> {

    @Query("SELECT rd FROM RoomDungeon rd WHERE rd.game.id = ?1 AND rd.floor = ?2")
    List<RoomDungeon> findAllByGameAndFloor(Integer id, Integer actualFloor);

    @Query("SELECT rd.room FROM RoomDungeon rd WHERE rd.position = ?1 AND rd.floor = ?2 AND rd.game.id = ?3")
    Room getExactRoomInGame(Integer actualRoomInFloor, Integer actualFloor, Integer gameId);

}
