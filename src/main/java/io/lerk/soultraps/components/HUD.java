package io.lerk.soultraps.components;

import greenfoot.Actor;
import greenfoot.Color;
import greenfoot.GreenfootImage;
import io.lerk.soultraps.mobs.Player;
import io.lerk.soultraps.sys.Fonts;

/**
 * A HUD showing the player's current health and other stuff.
 *
 * @author Lukas Fülling (lukas@k40s.net)
 * @see Player
 */
public class HUD extends Actor {

    /**
     * Counter value used for throttling.
     */
    private long lastUpdateMillis;

    /**
     * Constructor.
     */
    public HUD() {
        updateImage();
    }

    /**
     * Updates the HUD's image if necessary.
     */
    @Override
    public void act() {
        if (System.currentTimeMillis() - lastUpdateMillis >= 100) {
            updateImage();
            lastUpdateMillis = System.currentTimeMillis();
        }
    }

    /**
     * Internal update method that is throttled.
     * This should call {@link #setImage(GreenfootImage)}.
     */
    private void updateImage() {
        GreenfootImage image = new GreenfootImage(48*3, 48*2);
        image.setColor(Color.WHITE);
        image.setFont(Fonts.getFont(Fonts.Types.SKYRIM, 32f));
        image.drawString(Player.getSelf().getHealth() + "", 0, 24);
        setImage(image);
    }
}
