package etsii.tfg.DungeonRaiders.room;

import lombok.Getter;

@Getter
public enum TrapDamage {
    WOUND("/img/icons/Wound.png"), COIN("/img/icons/Coin.png");

    private final String image;

    private TrapDamage(final String image) {
        this.image = image;
    }
}
