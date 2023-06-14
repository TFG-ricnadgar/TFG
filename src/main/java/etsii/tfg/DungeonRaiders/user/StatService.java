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

    public Integer avgGameDurationByUserId(Integer userId) {
        return statRepository.gamesAvgDurationByUser(userId);
    }

    public Integer totalGameDurationByUserId(Integer userId) {
        return statRepository.gamesTotalDurationByUser(userId);
    }

    public Integer totalCoins() {
        return statRepository.totalCoins();
    }

    public Integer gamesPlayedWithCaracter(Character characterPlayer) {
        return statRepository.gamesPlayedWithCharacter(characterPlayer);
    }

    public Integer totalWounds() {
        return statRepository.totalWounds();
    }

    public Integer gamesMaxCoins() {
        return statRepository.gamesMaxCoins();
    }

    public Integer gamesMaxWounds() {
        return statRepository.gamesMaxWounds();
    }

    public Integer gamesPlayed() {
        return statRepository.gamesPlayed();
    }

    public Integer avgGameDuration() {
        return statRepository.gamesAvgDuration();
    }

    public Integer gamesWonWithCaracter(Character characterPlayer) {
        return statRepository.gamesWonByCharacter(characterPlayer);
    }

    public Integer gamesWonWithCaracterByUserId(Integer userId, Character characterPlayer) {
        return statRepository.gamesWonByCharacterByUserId(userId, characterPlayer);
    }

    public Integer totalGameDuration() {
        return statRepository.totalGameDuration();
    }
}
