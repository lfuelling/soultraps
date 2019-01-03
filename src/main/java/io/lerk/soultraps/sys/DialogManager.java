package io.lerk.soultraps.sys;

import greenfoot.*;
import io.lerk.soultraps.levels.Level;
import io.lerk.soultraps.mobs.DialogMob;
import io.lerk.soultraps.mobs.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class used to display dialogs.
 *
 * @author Lukas Fülling (lukas@k40s.net)
 */
public class DialogManager {

    /**
     * Logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(DialogManager.class);

    /**
     * DialogManager instance.
     */
    private static DialogManager instance = null;

    /**
     * World where the message is displayed in.
     */
    private final World world;

    private DialogManager(World world) {
        this.world = world;
    }

    /**
     * Displays a message to the user.
     *
     * @param s   the message to display
     * @param mob the mob the player is talking to
     */
    public static void displayMessage(String s, World world, DialogMob mob) {
        if (instance == null || !instance.world.equals(world)) {
            logger.debug("Instance is null or world has changed.");
            instance = new DialogManager(world);
        }
        if (mob != null) {
            mob.setTalking(true);
        }
        Player.getSelf().setTalking(true);
        world.addObject(new Message(s, mob), 0, 0);
    }

    private static void dismiss(Message message) {
        if (instance == null) {
            throw new IllegalStateException("DialogManager instance is null!");
        } else {
            if (message.mob != null) {
                message.mob.setTalking(false);
            }
            Player.getSelf().setTalking(false);
            instance.world.removeObject(message);
        }
    }

    private static class Message extends Actor {
        private final DialogMob mob;

        Message(String message, DialogMob mob) {
            this.mob = mob;
            GreenfootImage image = new GreenfootImage(Level.LEVEL_WIDTH * Level.CELL_SIZE, 512);
            image.setColor(Color.BLACK);
            image.fill();
            image.setColor(Color.WHITE);
            image.drawRect(0, 0, image.getWidth(), image.getHeight());
            image.setFont(Fonts.getFont(Fonts.Types.SKYRIM, 24));
            image.drawString(message, 4, (image.getHeight() / 2));
            setImage(image);
        }

        @Override
        public void act() {
            if (Greenfoot.isKeyDown("space")) {
                DialogManager.dismiss(this);
            }
        }
    }
}
