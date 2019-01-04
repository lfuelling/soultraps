package io.lerk.soultraps.levels;

import greenfoot.World;
import io.lerk.soultraps.levels.types.LevelType;
import io.lerk.soultraps.mobs.BaseMob;
import io.lerk.soultraps.sys.StopWatch;
import io.lerk.soultraps.tiles.TileActor;
import io.lerk.soultraps.tiles.Tiles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

import static io.lerk.soultraps.levels.menu.Launcher.BASE_HEIGHT;
import static io.lerk.soultraps.levels.menu.Launcher.BASE_WIDTH;
import static io.lerk.soultraps.tiles.Tiles.byName;

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
    protected final String[][] levelTiles = new String[LEVEL_WIDTH][LEVEL_HEIGHT];

    /**
     * Level type.
     */
    protected final LevelType type = LevelType.UNDEFINED;

    /**
     * Constructor.
     */
    public Level() {
        super(BASE_WIDTH, BASE_HEIGHT, CELL_SIZE);
        drawGround();
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
                TileActor tile = byName(levelTiles[widthCount][heightCount]);
                addObject(tile,
                        ((getWidth()) * widthCount) / (getCellSize() * 4), // x
                        ((getHeight()) * heightCount) / (getCellSize() * 2)); // y
                if (tile.isOverlappingTileOtherThanEmptyOrGround()) {
                    removeObject(tile); // remove overlapping tiles
                }
            }
        }
        watch.stop("renderViewportItems()");
    }

    /**
     * Populates {@link #levelTiles} with random tiles.
     */
    protected abstract void fillTiles();

    /**
     * Draws ground across the {@link #levelTiles}.
     */
    private void drawGround() {
        log.debug("Drawing ground...");
        StopWatch watch = new StopWatch(StopWatch.LogLevel.DEBUG);
        watch.start();
        for (int widthCount = 0; widthCount < getWidth(); widthCount++) {
            for (int heightCount = 0; heightCount < getHeight(); heightCount++) {
                addObject(randomGroundTile(),
                        ((getWidth()) * widthCount) / (getCellSize() * 4), // x
                        ((getHeight()) * heightCount) / (getCellSize() * 2)); // y
            }
        }
        watch.stop("drawGround()");
    }

    /**
     * Generate a random ground tile.
     *
     * @return a random ground tile.
     */
    protected abstract TileActor randomGroundTile();

    /**
     * Generates a random tile name.
     *
     * @return a new random tile name.
     */
    protected abstract String getRandomTile();

    /**
     * Adds a mob at a random "free" (of trees, bushes, stones, etc.) tile.
     * This method also runs a {@link StopWatch} that logs the added mob at debug level.
     *
     * @param mob the mob to add.
     */
    protected void addMob(BaseMob mob) {
        StopWatch stopWatch = new StopWatch(StopWatch.LogLevel.DEBUG);
        stopWatch.start();
        int randomX = 0;
        int randomY = 0;
        final boolean[] goodTile = {false};
        while (!goodTile[0]) {
            randomX = new Random().nextInt(Level.LEVEL_WIDTH);
            randomY = new Random().nextInt(Level.LEVEL_HEIGHT);
            getObjectsAt(randomX, randomY, TileActor.class).forEach(t -> goodTile[0] = Tiles.evaluateSpawn(t));
        }
        addObject(mob, randomX, randomY);
        stopWatch.stop("addMob(" + mob.getClass().getName() + ")");
    }

    /**
     * Getter for levelTiles.
     *
     * @return level tiles
     */
    public String[][] getLevelTiles() {
        return levelTiles;
    }

    /**
     * Getter for level type.
     *
     * @return level type
     */
    public abstract LevelType getType();
}
