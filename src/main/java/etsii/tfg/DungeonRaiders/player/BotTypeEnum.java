package etsii.tfg.DungeonRaiders.player;

import java.util.List;
import java.util.Random;

import etsii.tfg.DungeonRaiders.bot.CleverBot;
import etsii.tfg.DungeonRaiders.card.Card;
import etsii.tfg.DungeonRaiders.game.Game;
import etsii.tfg.DungeonRaiders.roomDungeon.RoomDungeon;
import lombok.Getter;

@Getter
public enum BotTypeEnum {
    RANDOM("Bot Aleatorio") {

        @Override
        public Card chooseCard(Player bot, List<RoomDungeon> actualFloor, Game game) {
            Random random = new Random();
            List<Card> playableCards = bot
                    .getPlayableCardsForBots(actualFloor.get(game.getActualRoomInFloor()).getRoom());
            return playableCards.get(random.nextInt(playableCards.size()));

        }

    },
    CLEVER("Bot inteligente") {

        @Override
        public Card chooseCard(Player bot, List<RoomDungeon> actualFloor, Game game) {
            return CleverBot.chooseCard(bot, actualFloor, game);
        }

    };

    private String name;

    private BotTypeEnum(String name) {
        this.name = name;
    }

    public abstract Card chooseCard(Player bot, List<RoomDungeon> actualFloor, Game game);
}
