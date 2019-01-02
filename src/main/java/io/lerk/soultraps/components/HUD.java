package io.lerk.soultraps.components;

import greenfoot.Actor;
import greenfoot.GreenfootImage;
import io.lerk.soultraps.mobs.Player;

/**
 * A HUD showing the player's current health and other stuff.
 *
 * @author Lukas Fülling (lukas@k40s.net)
 * @see Player
 */
public class HUD extends Actor {

    /**
     * The player.
     */
    private final Player player;

    /**
     * Counter value used for throttling.
     */
    private long lastUpdateMillis;

    /**
     * Constructor.
     */
    public HUD() {
        this.player = Player.getSelf();
    }

    /**
     * Updates the HUD's image if necessary.
     */
    @Override
    public void act() {
        if (System.currentTimeMillis() - 100 >= lastUpdateMillis) {
            updateImage();
            lastUpdateMillis = System.currentTimeMillis();
        }
    }

    /**
     * Internal update method that is throttled.
     * This should call {@link #setImage(GreenfootImage)}.
     */
    private void updateImage() {
        //FIXME implement
        setImage(new GreenfootImage(256, 64));
    }
}
