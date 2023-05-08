package etsii.tfg.DungeonRaiders.player;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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
import etsii.tfg.DungeonRaiders.card.CardState;
import etsii.tfg.DungeonRaiders.card.CardType;
import etsii.tfg.DungeonRaiders.game.Game;
import etsii.tfg.DungeonRaiders.model.BaseEntity;
import etsii.tfg.DungeonRaiders.room.Room;
import etsii.tfg.DungeonRaiders.torchRoom.TorchRoom;
import etsii.tfg.DungeonRaiders.user.User;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "players")
public class Player extends BaseEntity {

    @ManyToOne(optional = true)
    @JoinColumn(name = "user_id")
    private User user;

    private String name;

    @ManyToOne(optional = true)
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

    @OneToMany(mappedBy = "player", cascade = { CascadeType.ALL, CascadeType.REMOVE }, orphanRemoval = true)
    private List<Card> cards;

    @OneToMany(mappedBy = "player", cascade = { CascadeType.ALL, CascadeType.REMOVE }, orphanRemoval = true)
    private List<TorchRoom> torchRooms;

    private BotTypeEnum botType = null;

    public Boolean hasATorch() {
        return this.cards.stream().anyMatch(c -> c.getType() == CardType.torch);
    }

    public List<Card> getPlayableCards(Room room) {
        return this.cards.stream().filter(c -> c.getCardState() == CardState.NOT_PLAYED && room.cardIsPlayable(c))
                .collect(Collectors.toList());
    }

    public List<Card> getBasicPlayableCards() {
        return this.cards.stream().filter(c -> c.getCardState() == CardState.NOT_PLAYED && c.isBasic())
                .collect(Collectors.toList());
    }

    public Boolean isABot() {
        return this.user == null;
    }

    public Boolean alreadyPlayedACard() {
        return this.cards.stream().anyMatch(
                c -> c.getCardState().equals(CardState.RECENTLY_PLAYED) || c.getCardState().equals(CardState.REVEALED));
    }

    public String setName() {
        if (isABot()) {
            Random randomNum = new Random();
            Integer randomNumber = randomNum.nextInt(1000);
            return "bot" + randomNumber;
        } else {
            return this.getUser().getUsername();
        }

    }

    public Player() {
    }

    public Player(User user, Game game, Integer coins, Integer wounds, Character character) {
        this.name = user.getUsername();
        this.user = user;
        this.game = game;
        this.coins = coins;
        this.wounds = wounds;
        this.character = character;
    }

    public Player(String name, BotTypeEnum botType, Game game, Integer coins, Integer wounds,
            Character character) {
        this.name = name;
        this.botType = botType;
        this.game = game;
        this.coins = coins;
        this.wounds = wounds;
        this.character = character;
    }

}
