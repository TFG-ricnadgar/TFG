package etsii.tfg.DungeonRaiders.game;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface GameRepository extends CrudRepository<Game, Integer> {

    @Query("SELECT g FROM Game g WHERE g.turn = -1 ")
    List<Game> findAllInLobbyGames();

    @Transactional
    @Modifying
    @Query("DELETE FROM Game g WHERE g.id = ?1")
    void deleteGameById(Integer id);

}
