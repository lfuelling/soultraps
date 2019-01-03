package io.lerk.soultraps.levels;

import greenfoot.World;
import io.lerk.soultraps.components.HUD;
import io.lerk.soultraps.sys.StopWatch;
import io.lerk.soultraps.sys.Tiles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Random;

import static io.lerk.soultraps.levels.Launcher.BASE_HEIGHT;
import static io.lerk.soultraps.levels.Launcher.BASE_WIDTH;
import static io.lerk.soultraps.sys.Tiles.*;

/**
 * Base level class with word generation logic.
 *
 * @author Lukas Fülling (lukas@k40s.net)
 */
public abstract class Level extends World {

    /**
     * Base world width.
     */
    public static final int LEVEL_WIDTH = 128;

    /**
     * Base world height.
     */
    public static final int LEVEL_HEIGHT = 128;

    /**
     * Level cell size.
     */
    public static final int CELL_SIZE = 16;

    /**
     * Logger.
     */
    private static final Logger log = LoggerFactory.getLogger(Level.class);

    /**
     * Table containing all the level tiles.
     */
    private final String[][] levelTiles = new String[LEVEL_WIDTH][LEVEL_HEIGHT];

    /**
     * Constructor.
     */
    public Level() {
        super(BASE_WIDTH, BASE_HEIGHT, CELL_SIZE);
        drawGrass();
        fillTiles();
        renderViewportItems();
    }

    /**
     * Renders the tiles contained in {@link #levelTiles}.
     */
    private void renderViewportItems() {
        log.debug("Rendering viewport...");
        StopWatch watch = new StopWatch(StopWatch.LogLevel.DEBUG);
        watch.start();
        for (int widthCount = 0; widthCount < getWidth(); widthCount++) {
            for (int heightCount = 0; heightCount < getHeight(); heightCount++) {
                Tiles.Tile tile = byName(levelTiles[widthCount][heightCount]);
                addObject(tile,
                        ((getWidth()) * widthCount) / (getCellSize() * 4), // x
                        ((getHeight()) * heightCount) / (getCellSize() * 2)); // y
                if (tile.isOverlappingTileOtherThanEmptyOrGrass()) {
                    removeObject(tile); // remove overlapping tiles
                }
            }
        }
        watch.stop("renderViewportItems()");
    }

    /**
     * Populates {@link #levelTiles} with random tiles.
     */
    private void fillTiles() {
        log.debug("Generating level...");
        StopWatch watch = new StopWatch(StopWatch.LogLevel.DEBUG);
        watch.start();
        for (int widthCount = 0; widthCount < LEVEL_WIDTH; widthCount++) {
            String[] tmp = new String[LEVEL_HEIGHT];
            for (int heightCount = 0; heightCount < LEVEL_HEIGHT; heightCount++) {
                String randomTile = getRandomTile();
                if (randomTile.equals(Grass01.getName()) || randomTile.equals(Grass02.getName())) {
                    randomTile = Empty.getName(); // replace grass with empty
                }
                tmp[heightCount] = randomTile;
            }
            levelTiles[widthCount] = tmp;
        }
        watch.stop("fillTiles()");
    }

    /**
     * Draws grass across the {@link #levelTiles}.
     */
    private void drawGrass() {
        log.debug("Drawing grass...");
        StopWatch watch = new StopWatch(StopWatch.LogLevel.DEBUG);
        watch.start();
        for (int widthCount = 0; widthCount < getWidth(); widthCount++) {
            for (int heightCount = 0; heightCount < getHeight(); heightCount++) {
                addObject(randomGrassTile(),
                        ((getWidth()) * widthCount) / (getCellSize() * 4), // x
                        ((getHeight()) * heightCount) / (getCellSize() * 2)); // y
            }
        }
        watch.stop("drawGrass()");
    }

    /**
     * Generate a random grass tile.
     *
     * @return a random grass tile.
     */
    private Tiles.Tile randomGrassTile() {
        return (new Random().nextInt(2) == 0)
                ? byName(Grass01.getName())
                : byName(Grass02.getName());
    }

    /**
     * Generates a random tile name.
     *
     * @return a new random tile name.
     */
    private String getRandomTile() {
        StopWatch watch = new StopWatch(StopWatch.LogLevel.DEBUG);
        watch.start();
        final String[] res = {null};
        while (res[0] == null) {
            Arrays.stream(Tiles.values()).forEach(t -> {
                if ((t.equals(Grass01) || t.equals(Grass02)) && new Random().nextInt(2) == 0) { // 50% chance of grass
                    res[0] = t.getName();
                } else if (new Random().nextInt(250) == 0) {
                    res[0] = t.getName();
                }
            });
        }
        watch.stop("getRandomTile()");
        return res[0];
    }
}
