package etsii.tfg.DungeonRaiders.room;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("SHOP")
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

    @Override
    public String getType() {
        return "SHOP";
    }
}
