package etsii.tfg.DungeonRaiders.bot;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import etsii.tfg.DungeonRaiders.card.Card;
import etsii.tfg.DungeonRaiders.card.CardType;
import etsii.tfg.DungeonRaiders.game.Game;
import etsii.tfg.DungeonRaiders.player.Player;
import etsii.tfg.DungeonRaiders.room.Enemy;
import etsii.tfg.DungeonRaiders.room.FinalBoss;
import etsii.tfg.DungeonRaiders.room.Room;
import etsii.tfg.DungeonRaiders.room.Shop;
import etsii.tfg.DungeonRaiders.room.Trap;
import etsii.tfg.DungeonRaiders.room.Treasure;
import etsii.tfg.DungeonRaiders.roomDungeon.RoomDungeon;

public class CleverBot {

    public static Card chooseCard(Player bot, List<RoomDungeon> actualFloor, Game game) {
        Room roomInPlay = actualFloor.get(game.getActualRoomInFloor()).getRoom();
        Card cardPlayed = new Card();
        List<Card> sortedCardsInHand = bot.getPlayableCards(roomInPlay).stream()
                .sorted(Comparator.comparingInt(c -> c.getType().getValue()))
                .collect(Collectors.toList());
        switch (roomInPlay.getType()) {
            case "ENEMY":
                cardPlayed = handleEnemy(sortedCardsInHand, bot, (Enemy) roomInPlay, actualFloor, game);
                break;
            case "TRAP":
                cardPlayed = handleTrap(sortedCardsInHand, bot, (Trap) roomInPlay, actualFloor, game);
                break;
            case "SHOP":
                cardPlayed = handleShop(sortedCardsInHand, bot, (Shop) roomInPlay, actualFloor, game);
                break;
            case "TREASURE":
                cardPlayed = handleTreasure(sortedCardsInHand, bot, (Treasure) roomInPlay, actualFloor, game);
                break;
            case "FINAL_BOSS":
                cardPlayed = handleFinalBoss(sortedCardsInHand, (FinalBoss) roomInPlay, bot, game);
                break;
            default:
                break;
        }
        return cardPlayed;
    }

    private static Map<String, Integer> minCardValueNeededNextRooms(List<RoomDungeon> actualFloor, Integer roomInFloor,
            Integer playersAmount, Player bot) {
        Map<String, Integer> valuesNeeded = new HashMap<>();
        Integer minCardValue = 99;
        Integer amountCardsNeeded = 0;
        List<Room> nextVisibleRooms = actualFloor.stream()
                .filter(rd -> !rd.getIsHidden() && rd.getPosition() > roomInFloor)
                .map(rd -> rd.getRoom())
                .collect(Collectors.toList());
        for (Room room : nextVisibleRooms) {
            if (room.getType() == "ENEMY") {
                Enemy enemyRoom = (Enemy) room;
                amountCardsNeeded++;
                if (enemyRoom.minValueToWinSafe(playersAmount) < minCardValue) {
                    minCardValue = enemyRoom.minValueToWinSafe(playersAmount);
                }

            }
        }

        for (Card card : bot.getCards()) {
            if (card.getType() == CardType.sword) {
                amountCardsNeeded--;
            }
        }

        valuesNeeded.put("minCardValue", minCardValue);
        valuesNeeded.put("amountCardsNeeded", amountCardsNeeded);
        return valuesNeeded;
    }

    private static Card pickHighestNotNeededOrLowestNeeded(Game game, List<Card> sortedCardsInHand, Player bot,
            List<RoomDungeon> actualFloor) {
        Map<String, Integer> values = minCardValueNeededNextRooms(actualFloor, game.getActualRoomInFloor(),
                game.getPlayers().size(), bot);

        // Firstly is checked that all the cards in hand are needed for future rooms
        if (values.get("amountCardsNeeded") < sortedCardsInHand.size()) {

            // In case there is at least a single card left that is not needed, the ones not
            // needed are filtered into a list
            sortedCardsInHand = sortedCardsInHand.stream()
                    .filter(c -> c.getType().getValue() < values.get("minCardValue"))
                    .sorted(Comparator.comparingInt(c -> c.getType().getValue()))
                    .collect(Collectors.toList());
            // The biggest one available not needed is picked
            sortedCardsInHand.get(sortedCardsInHand.size() - 1);
        }
        // In the case that all are needed the smallest is picked
        return sortedCardsInHand.get(0);
    }

    private static Card handleEnemy(List<Card> sortedCardsInHand, Player bot, Enemy roomInPlay,
            List<RoomDungeon> actualFloor,
            Game game) {
        Integer minValueInCardRequiredToWinSafe = roomInPlay.minValueToWinSafe(game.getPlayers().size());
        List<Card> cardsWithMinValueToWinSafe = sortedCardsInHand.stream()
                .filter(c -> c.getType().getValue() >= minValueInCardRequiredToWinSafe)
                .collect(Collectors.toList());
        if (cardsWithMinValueToWinSafe.size() > 0) {
            return cardsWithMinValueToWinSafe.get(0);
        } else {
            return sortedCardsInHand.get(sortedCardsInHand.size() - 1);
        }

    }

    private static Card handleTrap(List<Card> sortedCardsInHand, Player bot, Trap roomInPlay,
            List<RoomDungeon> actualFloor, Game game) {
        Boolean agressive = false;
        Card cardPlayed = new Card();
        switch (roomInPlay.getTarget()) {
            case LESSWOUNDED:
                agressive = game.getPlayers().stream()
                        .filter(p -> p.getWounds() < bot.getWounds())
                        .count() > 0;
                break;
            case RICHEST:
                agressive = game.getPlayers().stream()
                        .filter(p -> p.getCoins() > bot.getCoins())
                        .count() > 0;
                break;
            default:
                break;
        }

        // First is checked the player is not the one targeted by the trap
        if (agressive) {
            cardPlayed = pickHighestNotNeededOrLowestNeeded(game, sortedCardsInHand, bot, actualFloor);

        } else {
            cardPlayed = sortedCardsInHand.get(0);
        }
        return cardPlayed;
    }

    private static Card handleShop(List<Card> sortedCardsInHand, Player bot, Shop roomInPlay,
            List<RoomDungeon> actualFloor, Game game) {

        return pickHighestNotNeededOrLowestNeeded(game, sortedCardsInHand, bot, actualFloor);
    }

    private static Card handleTreasure(List<Card> sortedCardsInHand, Player bot, Treasure roomInPlay,
            List<RoomDungeon> actualFloor, Game game) {
        if (sortedCardsInHand.stream().anyMatch(c -> c.getType().equals(CardType.key))) {
            return sortedCardsInHand.stream()
                    .filter(c -> c.getType().equals(CardType.key))
                    .findFirst()
                    .get();
        }

        return pickHighestNotNeededOrLowestNeeded(game, sortedCardsInHand, bot, actualFloor);
    }

    private static Card handleFinalBoss(List<Card> sortedCardsInHand, FinalBoss roomInPlay, Player bot, Game game) {
        if (sortedCardsInHand.stream().anyMatch(c -> c.getType().equals(roomInPlay.getEscapeCard()))) {
            return sortedCardsInHand.stream()
                    .filter(c -> c.getType().equals(roomInPlay.getEscapeCard()))
                    .findFirst()
                    .get();
        } else {
            Integer minValueInCardRequiredToWinSafe = roomInPlay.minValueToWinSafe(game.getPlayers().size());
            List<Card> cardsWithMinValueToWinSafe = sortedCardsInHand.stream()
                    .filter(c -> c.getType().getValue() >= minValueInCardRequiredToWinSafe)
                    .collect(Collectors.toList());
            if (cardsWithMinValueToWinSafe.size() > 0) {
                return cardsWithMinValueToWinSafe.get(0);
            } else {
                return sortedCardsInHand.get(0);
            }
        }

    }

}
