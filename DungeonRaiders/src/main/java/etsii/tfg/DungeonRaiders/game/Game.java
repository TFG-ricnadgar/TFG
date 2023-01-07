package etsii.tfg.DungeonRaiders.game;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import etsii.tfg.DungeonRaiders.model.BaseEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="games")
public class Game extends BaseEntity{

    @NotBlank
    private String gameName;

    @NotBlank
    private String creatorUsername;

    @NotNull
    private Integer turn;

    @NotNull
    @Min(0)
    @Max(5)
    private Integer currentFloor;

    @NotNull
    @Min(0)
    @Max(5)
    private Integer currentRoom;


    
}
