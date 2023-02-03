package etsii.tfg.DungeonRaiders.player;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import etsii.tfg.DungeonRaiders.card.Card;
import etsii.tfg.DungeonRaiders.game.Game;
import etsii.tfg.DungeonRaiders.model.BaseEntity;
import etsii.tfg.DungeonRaiders.user.User;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "players")
public class Player extends BaseEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "game_id")
    private Game game;

    @NotNull
    @Min(0)
    private Integer coins;

    @NotNull
    @Min(0)
    private Integer wounds;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "character_name")
    private Character character;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Card> cards;
}
