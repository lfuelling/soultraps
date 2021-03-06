package io.lerk.soultraps.levels.playable;

import io.lerk.soultraps.components.HUD;
import io.lerk.soultraps.levels.types.GrasslandLevel;
import io.lerk.soultraps.mobs.Enemies.Bat;
import io.lerk.soultraps.mobs.Enemies.Wolf;
import io.lerk.soultraps.mobs.Enemies.Zombie;
import io.lerk.soultraps.mobs.friendly.Alchemist;
import io.lerk.soultraps.mobs.player.Player;
import io.lerk.soultraps.mobs.stat1c.Floppy;
import io.lerk.soultraps.items.HPPotion;
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
public class GenericGrasslandLevel extends GrasslandLevel {

    /**
     * Logger.
     */
    private static final Logger log = LoggerFactory.getLogger(GenericGrasslandLevel.class);

    /**
     * {@inheritDoc}.
     */
    public GenericGrasslandLevel() {
        super(); // call super() or level won't be initialized
        addLevelContent();
    }

    /**
     * {@inheritDoc}.
     */
    public GenericGrasslandLevel(String[][] tiles, Pair<Integer, Integer> portalCoordinates, Pair<Integer, Integer> floppyCoordinates) {
        super(tiles, portalCoordinates, floppyCoordinates);
        addLevelContent();
    }

    /**
     * Method to add level mobs.
     */
    protected void addLevelContent() {
        log.debug("Adding DialogManager...");
        addObject(DialogManager.get(), 0, 0);
        log.debug("Adding portal...");
        addMob(new Portal());
        log.debug("Adding floppy...");
        addMob(new Floppy());
        log.info("Adding Potions...");
        addMob(new HPPotion());
        addMob(new HPPotion());
        log.info("Adding Mobs...");
        addMob(new Wolf());
        addMob(new Wolf());
        addMob(new Bat());
        addMob(new Bat());
        addMob(new Zombie());
        addMob(new Zombie());
        if(new Random().nextInt(3) == 1) {
            addMob(new Alchemist());
        }
        log.info("Adding player...");
        addMob(Player.getSelf());
        log.info("Adding HUD...");
        addObject(new HUD(), HUD_X, HUD_Y);
    }
}
