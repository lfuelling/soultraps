package io.lerk.soultraps.tiles;

public enum DesertTiles implements Tile {
    Ground01("desert/ground01"),
    Ground02("desert/ground02"),
    Cactus01("desert/cactus01"),
    Cactus02("desert/cactus02"),
    Tree01("desert/tree01"),
    Tree02("desert/tree02");

    private final String name;

    DesertTiles(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
