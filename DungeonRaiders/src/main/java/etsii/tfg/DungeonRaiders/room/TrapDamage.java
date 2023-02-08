package etsii.tfg.DungeonRaiders.room;

import etsii.tfg.DungeonRaiders.player.Player;
import lombok.Getter;

@Getter
public enum TrapDamage {
    WOUND("/img/icons/Wound.png") {

        @Override
        Player damage(Player player, Integer effectAmount) {
            player.setWounds(player.getWounds() + effectAmount);
            return player;
        }
    },
    COIN("/img/icons/Coin.png") {
        @Override
        Player damage(Player player, Integer effectAmount) {
            Integer newCoins = player.getCoins() + effectAmount > 0 ? player.getCoins() + effectAmount : 0;
            player.setCoins(newCoins);
            return player;
        }
    };

    private final String image;

    private TrapDamage(final String image) {
        this.image = image;
    }

    abstract Player damage(Player player, Integer effectAmount);
}
