package io.lerk.soultraps.sys.console;

import greenfoot.Actor;
import greenfoot.Color;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import io.lerk.soultraps.levels.Level;
import io.lerk.soultraps.levels.TestLevel;
import io.lerk.soultraps.mobs.BaseMob;
import io.lerk.soultraps.mobs.Enemies.Bat;
import io.lerk.soultraps.mobs.Enemies.Wolf;
import io.lerk.soultraps.mobs.Enemies.Zombie;
import io.lerk.soultraps.mobs.Player;
import io.lerk.soultraps.mobs.friendly.Lumberjack;
import io.lerk.soultraps.sys.Fonts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Console extends Actor {

    private static final Logger log = LoggerFactory.getLogger(Console.class);
    private static final int DEBOUNCE = 200;
    private static final int IMAGE_HEIGHT = 32;
    private static final int IMAGE_WIDTH = Level.LEVEL_WIDTH * Level.CELL_SIZE;
    private static final String[] chars = {"a", "b", "c", "d", "e", "f", "g", "h",
            "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
            "w", "x", "y", "z", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
    public static final String OK = "Ok!";
    public static final String UNKNOWN_COMMAND = "Unknown Command!";

    private String text = "";
    private String prevText = "";
    private long lastUpdate = 0;

    Console() {
        GreenfootImage image = getBackground();
        setImage(image);
        lastUpdate = System.currentTimeMillis();
    }

    private GreenfootImage getBackground() {
        GreenfootImage background = new GreenfootImage(IMAGE_WIDTH, IMAGE_HEIGHT);
        background.setColor(Color.BLACK);
        background.fill();
        return background;
    }

    @Override
    public void act() {
        if (System.currentTimeMillis() - lastUpdate > DEBOUNCE) {
            if (Greenfoot.isKeyDown("enter")) {
                text = runCommand();
            } else if (Greenfoot.isKeyDown("backspace")) {
                if (text.length() > 1) {
                    text = text.substring(0, text.length() - 2);
                } else {
                    text = "";
                }
            } else if (arrowKeyPressed()) {
                log.warn("TODO: implement command history/cursors!");
            } else {
                handleKeyboardInput();
            }
            lastUpdate = System.currentTimeMillis();
        }
        if (!text.equals(prevText)) {
            GreenfootImage image = new GreenfootImage(IMAGE_WIDTH, IMAGE_HEIGHT);
            image.drawImage(getBackground(), 0, 0);
            image.drawImage(getTextImage(), IMAGE_WIDTH / 2, 12);
            setImage(image);
            prevText = text;
        }
    }

    private GreenfootImage getTextImage() {
        GreenfootImage textImage = new GreenfootImage(IMAGE_WIDTH, IMAGE_HEIGHT - 2);
        textImage.setColor(Color.WHITE);
        textImage.setFont(Fonts.getFont(Fonts.Types.SKYRIM, 24));
        textImage.drawString(text, 4, (textImage.getHeight() / 2));
        return textImage;
    }

    private boolean arrowKeyPressed() {
        return Greenfoot.isKeyDown("down") ||
                Greenfoot.isKeyDown("up") ||
                Greenfoot.isKeyDown("left") ||
                Greenfoot.isKeyDown("right");
    }

    private String runCommand() {
        if(text.startsWith(OK) || text.startsWith(UNKNOWN_COMMAND)) {
            log.warn("Ignoring output!");
            return text;
        }
        if (text.startsWith("spawn")) {
            return handleSpawnMob();
        }
        switch (text) {
            case "testroom":
                if (getWorld() instanceof TestLevel) {
                    ConsoleUtil.closeConsole();
                    TestLevel.exit();
                    return OK;
                } else {
                    Greenfoot.setWorld(new TestLevel((Level) getWorld()));
                    return OK;
                }
            case "heal":
                Player.getSelf().setHealth(Player.getSelf().maxHealth());
                return OK;
            default:
                return UNKNOWN_COMMAND;
        }
    }

    /**
     * Command used to spawn a mob.
     * <p>
     * Example Syntax: "spawnbat", "spawnwolf", etc.
     *
     * @return Command output.
     */
    private String handleSpawnMob() {
        String[] args = text.split("spawn");
        if (args.length > 1) {
            BaseMob mob;
            switch (args[1]) {
                case "bat":
                    mob = new Bat();
                    break;
                case "wolf":
                    mob = new Wolf();
                    break;
                case "zombie":
                    mob = new Zombie();
                    break;
                case "lumber":
                    mob = new Lumberjack();
                    break;
                default:
                    return UNKNOWN_COMMAND;
            }
            ((Level) getWorld()).addMob(mob);
        } else {
            return UNKNOWN_COMMAND;
        }
        return OK;
    }

    private void handleKeyboardInput() {
        String key = null;

        for (String c : chars) {
            if (Greenfoot.isKeyDown(c)) {
                key = c;
                break;
            }
        }

        if (key != null) {
            text += key;
        }
    }
}
