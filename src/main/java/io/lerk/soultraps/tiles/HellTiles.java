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
    HellGround01("hell/ground01", false, Type.GROUND),

    /**
     * Ground tile.
     */
    HellGround02("hell/ground02", false, Type.GROUND),

    /**
     * Tree tile.
     */
    HellTree01("hell/tree01", true, Type.TREE),

    /**
     * Tree tile.
     */
    Lava("hell/lava/lava01", false, Type.FLOWING);

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
     *  @param name     tile name
     * @param blocking decides if the player can walk over the tile
     * @param type the tile type
     */
    HellTiles(String name, Boolean blocking, Type type) {
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
     * @return blocking
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
