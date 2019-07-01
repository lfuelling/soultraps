package io.lerk.soultraps.levels;

import greenfoot.World;
import io.lerk.soultraps.mobs.stat1c.HellCastle;
import io.lerk.soultraps.sys.console.ConsoleUtil;
import io.lerk.soultraps.levels.types.LevelType;
import io.lerk.soultraps.mobs.BaseMob;
import io.lerk.soultraps.mobs.Player;
import io.lerk.soultraps.mobs.stat1c.Floppy;
import io.lerk.soultraps.mobs.stat1c.Portal;
import io.lerk.soultraps.sys.StopWatch;
import io.lerk.soultraps.tiles.TileActor;
import io.lerk.soultraps.tiles.Tiles;
import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.UUID;

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
    protected final LevelType type = LevelType.UNDEFINED; //FIXME actually use this

    /**
     * Coordinates of the {@link Portal} in this level.
     */
    protected Pair<Integer, Integer> portalCoordinates; //FIXME actually use this

    /**
     * Coordinates of the {@link Floppy} in this level.
     */
    protected Pair<Integer, Integer> floppyCoordinates; //FIXME actually use this

    private String uniqueId = UUID.randomUUID().toString();

    /**
     * Constructor.
     */
    public Level() {
        super(BASE_WIDTH, BASE_HEIGHT, CELL_SIZE);
        drawGround();
        fillTiles();
        renderViewportItems();
        this.portalCoordinates = null;
        this.floppyCoordinates = null;
    }

    @Override
    public final void act() {
        super.act();
        ConsoleUtil.handle(this);
    }

    /**
     * Constructor that allows the level to be constructed using a predefined set of tiles.
     *
     * @param tiles the tiles to use
     */
    public Level(String[][] tiles, Pair<Integer, Integer> portalCoordinates, Pair<Integer, Integer> floppyCoordinates) {
        super(BASE_WIDTH, BASE_HEIGHT, CELL_SIZE);
        this.portalCoordinates = portalCoordinates;
        this.floppyCoordinates = floppyCoordinates;
        drawGround();
        for (int i = 0; i < tiles.length; i++) {
            for (int i1 = 0; i1 < tiles[i].length; i1++) {
                this.levelTiles[i][i1] = tiles[i][i1];
            }
        }
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
    public void addMob(BaseMob mob) {
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
        if (mob instanceof Player) {
            addObject(mob, randomX, randomY);
            return;
        } else {
            if (mob instanceof Portal && portalCoordinates != null) {
                addObject(mob, portalCoordinates.getKey(), portalCoordinates.getValue());
                return;
            } else if (mob instanceof Floppy && floppyCoordinates != null) {
                return;
            } else if (mob instanceof HellCastle) {
                addHellCastle(mob, randomX, randomY);
                return;
            } else {
                addObject(mob, randomX, randomY);
            }
        }
        if (mob instanceof Portal) {
            portalCoordinates = new Pair<>(mob.getX(), mob.getY());
        } else if (mob instanceof Floppy) {
            floppyCoordinates = new Pair<>(mob.getX(), mob.getY());
        }
        stopWatch.stop("addMob(" + mob.getClass().

                getName() + ")");
    }

    private void addHellCastle(BaseMob mob, int randomX, int randomY) {
        int width = mob.getImage().getWidth() / Level.CELL_SIZE;
        int height = mob.getImage().getHeight() / Level.CELL_SIZE;
        if (randomX < width) {
            randomX += width;
        } else if (randomX + width > Level.LEVEL_WIDTH) {
            randomX = randomX - width;
        }
        if (randomY < height) {
            randomY += height;
        } else if (randomY + height > Level.LEVEL_HEIGHT) {
            randomY = randomY - height;
        }
        addObject(mob, randomX, randomY);
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

    /**
     * Getter for portal coordinates.
     *
     * @return the coordinates of the portal.
     */
    public Pair<Integer, Integer> getPortalCoordinates() {
        return portalCoordinates;
    }

    /**
     * Getter for floppy coordinates.
     *
     * @return the coordinates of the floppy.
     */
    public Pair<Integer, Integer> getFloppyCoordinates() {
        return floppyCoordinates;
    }

    public String getUniqueId() {
        return uniqueId;
    }
}
