package io.lerk.soultraps.sys;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

/**
 * Enum that contains all possible tiles.
 *
 * @author Lukas Fülling (lukas@k40s.net)
 */
public enum Tiles {
    /**
     * Grass tile 1.
     */
    Grass01("grass01"),

    /**
     * Grass tile 2.
     */
    Grass02("grass02"),

    /**
     * Tree tile 1.
     */
    Tree01("tree01"),

    /**
     * Tree tile 2.
     */
    Tree02("tree02"),

    /**
     * Tree tile 3.
     */
    Tree03("tree03"),

    /**
     * Stones tile 1.
     */
    Stones01("stones01"),

    /**
     * Stones tile 2.
     */
    Stones02("stones02"),

    /**
     * Stones tile 3.
     */
    Stones03("stones03"),

    /**
     * Bush tile 1.
     */
    Bush01("bush01"),

    /**
     * Bush tile 2.
     */
    Bush02("bush02"),

    /**
     * Fern tile 1.
     */
    Fern01("fern01"),

    /**
     * Fern tile 2.
     */
    Fern02("fern02"),

    /**
     * Fern tile 3.
     */
    Fern03("fern03"),

    /**
     * Fern tile 4.
     */
    Fern04("fern04"),

    /**
     * Tree stump tile 1.
     */
    TreeStump01("treestump01"),

    /**
     * Tree stump tile 2.
     */
    TreeStump02("treestump02"),

    /**
     * Tree stump tile 3.
     */
    TreeStump03("treestump03"),

    /**
     * Empty tile.
     */
    Empty("empty");

    /**
     * Tile name.
     */
    private final String name;

    /**
     * Constructor.
     *
     * @param name tile name
     */
    Tiles(String name) {
        this.name = name;
    }

    /**
     * Getter for tile name.
     *
     * @return tile name
     */
    public String getName() {
        return name;
    }

    /**
     * Checks a given tile for fulfilling spawnpoint requirements.
     *
     * @param t the tile
     * @return true if the tile is okay to use as spawn
     */
    public static boolean evaluateSpawn(Tiles.Tile t) {
        boolean grassOrEmpty = t.getTileType().equals(Tiles.Grass01) ||
                t.getTileType().equals(Tiles.Grass02) ||
                t.getTileType().equals(Tiles.Empty);
        return grassOrEmpty && !t.isOverlappingTileOtherThanEmptyOrGrass();
    }

    /**
     * Finds a tile by name.
     *
     * @param name the name of the tile to find
     * @return the file or null
     */
    public static Tile byName(String name) {
        if (name != null) {
            //noinspection IfCanBeSwitch switch won't work with "variables" 👀
            if (name.equals(Grass01.getName())) {
                return new Tile(Grass01, new GreenfootImage("grass01.png"));
            } else if (name.equals(Grass02.getName())) {
                return new Tile(Grass02, new GreenfootImage("grass02.png"));
            } else if (name.equals(Tree01.getName())) {
                return new Tile(Tree01, new GreenfootImage("tree01.png"));
            } else if (name.equals(Tree02.getName())) {
                return new Tile(Tree02, new GreenfootImage("tree02.png"));
            } else if (name.equals(Tree03.getName())) {
                return new Tile(Tree03, new GreenfootImage("tree03.png"));
            } else if (name.equals(Stones01.getName())) {
                return new Tile(Stones01, new GreenfootImage("stones01.png"));
            } else if (name.equals(Stones02.getName())) {
                return new Tile(Stones02, new GreenfootImage("stones02.png"));
            } else if (name.equals(Stones03.getName())) {
                return new Tile(Stones03, new GreenfootImage("stones03.png"));
            } else if (name.equals(Bush01.getName())) {
                return new Tile(Bush01, new GreenfootImage("bush01.png"));
            } else if (name.equals(Bush02.getName())) {
                return new Tile(Bush02, new GreenfootImage("bush02.png"));
            } else if (name.equals(Fern01.getName())) {
                return new Tile(Fern01, new GreenfootImage("fern01.png"));
            } else if (name.equals(Fern02.getName())) {
                return new Tile(Fern02, new GreenfootImage("fern02.png"));
            } else if (name.equals(Fern03.getName())) {
                return new Tile(Fern03, new GreenfootImage("fern03.png"));
            } else if (name.equals(Fern04.getName())) {
                return new Tile(Fern04, new GreenfootImage("fern04.png"));
            } else if (name.equals(TreeStump01.getName())) {
                return new Tile(TreeStump01, new GreenfootImage("treestump01.png"));
            } else if (name.equals(TreeStump02.getName())) {
                return new Tile(TreeStump02, new GreenfootImage("treestump02.png"));
            } else if (name.equals(TreeStump03.getName())) {
                return new Tile(TreeStump03, new GreenfootImage("treestump03.png"));
            } else if (name.equals(Empty.getName())) {
                return new Tile(Empty, new GreenfootImage("empty.png"));
            }
        }
        return null;
    }

    /**
     * Tile class.
     *
     * @author Lukas Fülling (lukas@k40s.net)
     */
    public static class Tile extends Actor {
        /**
         * Tile type.
         */
        private final Tiles tileType;

        /**
         * Constructor.
         *
         * @param tileType tile type
         * @param image    tile image
         */
        Tile(Tiles tileType, GreenfootImage image) {
            this.tileType = tileType;
            setImage(image);
        }

        /**
         * Getter for tile type.
         *
         * @return tile type
         */
        public Tiles getTileType() {
            return tileType;
        }

        /**
         * Checks if the tile is overlapping somethign else than grass or empty tiles.
         *
         * @return true if there is such a tile overlapping the current tile
         */
        public boolean isOverlappingTileOtherThanEmptyOrGrass() {
            final boolean[] res = {false};
            if (getIntersectingObjects(Tile.class).size() != 0) {
                getIntersectingObjects(Tile.class).forEach(t -> {
                    if (!t.tileType.equals(Grass01) && !t.tileType.equals(Grass02) && !t.tileType.equals(Empty)) {
                        res[0] = true;
                    }
                });
            }
            return res[0];
        }
    }
}