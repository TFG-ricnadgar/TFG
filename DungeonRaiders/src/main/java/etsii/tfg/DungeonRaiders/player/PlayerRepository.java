package etsii.tfg.DungeonRaiders.player;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import etsii.tfg.DungeonRaiders.game.Game;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Integer> {

    @Query("SELECT p.game FROM Player p WHERE p.game.winnerUsername IS NULL AND p.user.username = ?1")
    Game activeGameByUsername(String authenticatedUsername);

    @Transactional
    @Modifying
    @Query("DELETE FROM Player p WHERE p.id = ?1")
    void deletePlayerById(Integer id);

    @Query("SELECT p FROM Player p WHERE p.game.winnerUsername IS NULL AND p.user.username = ?1")
    Player activePlayerByUsername(String authenticatedUsername);
}
