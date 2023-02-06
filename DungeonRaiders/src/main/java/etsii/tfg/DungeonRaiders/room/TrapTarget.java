package etsii.tfg.DungeonRaiders.room;

import lombok.Getter;

@Getter
public enum TrapTarget {
    LESSWOUNDED("/img/icons/Down.png", "/img/icons/Wound.png"), RICHEST("/img/icons/Top.png", "/img/icons/Coin.png");

    private final String imageOrder;
    private final String imageCharacteristic;

    private TrapTarget(final String imageOrder, final String imageCharacteristic) {
        this.imageOrder = imageOrder;
        this.imageCharacteristic = imageCharacteristic;
    }
}
