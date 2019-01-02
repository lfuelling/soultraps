package io.lerk.soultraps.levels;

import io.lerk.soultraps.components.HUD;
import io.lerk.soultraps.mobs.BaseMob;
import io.lerk.soultraps.mobs.Lumberjack;
import io.lerk.soultraps.mobs.Player;
import io.lerk.soultraps.mobs.Wolf;
import io.lerk.soultraps.sys.StopWatch;
import io.lerk.soultraps.sys.Tiles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * The first level the player reches after the main menu.
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
        log.debug("Adding player...");
        addMob(Player.getSelf());
        log.debug("Adding mobs...");
        addMob(new Lumberjack());
        addMob(new Wolf());
        addMob(new Wolf());
        log.debug("Adding HUD...");
        drawHUD();
    }

    /**
     * Draws HUD.
     */
    protected void drawHUD() {
        addObject(new HUD(), 0, 0);
    }

    /**
     * Adds a mob at a random "free" (of trees, bushes, stones, etc.) tile.
     * This method also runs a {@link StopWatch} that logs the added mob at debug level.
     *
     * @param mob the mob to add.
     */
    private void addMob(BaseMob mob) {
        StopWatch stopWatch = new StopWatch(StopWatch.LogLevel.DEBUG);
        stopWatch.start();
        int randomX = 0;
        int randomY = 0;
        final boolean[] goodTile = {false};
        while (!goodTile[0]) {
            randomX = new Random().nextInt(Level.LEVEL_WIDTH);
            randomY = new Random().nextInt(Level.LEVEL_HEIGHT);
            getObjectsAt(randomX, randomY, Tiles.Tile.class).forEach(t -> goodTile[0] = Tiles.evaluateSpawn(t));
        }
        addObject(mob, randomX, randomY);
        stopWatch.stop("addMob(" + mob.getClass().getName() + ")");
    }

}
