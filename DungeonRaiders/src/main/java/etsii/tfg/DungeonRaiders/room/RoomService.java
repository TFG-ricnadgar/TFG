package etsii.tfg.DungeonRaiders.room;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {
    private RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> findAllNormalRooms() {
        List<Room> finalBossRooms = findAllFinalBossRooms();
        List<Room> allRooms = findAllRooms();
        allRooms.removeAll(finalBossRooms);
        return allRooms;
    }

    public List<Room> findAllFinalBossRooms() {
        return roomRepository.findAllRoomsOfType("FINAL_BOSS");
    }

    public List<Room> findAllRooms() {
        return roomRepository.findAllRooms();
    }

    public Room findRoomById(Integer id) {
        return roomRepository.findById(id).orElse(null);
    }

}
