package io.lerk.soultraps.levels;

import io.lerk.soultraps.mobs.Enemies.Bat;
import io.lerk.soultraps.mobs.Enemies.Wolf;
import io.lerk.soultraps.mobs.Enemies.Zombie;
import io.lerk.soultraps.mobs.Player;
import io.lerk.soultraps.mobs.Portal;
import io.lerk.soultraps.sys.dialog.DialogManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is a regular (ie. easy) Level.
 *
 * @author Lukas Fülling (lukas@k40s.net)
 */
public class RegularLevel extends Level {

    /**
     * Logger.
     */
    private static final Logger log = LoggerFactory.getLogger(RegularLevel.class);

    /**
     * Constructor.
     */
    public RegularLevel() {
        super();
        log.debug("Adding DialogManager...");
        addObject(DialogManager.get(), 0, 0);
        log.info("Adding player...");
        addMob(Player.getSelf());
        log.debug("Adding portal...");
        addMob(new Portal());
        log.info("Adding Mobs...");
        addMob(new Wolf());
        addMob(new Zombie());
        addMob(new Bat());
    }
}
