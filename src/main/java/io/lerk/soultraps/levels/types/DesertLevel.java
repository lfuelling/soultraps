package io.lerk.soultraps.levels.types;

import io.lerk.soultraps.levels.Level;
import io.lerk.soultraps.sys.StopWatch;
import io.lerk.soultraps.tiles.DesertTiles;
import io.lerk.soultraps.tiles.TileActor;
import io.lerk.soultraps.tiles.Tiles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Random;

import static io.lerk.soultraps.tiles.DesertTiles.Ground01;
import static io.lerk.soultraps.tiles.DesertTiles.Ground02;
import static io.lerk.soultraps.tiles.MiscTiles.Empty;

/**
 * Desert level.
 *
 * @author Lukas Fülling (lukas@k40s.net)
 */
public class DesertLevel extends Level {

    /**
     * Logger.
     */
    private static final Logger log = LoggerFactory.getLogger(DesertLevel.class);

    /**
     * {@inheritDoc}.
     */
    @Override
    protected void fillTiles() {
        log.debug("Generating level...");
        StopWatch watch = new StopWatch(StopWatch.LogLevel.DEBUG);
        watch.start();
        for (int widthCount = 0; widthCount < LEVEL_WIDTH; widthCount++) {
            String[] tmp = new String[LEVEL_HEIGHT];
            for (int heightCount = 0; heightCount < LEVEL_HEIGHT; heightCount++) {
                String randomTile = getRandomTile();
                if (randomTile.equals(Ground01.getName()) || randomTile.equals(Ground02.getName())) {
                    randomTile = Empty.getName(); // replace grass with empty
                }
                tmp[heightCount] = randomTile;
            }
            levelTiles[widthCount] = tmp;
        }
        watch.stop("fillTiles()");
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    protected TileActor randomGroundTile() {
        return (new Random().nextInt(2) == 0)
                ? Tiles.byName(Ground01.getName())
                : Tiles.byName(Ground02.getName());
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    protected String getRandomTile() {
        StopWatch watch = new StopWatch(StopWatch.LogLevel.DEBUG);
        watch.start();
        final String[] res = {null};
        while (res[0] == null) {
            Arrays.stream(DesertTiles.values()).forEach(t -> {
                if ((t.equals(Ground01) || t.equals(Ground02)) && new Random().nextInt(1) == 0) { // 50% chance of grass
                    res[0] = t.getName();
                } else if (new Random().nextInt(250) == 0) {
                    res[0] = t.getName();
                }
            });
        }
        watch.stop("getRandomTile()");
        return res[0];
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public LevelType getType() {
        return LevelType.DESERT;
    }

    /**
     * {@inheritDoc}.
     */
    public DesertLevel(String[][] tiles)
    {
        super(tiles);
    }

    /**
     * {@inheritDoc}.
     */
    public DesertLevel()
    {
        super();
    }
}
