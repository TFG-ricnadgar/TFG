package etsii.tfg.DungeonRaiders.card;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends CrudRepository<Card, Integer> {

    @Query("SELECT c FROM Card c WHERE c.player.id = ?1 AND c.isRecentlyUsed=true")
    Card findCardPlayedThisTurn(Integer playerId);

    @Query("SELECT c From Card c WHERE c.player.game.id = ?1 AND c.isRecentlyUsed=true")
    List<Card> findAllCardsPlayedThisTurn(Integer gameId);

    @Query("SELECT c From Card c WHERE c.player.game.id= ?1 AND c.isUsed=true")
    List<Card> findAllCardsPlayed(Integer gameId);

}
