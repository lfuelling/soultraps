package io.lerk.soultraps.tiles;

/**
 * Enum that contains all possible hell tiles.
 *
 * @author Lukas Fülling (lukas@k40s.net)
 */
public enum HellTiles implements Tile {
    /**
     * Ground tile.
     */
    HellGround01("hell/ground01", false),

    /**
     * Ground tile.
     */
    HellGround02("hell/ground02", false),

    /**
     * Tree tile.
     */
    HellTree01("hell/tree01", true),

    /**
     * Tree tile.
     */
    Lava("hell/lava/lava01", false);

    /**
     * Tile name.
     */
    private final String name;

    /**
     * Decides if the player can walk over the tile.
     */
    private final Boolean blocking;

    /**
     * Constructor.
     *
     * @param name     tile name
     * @param blocking decides if the player can walk over the tile
     */
    HellTiles(String name, Boolean blocking) {
        this.name = name;
        this.blocking = blocking;
    }

    /**
     * Getter for tile name.
     *
     * @return tile name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Getter for blocking.
     *
     * @return blocking
     */
    @Override
    public Boolean isBlocking() {
        return blocking;
    }
}
