package io.lerk.soultraps.tiles;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

import static io.lerk.soultraps.tiles.DesertTiles.Ground01;
import static io.lerk.soultraps.tiles.DesertTiles.Ground02;
import static io.lerk.soultraps.tiles.GrasslandTiles.Grass01;
import static io.lerk.soultraps.tiles.GrasslandTiles.Grass02;
import static io.lerk.soultraps.tiles.MiscTiles.Empty;


/**
 * TileActor class.
 *
 * @author Lukas Fülling (lukas@k40s.net)
 */
public class TileActor extends Actor {

    /**
     * TileActor type.
     */
    private final Tile tileType;

    /**
     * Constructor.
     *
     * @param tileType tile type
     * @param image    tile image
     */
    TileActor(Tile tileType, GreenfootImage image) {
        this.tileType = tileType;
        setImage(image);
    }

    /**
     * Getter for tile type.
     *
     * @return tile type
     */
    public Tile getTileType() {
        return tileType;
    }

    /**
     * Checks if the tile is overlapping something else than ground or empty tiles.
     *
     * @return true if there is such a tile overlapping the current tile
     */
    public boolean isOverlappingTileOtherThanEmptyOrGround() {
        final boolean[] res = {false};
        if (getIntersectingObjects(TileActor.class).size() != 0) {
            getIntersectingObjects(TileActor.class).forEach(t -> {
                if (!isTileOtherThanGround()) {
                    res[0] = true;
                }
            });
        }
        return res[0];
    }

    /**
     * Checks if current tile is other than ground.
     *
     * @return true if not ground
     */
    public boolean isTileOtherThanGround() {
        return !tileType.equals(Grass01) &&
                !tileType.equals(Grass02) &&
                !tileType.equals(Empty) &&
                !tileType.equals(Ground01) &&
                !tileType.equals(Ground02);
    }
}