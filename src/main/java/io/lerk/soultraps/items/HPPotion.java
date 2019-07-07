package io.lerk.soultraps.items;

import io.lerk.soultraps.mobs.player.Player;

import java.util.Collections;

public class HPPotion extends Item {

    public static final int AMOUNT = 25;

    /**
     * Constructor.
     */
    public HPPotion() {
        super("Health Potion",
                "Normal health potion.",
                50, 1);
        effects = Collections.singletonList(() -> {
            Player.increaseHealth(AMOUNT);
            Player.getSelf().getItems().remove(this);
        });
        setImage("images/hp-potion.png");
    }

}
