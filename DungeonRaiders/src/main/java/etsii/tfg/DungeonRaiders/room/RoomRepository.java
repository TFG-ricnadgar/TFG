package etsii.tfg.DungeonRaiders.room;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends CrudRepository<Room, Integer> {

    @Query(value = "SELECT * FROM ROOMS WHERE room_type = ?1", nativeQuery = true)
    List<Room> findAllRoomsOfType(String type);

    @Query("SELECT r FROM Room r")
    List<Room> findAllRooms();

}
