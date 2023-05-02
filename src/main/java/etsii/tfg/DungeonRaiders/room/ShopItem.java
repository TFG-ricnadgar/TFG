package etsii.tfg.DungeonRaiders.room;

import etsii.tfg.DungeonRaiders.card.Card;
import etsii.tfg.DungeonRaiders.card.CardService;
import etsii.tfg.DungeonRaiders.card.CardType;
import etsii.tfg.DungeonRaiders.player.Player;
import etsii.tfg.DungeonRaiders.player.PlayerService;
import lombok.Getter;

@Getter
public enum ShopItem {
    HEALTHONE("/img/icons/Potion1.png") {

        @Override
        void buyEffect(Player player, PlayerService playerService, CardService cardService) {
            Integer newWounds = player.getWounds() - 1 > 0 ? player.getWounds() - 1 : 0;
            player.setWounds(newWounds);
            playerService.save(player);
        }

    },
    HEALTHTWO("/img/icons/Potion2.png") {

        @Override
        void buyEffect(Player player, PlayerService playerService, CardService cardService) {
            Integer newWounds = player.getWounds() - 2 > 0 ? player.getWounds() - 2 : 0;
            player.setWounds(newWounds);
            playerService.save(player);
        }

    },
    COINONE("/img/icons/Coin1.png") {

        @Override
        void buyEffect(Player player, PlayerService playerService, CardService cardService) {
            player.setCoins(player.getCoins() + 1);
            playerService.save(player);
        }

    },
    COINTWO("/img/icons/Coin2.png") {

        @Override
        void buyEffect(Player player, PlayerService playerService, CardService cardService) {
            player.setCoins(player.getCoins() + 2);
            playerService.save(player);
        }

    },
    COINTHREE("/img/icons/Coin3.png") {

        @Override
        void buyEffect(Player player, PlayerService playerService, CardService cardService) {
            player.setCoins(player.getCoins() + 3);
            playerService.save(player);
        }

    },
    TORCH("/img/icons/Torch.png") {

        @Override
        void buyEffect(Player player, PlayerService playerService, CardService cardService) {
            Card newCard = new Card(CardType.torch, player);
            cardService.save(newCard);
        }

    },
    CRYSTALBALL("/img/icons/CrystalBall.png") {

        @Override
        void buyEffect(Player player, PlayerService playerService, CardService cardService) {
            Card newCard = new Card(CardType.crystalBall, player);
            cardService.save(newCard);
        }

    },
    KEY("/img/icons/Key.png") {

        @Override
        void buyEffect(Player player, PlayerService playerService, CardService cardService) {
            Card newCard = new Card(CardType.key, player);
            cardService.save(newCard);
        }

    },
    SWORD("/img/icons/Sword.png") {

        @Override
        void buyEffect(Player player, PlayerService playerService, CardService cardService) {
            Card newCard = new Card(CardType.sword, player);
            cardService.save(newCard);
        }

    };

    private final String image;

    private ShopItem(final String image) {
        this.image = image;
    }

    abstract void buyEffect(Player player, PlayerService playerService, CardService cardService);
}
