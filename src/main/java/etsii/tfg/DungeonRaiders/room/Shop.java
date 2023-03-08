package etsii.tfg.DungeonRaiders.room;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import etsii.tfg.DungeonRaiders.card.Card;
import etsii.tfg.DungeonRaiders.card.CardService;
import etsii.tfg.DungeonRaiders.game.Game;
import etsii.tfg.DungeonRaiders.player.PlayerService;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("SHOP")
public class Shop extends Room {
    @NotNull
    private ShopItem firstItem;

    @NotNull
    private ShopItem secondItem;

    @NotNull
    private ShopItem thirdItem;

    @NotNull
    private ShopItem fourthItem;

    @NotNull
    private ShopItem fifthItem;

    @Override
    public String getType() {
        return "SHOP";
    }

    @Override
    public void effect(Game game, List<Card> cardsPlayedThisTurn, PlayerService playerService,
            CardService cardService) {
        for (Card card : cardsPlayedThisTurn) {
            switch (card.getType().getValue()) {
                case 1:
                    this.firstItem.buyEffect(card.getPlayer(), playerService, cardService);
                    break;
                case 2:
                    this.secondItem.buyEffect(card.getPlayer(), playerService, cardService);
                    break;
                case 3:
                    this.thirdItem.buyEffect(card.getPlayer(), playerService, cardService);
                    break;
                case 4:
                    this.fourthItem.buyEffect(card.getPlayer(), playerService, cardService);
                    break;
                case 5:
                    this.fifthItem.buyEffect(card.getPlayer(), playerService, cardService);
                    break;

                default:
                    break;
            }
        }
    }
}
