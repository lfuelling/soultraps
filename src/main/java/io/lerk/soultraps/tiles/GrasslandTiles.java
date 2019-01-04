package io.lerk.soultraps.tiles;

/**
 * Enum that contains all possible grassland tiles.
 *
 * @author Lukas Fülling (lukas@k40s.net)
 */
public enum GrasslandTiles implements Tile {
    /**
     * Grass tile 1.
     */
    Grass01("grassland/grass01", false),

    /**
     * Grass tile 2.
     */
    Grass02("grassland/grass02", false),

    /**
     * Tree tile 1.
     */
    Tree01("grassland/tree01", false),

    /**
     * Tree tile 2.
     */
    Tree02("grassland/tree02", false),

    /**
     * Tree tile 3.
     */
    Tree03("grassland/tree03", false),

    /**
     * Stones tile 1.
     */
    Stones01("grassland/stones01", false),

    /**
     * Stones tile 2.
     */
    Stones02("grassland/stones02", false),

    /**
     * Stones tile 3.
     */
    Stones03("grassland/stones03", false),

    /**
     * Bush tile 1.
     */
    Bush01("grassland/bush01", false),

    /**
     * Bush tile 2.
     */
    Bush02("grassland/bush02", false),

    /**
     * Fern tile 1.
     */
    Fern01("grassland/fern01", false),

    /**
     * Fern tile 2.
     */
    Fern02("grassland/fern02", false),

    /**
     * Fern tile 3.
     */
    Fern03("grassland/fern03", false),

    /**
     * Fern tile 4.
     */
    Fern04("grassland/fern04", false),

    /**
     * Tree stump tile 1.
     */
    TreeStump01("grassland/treestump01", false),

    /**
     * Tree stump tile 2.
     */
    TreeStump02("grassland/treestump02", false),

    /**
     * Tree stump tile 3.
     */
    TreeStump03("grassland/treestump03", false);

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
    GrasslandTiles(String name, Boolean blocking) {
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
