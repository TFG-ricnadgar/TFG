package etsii.tfg.DungeonRaiders.player;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public enum Character {
    thief("Ladrona",2,2,new ArrayList<Integer>()),
    knight("Caballero",1,1,new ArrayList<Integer>()),
    warrior("Guerrero",2,0,new ArrayList<Integer>()),
    sorcerer("Hechicero",1,1,new ArrayList<Integer>()),
    explorer("Exploradora",3,2,new ArrayList<Integer>());

    private final String name;
    private final Integer initialCoins;
    private final Integer initialWounds;
    private final List<Integer> initialCards;

    private Character(final String name,final Integer initialCoins, final Integer initialWounds, final List<Integer> initialCards) {
        this.name = name;
        this.initialCoins = initialCoins;
        this.initialWounds=initialWounds;
        this.initialCards = initialCards;
    }
}
