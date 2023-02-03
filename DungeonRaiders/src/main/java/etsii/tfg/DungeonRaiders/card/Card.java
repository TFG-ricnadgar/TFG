package etsii.tfg.DungeonRaiders.card;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import etsii.tfg.DungeonRaiders.model.BaseEntity;
import etsii.tfg.DungeonRaiders.player.Player;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "cards")
public class Card extends BaseEntity {

    public Card() {
    }

    public Card(CardType type, Player player) {
        this.type = type;
        this.player = player;
        this.isUsed = false;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "player_id")
    private Player player;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private CardType type;

    @NotNull
    private Boolean isUsed = false;

}
