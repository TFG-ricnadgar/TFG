package etsii.tfg.DungeonRaiders.player;

import lombok.Getter;

@Getter
public enum BotTypeEnum {
    RANDOM("Bot Aleatorio"), CLEVER("Bot inteligente");

    private String name;

    private BotTypeEnum(String name) {
        this.name = name;
    }
}
