package etsii.tfg.DungeonRaiders.player;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public enum Character {
    thief("Ladrona", 2, 2, new ArrayList<Integer>(), "/img/characters/LadronaLogo.png"),
    knight("Caballero", 1, 1, new ArrayList<Integer>(), "/img/characters/CaballeroLogo.png"),
    warrior("Guerrero", 2, 0, new ArrayList<Integer>(), "/img/characters/GuerreroLogo.png"),
    sorcerer("Hechicero", 1, 1, new ArrayList<Integer>(), "/img/characters/HechiceroLogo.png"),
    explorer("Exploradora", 3, 2, new ArrayList<Integer>(), "/img/characters/ExploradoraLogo.png");

    private final String name;
    private final Integer initialCoins;
    private final Integer initialWounds;
    private final List<Integer> initialCards;
    private final String image;

    private Character(final String name, final Integer initialCoins, final Integer initialWounds,
            final List<Integer> initialCards, final String image) {
        this.name = name;
        this.initialCoins = initialCoins;
        this.initialWounds = initialWounds;
        this.initialCards = initialCards;
        this.image = image;
    }
}
