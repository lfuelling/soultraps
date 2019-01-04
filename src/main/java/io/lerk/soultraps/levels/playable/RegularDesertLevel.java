package io.lerk.soultraps.levels.playable;

import io.lerk.soultraps.levels.types.DesertLevel;
import io.lerk.soultraps.mobs.Enemies.Zombie;
import io.lerk.soultraps.mobs.Player;
import io.lerk.soultraps.mobs.stat1c.Floppy;
import io.lerk.soultraps.mobs.stat1c.Portal;
import io.lerk.soultraps.sys.dialog.DialogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is a regular (ie. easy) Level.
 *
 * @author Lukas Fülling (lukas@k40s.net)
 */
public class RegularDesertLevel extends DesertLevel {

    /**
     * Logger.
     */
    private static final Logger log = LoggerFactory.getLogger(RegularDesertLevel.class);

    /**
     * Constructor.
     */
    public RegularDesertLevel() {
        super(); // call super() or level won't be initialized
        log.debug("Adding DialogManager...");
        addObject(DialogManager.get(), 0, 0);
        log.debug("Adding portal...");
        addMob(new Portal());
        log.debug("Adding floppy...");
        addMob(new Floppy());
        log.info("Adding Mobs...");
        addMob(new Zombie()); // only zombies in the desert
        addMob(new Zombie());
        addMob(new Zombie());
        log.info("Adding player...");
        addMob(Player.getSelf());
    }
}
