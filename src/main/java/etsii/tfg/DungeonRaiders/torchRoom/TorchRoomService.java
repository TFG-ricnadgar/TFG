package etsii.tfg.DungeonRaiders.torchRoom;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import etsii.tfg.DungeonRaiders.player.Player;
import etsii.tfg.DungeonRaiders.roomDungeon.RoomDungeon;

@Service
public class TorchRoomService {
    @Autowired
    private TorchRoomRepository torchRoomRepository;

    public void revealRoom(RoomDungeon roomDungeon, Player player) {
        TorchRoom newTorchRoom = new TorchRoom(roomDungeon, player);
        torchRoomRepository.save(newTorchRoom);
    }

    public List<RoomDungeon> findAllRoomDungeonByPlayerInFloor(Integer actualFloor, Player player) {
        return torchRoomRepository.findAllRoomDungeonByPlayerInFloor(actualFloor, player);
    }
}
