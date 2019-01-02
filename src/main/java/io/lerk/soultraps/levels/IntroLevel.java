package io.lerk.soultraps.levels;

import io.lerk.soultraps.components.HUD;
import io.lerk.soultraps.mobs.BaseMob;
import io.lerk.soultraps.mobs.Lumberjack;
import io.lerk.soultraps.mobs.Player;
import io.lerk.soultraps.sys.StopWatch;
import io.lerk.soultraps.sys.Tiles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class IntroLevel extends Level {

    private static final Logger log = LoggerFactory.getLogger(IntroLevel.class);

    public IntroLevel() {
        super();
        log.debug("Adding player...");
        addMob(Player.getSelf());
        log.debug("Adding mobs...");
        addMob(new Lumberjack());
        log.debug("Adding HUD...");
        drawHUD();
    }

    protected void drawHUD() {
        addObject(new HUD(Player.getSelf()), 0, 0);
    }

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
