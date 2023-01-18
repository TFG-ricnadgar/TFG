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

import etsii.tfg.DungeonRaiders.model.BaseEntity;
import etsii.tfg.DungeonRaiders.player.Player;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder.Default;

@Entity
@Getter
@Setter
@Table(name = "games")
public class Game extends BaseEntity {

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
    private Integer turn = 0;

    @NotNull
    @Min(0)
    @Max(5)
    private Integer currentFloor = 0;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Player> players;

    @NotNull
    @Min(0)
    @Max(5)
    private Integer currentRoom = 0;

    public Boolean isActive() {
        return winnerUsername == null;
    }

    public Boolean isInLobby() {
        return turn == 0;
    }

    public Boolean isFull() {
        return maxPlayers == players.size();
    }

}
