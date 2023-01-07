package etsii.tfg.DungeonRaiders.card;

import lombok.Getter;

@Getter
public enum CardType {
    one("1",1),
    two("2",2),
    three("3",3),
    four("4",4),
    five("5",5),
    torch("Antorcha",0),
    crystalBall("Bola de cristal",0),
    key("Llave",5),
    sword("Espada",5);

    private final String name;
    private final Integer value;

    private CardType(final String name,final Integer value) {
        this.name = name;
        this.value= value;
    }
}
