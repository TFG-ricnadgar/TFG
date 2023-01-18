package etsii.tfg.DungeonRaiders.game;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends CrudRepository<Game, Integer> {

    @Query("SELECT g FROM Game g WHERE g.turn = 0 ")
    List<Game> findAllInLobbyGames();

}
