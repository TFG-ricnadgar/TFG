package etsii.tfg.DungeonRaiders.player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import etsii.tfg.DungeonRaiders.card.Card;
import etsii.tfg.DungeonRaiders.card.CardType;
import lombok.Getter;

@Getter
public enum Character {
    thief("Ladrona", 2, 2, new ArrayList<CardType>(Arrays.asList(CardType.key)), "/img/characters/LadronaLogo.png"),
    knight("Caballero", 1, 1, new ArrayList<CardType>(Arrays.asList(CardType.sword)),
            "/img/characters/CaballeroLogo.png"),
    warrior("Guerrero", 2, 0, new ArrayList<CardType>(), "/img/characters/GuerreroLogo.png"),
    sorcerer("Hechicero", 1, 1, new ArrayList<CardType>(Arrays.asList(CardType.crystalBall, CardType.crystalBall)),
            "/img/characters/HechiceroLogo.png"),
    explorer("Exploradora", 3, 2, new ArrayList<CardType>(Arrays.asList(CardType.torch)),
            "/img/characters/ExploradoraLogo.png");

    private final String name;
    private final Integer initialCoins;
    private final Integer initialWounds;
    private final List<CardType> initialCards;
    private final String image;

    private Character(final String name, final Integer initialCoins, final Integer initialWounds,
            final List<CardType> initialCards, final String image) {
        this.name = name;
        this.initialCoins = initialCoins;
        this.initialWounds = initialWounds;
        this.initialCards = initialCards;
        this.image = image;
    }
}
