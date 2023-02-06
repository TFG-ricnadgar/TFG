package etsii.tfg.DungeonRaiders.RoomDungeon;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomDungeonRepository extends CrudRepository<RoomDungeon, Integer> {

    @Query("SELECT rd FROM RoomDungeon rd WHERE rd.game.id = ?1 AND rd.floor = ?2")
    List<RoomDungeon> findAllByGameAndFloor(Integer id, Integer actualFloor);

}
