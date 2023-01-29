package etsii.tfg.DungeonRaiders.room;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Shop extends Room {
    @NotNull
    private ShopItem firstItem;

    @NotNull
    private ShopItem secondItem;

    @NotNull
    private ShopItem thirdItem;

    @NotNull
    private ShopItem fourthItem;

    @NotNull
    private ShopItem fifthItem;
}
