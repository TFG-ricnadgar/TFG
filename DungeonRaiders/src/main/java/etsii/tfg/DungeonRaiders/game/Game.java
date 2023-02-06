package etsii.tfg.DungeonRaiders.game;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import etsii.tfg.DungeonRaiders.model.BaseEntity;
import etsii.tfg.DungeonRaiders.player.Player;
import etsii.tfg.DungeonRaiders.util.DungeonRaiderConstants;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "games")
public class Game extends BaseEntity {

    @Length(min = 3, max = 20)
    @NotBlank
    private String name;

    @NotBlank
    private String creatorUsername;

    private String winnerUsername;

    @NotNull
    @Min(3)
    @Max(5)
    private Integer maxPlayers = 5;

    @NotNull
    private Integer turn = -1;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Player> players;

    public Integer getActualFloor() {
        return Math.floorDiv(this.turn, DungeonRaiderConstants.ROOMS_PER_FLOOR_AMOUNT);
    }

    public Integer getActualRoomInFloor() {
        return this.turn % DungeonRaiderConstants.ROOMS_PER_FLOOR_AMOUNT;
    }

    public Boolean isActive() {
        return winnerUsername == null;
    }

    public Boolean isInLobby() {
        return this.turn == -1;
    }

    public Boolean isInGame() {
        return isActive() && !isInLobby();
    }

    public Boolean isFull() {
        return maxPlayers == players.size();
    }

    public Boolean hasEnoughPlayersToStart() {
        return players.size() >= 3;
    }

}
