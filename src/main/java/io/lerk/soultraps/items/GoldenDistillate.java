package io.lerk.soultraps.items;

import io.lerk.soultraps.mobs.player.Player;

import java.util.Collections;

public class GoldenDistillate extends Item {
    public static final int AMOUNT = 8000;

    /**
     * Constructor.
     */
    public GoldenDistillate() {
        super("Golden Distillate",
                "The legendary golden potion enhanced by the alchemist.",
                500000, 1);
        effects = Collections.singletonList(() -> {
            //TODO: add more effects
            Player.increaseHealth(AMOUNT);
            Player.getSelf().getItems().remove(this);
        });
        setImage("images/golden-distillate.png");
    }

}
