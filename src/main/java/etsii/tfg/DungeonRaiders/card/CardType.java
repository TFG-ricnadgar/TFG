package etsii.tfg.DungeonRaiders.card;

import lombok.Getter;

@Getter
public enum CardType {
    one("1", 1, "/img/icons/1.png"),
    two("2", 2, "/img/icons/2.png"),
    three("3", 3, "/img/icons/3.png"),
    four("4", 4, "/img/icons/4.png"),
    five("5", 5, "/img/icons/5.png"),
    torch("Antorcha", 0, "/img/icons/Torch.png"),
    crystalBall("Bola de cristal", 0, "/img/icons/CrystalBall.png"),
    key("Llave", 5, "/img/icons/Key.png"),
    sword("Espada", 5, "/img/icons/Sword.png");

    private final String name;
    private final Integer value;
    private final String image;

    private CardType(final String name, final Integer value, final String image) {
        this.name = name;
        this.value = value;
        this.image = image;
    }

    public Boolean isPlayable() {
        return this != torch;
    }
}
