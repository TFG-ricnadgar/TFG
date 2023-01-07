package etsii.tfg.DungeonRaiders.card;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import etsii.tfg.DungeonRaiders.player.Player;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="cards")
public class Card {

    @ManyToOne(optional = true)
    @JoinColumn(name = "player_id")
    private Player player;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private CardType type;

    private Boolean isUsed;
}
