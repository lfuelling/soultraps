package io.lerk.soultraps.sys;

import greenfoot.Actor;
import greenfoot.GreenfootImage;

public enum Tiles {
    Grass01("grass01"),
    Grass02("grass02"),
    Tree01("tree01"),
    Tree02("tree02"),
    Tree03("tree03"),
    Stones01("stones01"),
    Stones02("stones02"),
    Stones03("stones03"),
    Bush01("bush01"),
    Bush02("bush02"),
    Fern01("fern01"),
    Fern02("fern02"),
    Fern03("fern03"),
    Fern04("fern04"),
    TreeStump01("treestump01"),
    TreeStump02("treestump02"),
    TreeStump03("treestump03"),
    Empty("empty");

    private final String name;

    Tiles(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * Checks a given tile for fulfilling spawnpoint requirements.
     * @param t the tile
     * @return true if the tile is okay to use as spawn
     */
    public static boolean evaluateSpawn(Tiles.Tile t) {
        boolean grassOrEmpty = t.getTileType().equals(Tiles.Grass01) ||
                t.getTileType().equals(Tiles.Grass02) ||
                t.getTileType().equals(Tiles.Empty);
        return grassOrEmpty && !t.isOverlappingTileOtherThanEmptyOrGrass();
    }

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

    public static class Tile extends Actor {
        private final Tiles tileType;

        Tile(Tiles tileType, GreenfootImage image) {
            this.tileType = tileType;
            setImage(image);
        }

        public Tiles getTileType() {
            return tileType;
        }

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