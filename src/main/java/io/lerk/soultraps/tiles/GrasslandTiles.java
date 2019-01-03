package io.lerk.soultraps.tiles;

/**
 * Enum that contains all possible tiles.
 *
 * @author Lukas Fülling (lukas@k40s.net)
 */
public enum GrasslandTiles implements Tile {
    /**
     * Grass tile 1.
     */
    Grass01("grassland/grass01"),

    /**
     * Grass tile 2.
     */
    Grass02("grassland/grass02"),

    /**
     * Tree tile 1.
     */
    Tree01("grassland/tree01"),

    /**
     * Tree tile 2.
     */
    Tree02("grassland/tree02"),

    /**
     * Tree tile 3.
     */
    Tree03("grassland/tree03"),

    /**
     * Stones tile 1.
     */
    Stones01("grassland/stones01"),

    /**
     * Stones tile 2.
     */
    Stones02("grassland/stones02"),

    /**
     * Stones tile 3.
     */
    Stones03("grassland/stones03"),

    /**
     * Bush tile 1.
     */
    Bush01("grassland/bush01"),

    /**
     * Bush tile 2.
     */
    Bush02("grassland/bush02"),

    /**
     * Fern tile 1.
     */
    Fern01("grassland/fern01"),

    /**
     * Fern tile 2.
     */
    Fern02("grassland/fern02"),

    /**
     * Fern tile 3.
     */
    Fern03("grassland/fern03"),

    /**
     * Fern tile 4.
     */
    Fern04("grassland/fern04"),

    /**
     * Tree stump tile 1.
     */
    TreeStump01("grassland/treestump01"),

    /**
     * Tree stump tile 2.
     */
    TreeStump02("grassland/treestump02"),

    /**
     * Tree stump tile 3.
     */
    TreeStump03("grassland/treestump03");

    /**
     * TileActor name.
     */
    private final String name;

    /**
     * Constructor.
     *
     * @param name tile name
     */
    GrasslandTiles(String name) {
        this.name = name;
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

}
