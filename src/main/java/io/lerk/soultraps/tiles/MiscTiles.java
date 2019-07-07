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
    Empty("empty", false, Type.OTHER);

    /**
     * Tile name.
     */
    private final String name;

    /**
     * Decides if the player can walk over the tile.
     */
    private final Boolean blocking;

    private final Type type;

    /**
     * Constructor.
     *
     *  @param name     tile name
     * @param blocking decides if the player can walk over the tile
     * @param type the tile type
     */
    MiscTiles(String name, Boolean blocking, Type type) {
        this.name = name;
        this.blocking = blocking;
        this.type = type;
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

    @Override
    public Type getType() {
        return type;
    }
}
