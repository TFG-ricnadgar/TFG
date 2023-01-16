package etsii.tfg.DungeonRaiders.game;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {

    // @Query("SELECT g FROM Game g WHERE g.turn == 0 ")
    @Query("SELECT g FROM Game g")
    List<Game> findAllInLobbyGames();

}
