package etsii.tfg.DungeonRaiders.room;

import java.util.ArrayList;
import java.util.List;

import etsii.tfg.DungeonRaiders.player.Player;
import lombok.Getter;

@Getter
public enum TrapTarget {
    LESSWOUNDED("/img/icons/Down.png", "/img/icons/Wound.png") {
        @Override
        List<Player> getTargetPlayers(List<Player> players) {
            List<Player> targetPlayers = new ArrayList<Player>();
            Integer minWoundAmount = 99;
            for (Player player : players) {
                if (minWoundAmount > player.getWounds()) {
                    targetPlayers.clear();
                    targetPlayers.add(player);
                    minWoundAmount = player.getWounds();
                } else if (minWoundAmount == player.getWounds()) {
                    targetPlayers.add(player);
                }
            }
            return targetPlayers;
        }
    },
    RICHEST("/img/icons/Top.png", "/img/icons/Coin.png") {
        @Override
        List<Player> getTargetPlayers(List<Player> players) {
            List<Player> targetPlayers = new ArrayList<Player>();
            Integer maxMoneyAmount = -1;
            for (Player player : players) {
                if (maxMoneyAmount < player.getCoins()) {
                    targetPlayers.clear();
                    targetPlayers.add(player);
                    maxMoneyAmount = player.getCoins();
                } else if (maxMoneyAmount == player.getCoins()) {
                    targetPlayers.add(player);
                }
            }
            return targetPlayers;
        }
    };

    private final String imageOrder;
    private final String imageCharacteristic;

    private TrapTarget(final String imageOrder, final String imageCharacteristic) {
        this.imageOrder = imageOrder;
        this.imageCharacteristic = imageCharacteristic;
    }

    abstract List<Player> getTargetPlayers(List<Player> players);
}
