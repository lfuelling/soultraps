package io.lerk.soultraps.levels.playable;

import io.lerk.soultraps.components.HUD;
import io.lerk.soultraps.levels.types.DesertLevel;
import io.lerk.soultraps.mobs.Enemies.Zombie;
import io.lerk.soultraps.mobs.player.Player;
import io.lerk.soultraps.mobs.stat1c.Floppy;
import io.lerk.soultraps.items.HPPotion;
import io.lerk.soultraps.mobs.stat1c.HellPortal;
import io.lerk.soultraps.mobs.stat1c.Portal;
import io.lerk.soultraps.sys.dialog.DialogManager;
import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * This is a regular (ie. easy) Level.
 *
 * @author Lukas Fülling (lukas@k40s.net)
 */
public class GenericDesertLevel extends DesertLevel {

    /**
     * Logger.
     */
    private static final Logger log = LoggerFactory.getLogger(GenericDesertLevel.class);

    /**
     * {@inheritDoc}.
     */
    public GenericDesertLevel() {
        super(); // call super() or level won't be initialized
        addLevelContent();
    }

    /**
     * {@inheritDoc}.
     */
    public GenericDesertLevel(String[][] tiles, Pair<Integer, Integer> portalCoordinates, Pair<Integer, Integer> floppyCoordinates) {
        super(tiles, portalCoordinates, floppyCoordinates);
        addLevelContent();
    }

    /**
     * Method to add level mobs.
     */
    private void addLevelContent() {
        log.debug("Adding DialogManager...");
        addObject(DialogManager.get(), 0, 0);
        if (new Random().nextInt(10) == 5) {
            log.debug("Adding hell portal...");
            addMob(new HellPortal());
        } else {
            log.debug("Adding portal...");
            addMob(new Portal());
        }
        log.debug("Adding floppy...");
        addMob(new Floppy());
        log.info("Adding Potions...");
        addMob(new HPPotion());
        log.info("Adding Mobs...");
        for (int i = 0; i < 3; i++) {
            addMob(new Zombie()); // only zombies in the desert
        }
        log.info("Adding player...");
        addMob(Player.getSelf());
        log.info("Adding HUD...");
        addObject(new HUD(), HUD_X, HUD_Y);
    }
}
