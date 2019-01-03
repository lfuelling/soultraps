package io.lerk.soultraps.tiles;

public enum MiscTiles implements Tile {
    /**
     * Empty tile.
     */
    Empty("empty");

    private final String name;

    MiscTiles(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
