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
    Ground01("desert/ground01", false, Type.GROUND),

    /**
     * Ground tile.
     */
    Ground02("desert/ground02", false, Type.GROUND),

    /**
     * Cactus tile.
     */
    Cactus01("desert/cactus01", true, Type.PLANT),

    /**
     * Cactus tile.
     */
    Cactus02("desert/cactus02", true, Type.PLANT),

    /**
     * Tree tile.
     */
    Tree01("desert/tree01", true, Type.TREE),

    /**
     * Tree tile.
     */
    Tree02("desert/tree02", true, Type.TREE);

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
    DesertTiles(String name, Boolean blocking, Type type) {
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
