package etsii.tfg.DungeonRaiders.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import etsii.tfg.DungeonRaiders.player.Character;

@Service
public class StatService {
    @Autowired
    private StatRepository statRepository;

    public Integer gamesPlayedByUserId(int userId) {
        return statRepository.gamesPlayedByUser(userId);

    }

    public Integer gamesWonByUserId(Integer userId) {
        return statRepository.gamesWonByUser(userId);
    }

    public Integer gamesPlayedWithCaracterByUserId(Integer userId, Character characterPlayer) {
        return statRepository.gamesPlayedWithCharacterByUser(userId, characterPlayer);
    }

    public Integer totalCoinsByUserId(Integer userId) {
        return statRepository.totalCoinsByUser(userId);
    }

    public Integer totalWoundsByUserId(Integer userId) {
        return statRepository.totalWoundsByUser(userId);
    }

    public Integer gamesMaxCoinsByUserId(Integer userId) {
        return statRepository.gamesMaxCoinsByUser(userId);
    }

    public Integer gamesMaxWoundsUserId(Integer userId) {
        return statRepository.gamesMaxWoundsByUser(userId);
    }
}
