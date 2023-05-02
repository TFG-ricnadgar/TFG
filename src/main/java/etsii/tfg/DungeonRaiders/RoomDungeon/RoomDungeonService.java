package etsii.tfg.DungeonRaiders.roomDungeon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import etsii.tfg.DungeonRaiders.game.Game;
import etsii.tfg.DungeonRaiders.player.Player;
import etsii.tfg.DungeonRaiders.room.Room;
import etsii.tfg.DungeonRaiders.room.RoomService;
import etsii.tfg.DungeonRaiders.torchRoom.TorchRoomService;
import etsii.tfg.DungeonRaiders.util.DungeonRaiderConstants;

@Service
public class RoomDungeonService {

    private RoomDungeonRepository roomDungeonRepository;
    private RoomService roomService;
    private TorchRoomService torchRoomService;

    @Autowired
    public RoomDungeonService(RoomDungeonRepository roomDungeonRepository, RoomService roomService,
            TorchRoomService torchRoomService) {
        this.roomDungeonRepository = roomDungeonRepository;
        this.roomService = roomService;
        this.torchRoomService = torchRoomService;
    }

    public void generateDungeon(Game game) {
        Random random = new Random();
        List<Room> normalRooms = roomService.findAllNormalRooms();
        List<RoomDungeon> dungeon = new ArrayList<>();
        List<Room> finalBossRooms = roomService.findAllFinalBossRooms();
        for (int i = 0; i < DungeonRaiderConstants.FLOOR_AMOUNT; i++) {
            List<Integer> hiddenRooms = randomListOfHiddenRoomsInFloor();
            for (int j = 0; j < DungeonRaiderConstants.ROOMS_PER_FLOOR_AMOUNT; j++) {
                Boolean isHidden = hiddenRooms.contains(j);

                if (j == DungeonRaiderConstants.ROOMS_PER_FLOOR_AMOUNT - 1
                        && i == DungeonRaiderConstants.FLOOR_AMOUNT - 1) {
                    Room randomFinalBoss = finalBossRooms.get(random.nextInt(finalBossRooms.size()));
                    dungeon.add(new RoomDungeon(game, randomFinalBoss, isHidden, i, j));
                } else {
                    Room randomRoom = normalRooms.get(random.nextInt(normalRooms.size()));
                    normalRooms.remove(randomRoom);
                    dungeon.add(new RoomDungeon(game, randomRoom, isHidden, i, j));
                }

            }
        }
        saveAll(dungeon);
    }

    private void saveAll(List<RoomDungeon> dungeon) {
        roomDungeonRepository.saveAll(dungeon);
    }

    private List<Integer> randomListOfHiddenRoomsInFloor() {
        List<Integer> hiddenRooms = IntStream.range(0, DungeonRaiderConstants.ROOMS_PER_FLOOR_AMOUNT).boxed()
                .collect(Collectors.toList());
        Random random = new Random();
        Integer amountOfHiddenRoomsInFloor = DungeonRaiderConstants.MIN_ROOMS_HIDDEN_PER_FLOOR + random.nextInt(3);
        for (int i = 0; i < amountOfHiddenRoomsInFloor; i++) {
            hiddenRooms.remove(random.nextInt(hiddenRooms.size()));
        }
        return hiddenRooms;
    }

    public List<RoomDungeon> actualFloor(Game game, Player player) {
        List<RoomDungeon> actualFloor = List
                .copyOf(roomDungeonRepository.findAllByGameAndFloor(game.getId(), game.getActualFloor()));
        List<RoomDungeon> revealedRooms = torchRoomService.findAllRoomDungeonByPlayerInFloor(game.getActualFloor(),
                player);
        for (RoomDungeon room : actualFloor) {
            if (room.getIsHidden() && revealedRooms.contains(room)) {
                room.setIsHidden(false);
            }
        }

        return actualFloor;
    }

    public Room getExactRoomInGame(Integer actualRoomInFloor, Integer actualFloor, Integer gameId) {
        return roomDungeonRepository.getExactRoomInGame(actualRoomInFloor, actualFloor, gameId);
    }

    public RoomDungeon findRoomDungeonById(int roomDungeonId) {
        return roomDungeonRepository.findById(roomDungeonId).get();
    }
}
