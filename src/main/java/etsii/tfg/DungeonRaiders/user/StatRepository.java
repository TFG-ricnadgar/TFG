package etsii.tfg.DungeonRaiders.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import etsii.tfg.DungeonRaiders.player.Character;

@Repository
public interface StatRepository extends UserRepository {

    @Query("SELECT COUNT(g) FROM Game g WHERE g.winnerPlayer.user.id=?1")
    Integer gamesWonByUser(int userId);

    @Query("SELECT COUNT(p) FROM Player p WHERE p.user.id=?1")
    Integer gamesPlayedByUser(int userId);

    @Query("SELECT COUNT(p) FROM Player p WHERE p.user.id=?1 AND p.character=?2")
    Integer gamesPlayedWithCharacterByUser(Integer userId, Character characterPlayer);

    @Query("SELECT SUM(p.coins) FROM Player p WHERE p.user.id=?1")
    Integer totalCoinsByUser(Integer userId);

    @Query("SELECT SUM(p.wounds) FROM Player p WHERE p.user.id=?1")
    Integer totalWoundsByUser(Integer userId);

    @Query("SELECT MAX(p.coins) FROM Player p WHERE p.user.id=?1")
    Integer gamesMaxCoinsByUser(Integer userId);

    @Query("SELECT MAX(p.wounds) FROM Player p WHERE p.user.id=?1")
    Integer gamesMaxWoundsByUser(Integer userId);

}
