package etsii.tfg.DungeonRaiders.card;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends CrudRepository<Card, Integer> {

    @Query("SELECT c FROM Card c WHERE c.player.id = ?1 AND (c.cardState=etsii.tfg.DungeonRaiders.card.CardState.RECENTLY_PLAYED OR c.cardState=etsii.tfg.DungeonRaiders.card.CardState.REVEALED)")
    Card findCardPlayedThisTurn(Integer playerId);

    @Query("SELECT c From Card c WHERE c.player.game.id = ?1 AND (c.cardState=etsii.tfg.DungeonRaiders.card.CardState.RECENTLY_PLAYED OR c.cardState=etsii.tfg.DungeonRaiders.card.CardState.REVEALED)")
    List<Card> findAllCardsPlayedThisTurn(Integer gameId);

    @Query("SELECT c From Card c WHERE c.player.game.id= ?1 AND c.cardState=etsii.tfg.DungeonRaiders.card.CardState.PLAYED")
    List<Card> findAllCardsPlayed(Integer gameId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Card c WHERE c.id = ?1")
    void deleteCardById(Integer id);

    @Query("SELECT c.type From Card c WHERE c.player.game.id=?1 AND c.cardState=etsii.tfg.DungeonRaiders.card.CardState.REVEALED")
    List<CardType> findAllRevealedCards(Integer gameId);

}
