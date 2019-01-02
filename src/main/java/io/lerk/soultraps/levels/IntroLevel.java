package io.lerk.soultraps.levels;

import io.lerk.soultraps.Player;
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
        addPlayer();
    }

    private void addPlayer() {
        StopWatch stopWatch = new StopWatch(StopWatch.LogLevel.DEBUG);
        stopWatch.start();
        Player player = Player.getSelf();
        int randomX = 0;
        int randomY = 0;
        final boolean[] goodTile = {false};
        while (!goodTile[0]) {
            randomX = new Random().nextInt(Level.LEVEL_WIDTH);
            randomY = new Random().nextInt(Level.LEVEL_HEIGHT);
            getObjectsAt(randomX, randomY, Tiles.Tile.class).forEach(t -> goodTile[0] = Tiles.evaluateSpawn(t));
        }
        addObject(player, randomX, randomY);
        stopWatch.stop("addPlayer()");
    }


}
