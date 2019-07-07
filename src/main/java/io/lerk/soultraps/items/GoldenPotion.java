package io.lerk.soultraps.items;

import io.lerk.soultraps.mobs.player.Player;

import java.util.Collections;

public class GoldenPotion extends Item {

    public static final int AMOUNT = 2000;

    /**
     * Constructor.
     */
    public GoldenPotion() {
        super("Golden Potion",
                "The legendary golden potion.",
                500000, 1);
        effects = Collections.singletonList(() -> {
            Player.increaseHealth(AMOUNT);
            Player.getSelf().getItems().remove(this);
        });
        setImage("images/golden-potion.png");
    }

}
