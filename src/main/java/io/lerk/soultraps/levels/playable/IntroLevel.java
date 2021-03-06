package io.lerk.soultraps.levels.playable;

import io.lerk.soultraps.components.HUD;
import io.lerk.soultraps.levels.types.GrasslandLevel;
import io.lerk.soultraps.mobs.Enemies.Bat;
import io.lerk.soultraps.mobs.Enemies.Wolf;
import io.lerk.soultraps.mobs.Enemies.Zombie;
import io.lerk.soultraps.mobs.player.Player;
import io.lerk.soultraps.mobs.friendly.Alchemist;
import io.lerk.soultraps.mobs.friendly.Lumberjack;
import io.lerk.soultraps.items.HPPotion;
import io.lerk.soultraps.mobs.stat1c.Portal;
import io.lerk.soultraps.sys.dialog.DialogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The first level the player reaches after the main menu.
 * The player will get all necessary items here and will (hopefully) learn to kill mobs.
 *
 * @author Lukas Fülling (lukas@k40s.net)
 */
public class IntroLevel extends GrasslandLevel {

    /**
     * Logger.
     */
    private static final Logger log = LoggerFactory.getLogger(IntroLevel.class);

    /**
     * Constructor.
     */
    public IntroLevel() {
        super(); // call super() or level won't be initialized
        log.debug("Adding DialogManager...");
        addObject(DialogManager.get(), 0, 0);
        log.debug("Adding portal...");
        addMob(new Portal());
        log.info("Adding Potions...");
        addMob(new HPPotion());
        log.debug("Adding mobs...");
        addMob(new Lumberjack());
        addMob(new Alchemist());
        addMob(new Wolf());
        addMob(new Zombie());
        addMob(new Bat());
        log.debug("Adding player...");
        addMob(Player.getSelf());
        log.info("Adding HUD...");
        addObject(new HUD(), HUD_X, HUD_Y);
    }

}
