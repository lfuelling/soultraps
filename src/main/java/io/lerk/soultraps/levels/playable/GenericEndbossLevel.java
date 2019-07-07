package io.lerk.soultraps.levels.playable;

import io.lerk.soultraps.components.HUD;
import io.lerk.soultraps.levels.types.HellLevel;
import io.lerk.soultraps.mobs.Enemies.Zombie;
import io.lerk.soultraps.mobs.player.Player;
import io.lerk.soultraps.mobs.stat1c.HellPortal;
import io.lerk.soultraps.sys.dialog.DialogManager;
import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenericEndbossLevel extends HellLevel {

    /**
     * Logger.
     */
    private static final Logger log = LoggerFactory.getLogger(GenericEndbossLevel.class);

    /**
     * {@inheritDoc}.
     */
    public GenericEndbossLevel() {
        super(); // call super() or level won't be initialized
        addLevelContent();
    }

    /**
     * {@inheritDoc}.
     */
    public GenericEndbossLevel(String[][] tiles, Pair<Integer, Integer> portalCoordinates, Pair<Integer, Integer> floppyCoordinates) {
        super(tiles, portalCoordinates, floppyCoordinates);
        addLevelContent();
    }

    /**
     * Method to add level mobs.
     */
    private void addLevelContent() {
        log.debug("Adding DialogManager...");
        addObject(DialogManager.get(), 0, 0);
        log.debug("Adding hell portal...");
        addMob(new HellPortal());
        log.info("Adding Mobs...");
        for (int i = 0; i < 8; i++) {
            addMob(new Zombie()); // only zombies in the hell
        }
        //TODO: add boss
        log.info("Adding player...");
        addMob(Player.getSelf());
        log.info("Adding HUD...");
        addObject(new HUD(), HUD_X, HUD_Y);
    }
}
