package etsii.tfg.DungeonRaiders.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import etsii.tfg.DungeonRaiders.player.Character;

@Repository
public interface StatRepository extends UserRepository {

    @Query("SELECT COUNT(g) FROM Game g WHERE g.winnerPlayer.user.id=?1")
    Integer gamesWonByUser(int userId);

    @Query("SELECT COUNT(p) FROM Player p WHERE p.user.id=?1 AND p.game.winnerPlayer !=null")
    Integer gamesPlayedByUser(int userId);

    @Query("SELECT COUNT(p) FROM Player p WHERE p.user.id=?1 AND p.character=?2 AND p.game.winnerPlayer !=null")
    Integer gamesPlayedWithCharacterByUser(Integer userId, Character characterPlayer);

    @Query("SELECT SUM(p.coins) FROM Player p WHERE p.user.id=?1 AND p.game.winnerPlayer !=null")
    Integer totalCoinsByUser(Integer userId);

    @Query("SELECT SUM(p.wounds) FROM Player p WHERE p.user.id=?1 AND p.game.winnerPlayer !=null")
    Integer totalWoundsByUser(Integer userId);

    @Query("SELECT MAX(p.coins) FROM Player p WHERE p.user.id=?1 AND p.game.winnerPlayer !=null")
    Integer gamesMaxCoinsByUser(Integer userId);

    @Query("SELECT MAX(p.wounds) FROM Player p WHERE p.user.id=?1 AND p.game.winnerPlayer !=null")
    Integer gamesMaxWoundsByUser(Integer userId);

    @Query("SELECT AVG(p.game.durationInSeconds) From Player p where p.user.id=?1 AND p.game.winnerPlayer !=null")
    Integer gamesAvgDurationByUser(Integer userId);

    @Query("SELECT SUM(p.game.durationInSeconds) From Player p where p.user.id=?1 AND p.game.winnerPlayer !=null")
    Integer gamesTotalDurationByUser(Integer userId);

    @Query("SELECT COUNT(p) FROM Player p WHERE p.character=?1 AND p.game.winnerPlayer !=null AND p.user.id!=null")
    Integer gamesPlayedWithCharacter(Character characterPlayer);

    @Query("SELECT SUM(p.coins) FROM Player p WHERE p.game.winnerPlayer !=null AND p.user.id!=null")
    Integer totalCoins();

    @Query("SELECT SUM(p.wounds) FROM Player p WHERE p.game.winnerPlayer !=null AND p.user.id!=null")
    Integer totalWounds();

    @Query("SELECT MAX(p.coins) FROM Player p WHERE p.game.winnerPlayer !=null AND p.user.id!=null")
    Integer gamesMaxCoins();

    @Query("SELECT MAX(p.wounds) FROM Player p WHERE p.game.winnerPlayer !=null AND p.user.id!=null")
    Integer gamesMaxWounds();

    @Query("SELECT AVG(g.durationInSeconds) From Game g where g.winnerPlayer !=null")
    Integer gamesAvgDuration();

    @Query("SELECT COUNT(g) FROM Game g WHERE g.winnerPlayer!=null")
    Integer gamesPlayed();

    @Query("SELECT COUNT(p) FROM Player p WHERE p.character=?1 AND p.game.winnerPlayer = p AND p.user.id!=null")
    Integer gamesWonByCharacter(Character characterPlayer);

    @Query("SELECT COUNT(p) FROM Player p WHERE p.character=?2 AND p.game.winnerPlayer = p AND p.user.id=?1")
    Integer gamesWonByCharacterByUserId(Integer userId, Character characterPlayer);

    @Query("SELECT SUM(p.game.durationInSeconds) From Player p where p.user!=null AND p.game.winnerPlayer !=null")
    Integer totalGameDuration();

}
