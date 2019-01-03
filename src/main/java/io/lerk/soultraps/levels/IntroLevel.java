package io.lerk.soultraps.levels;

import io.lerk.soultraps.components.HUD;
import io.lerk.soultraps.mobs.Enemies.Bat;
import io.lerk.soultraps.mobs.Enemies.Wolf;
import io.lerk.soultraps.mobs.Enemies.Zombie;
import io.lerk.soultraps.mobs.Player;
import io.lerk.soultraps.mobs.Portal;
import io.lerk.soultraps.mobs.friendly.Lumberjack;
import io.lerk.soultraps.sys.dialog.DialogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The first level the player reaches after the main menu.
 * The player will get all necessary items here and will (hopefully) learn to kill mobs.
 *
 * @author Lukas Fülling (lukas@k40s.net)
 */
public class IntroLevel extends Level {

    /**
     * Logger.
     */
    private static final Logger log = LoggerFactory.getLogger(IntroLevel.class);

    /**
     * Constructor.
     */
    public IntroLevel() {
        super();
        log.debug("Adding DialogManager...");
        addObject(DialogManager.get(), 0, 0);
        log.debug("Adding player...");
        addMob(Player.getSelf());
        log.debug("Adding portal...");
        addMob(new Portal());
        log.debug("Adding mobs...");
        addMob(new Lumberjack());
        addMob(new Wolf());
        addMob(new Wolf());
        addMob(new Bat());
        addMob(new Bat());
        addMob(new Zombie());
        addMob(new Zombie());
        log.debug("Adding HUD...");
        drawHUD();
    }

    /**
     * Draws HUD.
     */
    protected void drawHUD() {
        addObject(new HUD(), 0, 0);
    }

}
