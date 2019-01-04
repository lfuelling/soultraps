package io.lerk.soultraps.tiles;

/**
 * Enum that contains all possible desert tiles.
 *
 * @author Lukas Fülling (lukas@k40s.net)
 */
public enum DesertTiles implements Tile {

    /**
     * Ground tile.
     */
    Ground01("desert/ground01", false),

    /**
     * Ground tile.
     */
    Ground02("desert/ground02", false),

    /**
     * Cactus tile.
     */
    Cactus01("desert/cactus01", true),

    /**
     * Cactus tile.
     */
    Cactus02("desert/cactus02", true),

    /**
     * Tree tile.
     */
    Tree01("desert/tree01", true),

    /**
     * Tree tile.
     */
    Tree02("desert/tree02", true);

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
    DesertTiles(String name, Boolean blocking) {
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
