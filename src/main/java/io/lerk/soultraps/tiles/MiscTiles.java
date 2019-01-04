package io.lerk.soultraps.tiles;

/**
 * Enum that contains some misc tiles.
 *
 * @author Lukas Fülling (lukas@k40s.net)
 */
public enum MiscTiles implements Tile {

    /**
     * Empty tile.
     */
    Empty("empty", false);

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
    MiscTiles(String name, Boolean blocking) {
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
     * @return blocking.
     */
    @Override
    public Boolean isBlocking() {
        return blocking;
    }
}
