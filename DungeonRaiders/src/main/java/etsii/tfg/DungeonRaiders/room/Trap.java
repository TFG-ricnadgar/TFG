package etsii.tfg.DungeonRaiders.room;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import etsii.tfg.DungeonRaiders.card.Card;
import etsii.tfg.DungeonRaiders.card.CardService;
import etsii.tfg.DungeonRaiders.game.Game;
import etsii.tfg.DungeonRaiders.player.Player;
import etsii.tfg.DungeonRaiders.player.PlayerService;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("TRAP")
public class Trap extends Room {

    @NotNull
    private Integer valueTwo;

    @NotNull
    private Integer valueThree;

    @NotNull
    private Integer valueFour;

    @NotNull
    private Integer valueFive;

    @NotNull
    private TrapTarget target;

    @NotNull
    private DamageType damageType;

    @Override
    public String getType() {
        return "TRAP";
    }

    private Integer getValue(Integer highestCard) {
        Integer value = 0;
        switch (highestCard) {
            case 2:
                value = this.valueTwo;
                break;
            case 3:
                value = this.valueThree;
                break;
            case 4:
                value = this.valueFour;
                break;
            case 5:
                value = this.valueFive;
                break;
            default:
                break;
        }
        return value;
    };

    @Override
    public void effect(Game game, List<Card> cardsPlayedThisTurn, PlayerService playerService,
            CardService cardService) {
        Integer highestCard = cardsPlayedThisTurn.stream()
                .mapToInt(c -> c.getType().getValue())
                .max()
                .orElseThrow();

        Integer effectAmount = getValue(highestCard);
        if (effectAmount != 0) {
            List<Player> targetPlayers = this.target.getTargetPlayers(game.getPlayers());
            for (Player player : targetPlayers) {
                player = this.damageType.damage(player, effectAmount);
                playerService.save(player);
            }
        }
    }

}
