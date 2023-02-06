package etsii.tfg.DungeonRaiders.room;

import lombok.Getter;

@Getter
public enum ShopItem {
    HEALTHONE("/img/icons/Potion1.png"), HEALTHTWO("/img/icons/Potion2.png"), COINONE("/img/icons/Coin1.png"),
    COINTWO("/img/icons/Coin2.png"), COINTHREE("/img/icons/Coin3.png"), TORCH("/img/icons/Torch.png"),
    CRYSTALBALL("/img/icons/CrystalBall.png"), KEY("/img/icons/Key.png"), SWORD("/img/icons/Sword.png");

    private final String image;

    private ShopItem(final String image) {
        this.image = image;
    }
}
