package etsii.tfg.DungeonRaiders.room;

import lombok.Getter;

@Getter
public enum DamageType {
    WOUND("/img/icons/Wound.png"), COIN("/img/icons/Coin.png"), WOUNDCOIN("/img/icons/WoundCoin.png");

    private final String image;

    private DamageType(String image) {
        this.image = image;
    }
}
