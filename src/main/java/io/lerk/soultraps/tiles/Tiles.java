package io.lerk.soultraps.tiles;

import greenfoot.GreenfootImage;

import static io.lerk.soultraps.tiles.DesertTiles.*;
import static io.lerk.soultraps.tiles.GrasslandTiles.*;
import static io.lerk.soultraps.tiles.HellTiles.HellGround01;
import static io.lerk.soultraps.tiles.HellTiles.HellGround02;
import static io.lerk.soultraps.tiles.MiscTiles.Empty;

/**
 * Utility class for tiles.
 *
 * @author Lukas Fülling (lukas@k40s.net)
 */
public class Tiles {

    /**
     * File suffix.
     */
    public static final String FILE_SUFFIX = ".png";

    /**
     * Checks a given tile for fulfilling spawnpoint requirements.
     *
     * @param t the tile
     * @return true if the tile is okay to use as spawn
     */
    public static boolean evaluateSpawn(TileActor t) {
        return !t.isTileOtherThanGround() && !t.isOverlappingTileOtherThanEmptyOrGround();
    }

    /**
     * Finds a tile by name.
     *
     * @param name the name of the tile to find
     * @return the file or null
     */
    public static TileActor byName(String name) {
        if (name != null) {
            //noinspection IfCanBeSwitch switch won't work with "variables" 👀
            if (name.equals(Grass01.getName())) {
                return new TileActor(Grass01, new GreenfootImage(name + FILE_SUFFIX));
            } else if (name.equals(Grass02.getName())) {
                return new TileActor(Grass02, new GreenfootImage(name + FILE_SUFFIX));
            } else if (name.equals(GrasslandTiles.Tree01.getName())) {
                return new TileActor(GrasslandTiles.Tree01, new GreenfootImage(name + FILE_SUFFIX));
            } else if (name.equals(GrasslandTiles.Tree02.getName())) {
                return new TileActor(GrasslandTiles.Tree02, new GreenfootImage(name + FILE_SUFFIX));
            } else if (name.equals(Tree03.getName())) {
                return new TileActor(Tree03, new GreenfootImage(name + FILE_SUFFIX));
            } else if (name.equals(Stones01.getName())) {
                return new TileActor(Stones01, new GreenfootImage(name + FILE_SUFFIX));
            } else if (name.equals(Stones02.getName())) {
                return new TileActor(Stones02, new GreenfootImage(name + FILE_SUFFIX));
            } else if (name.equals(Stones03.getName())) {
                return new TileActor(Stones03, new GreenfootImage(name + FILE_SUFFIX));
            } else if (name.equals(Bush01.getName())) {
                return new TileActor(Bush01, new GreenfootImage(name + FILE_SUFFIX));
            } else if (name.equals(Bush02.getName())) {
                return new TileActor(Bush02, new GreenfootImage(name + FILE_SUFFIX));
            } else if (name.equals(Fern01.getName())) {
                return new TileActor(Fern01, new GreenfootImage(name + FILE_SUFFIX));
            } else if (name.equals(Fern02.getName())) {
                return new TileActor(Fern02, new GreenfootImage(name + FILE_SUFFIX));
            } else if (name.equals(Fern03.getName())) {
                return new TileActor(Fern03, new GreenfootImage(name + FILE_SUFFIX));
            } else if (name.equals(Fern04.getName())) {
                return new TileActor(Fern04, new GreenfootImage(name + FILE_SUFFIX));
            } else if (name.equals(TreeStump01.getName())) {
                return new TileActor(TreeStump01, new GreenfootImage(name + FILE_SUFFIX));
            } else if (name.equals(TreeStump02.getName())) {
                return new TileActor(TreeStump02, new GreenfootImage(name + FILE_SUFFIX));
            } else if (name.equals(TreeStump03.getName())) {
                return new TileActor(TreeStump03, new GreenfootImage(name + FILE_SUFFIX));
            } else if (name.equals(Empty.getName())) {
                return new TileActor(Empty, new GreenfootImage(name + FILE_SUFFIX));
            } else if (name.equals(Ground01.getName())) {
                return new TileActor(Ground01, new GreenfootImage(name + FILE_SUFFIX));
            } else if (name.equals(Ground02.getName())) {
                return new TileActor(Ground02, new GreenfootImage(name + FILE_SUFFIX));
            } else if (name.equals(Cactus01.getName())) {
                return new TileActor(Cactus01, new GreenfootImage(name + FILE_SUFFIX));
            } else if (name.equals(Cactus02.getName())) {
                return new TileActor(Cactus02, new GreenfootImage(name + FILE_SUFFIX));
            } else if (name.equals(DesertTiles.Tree01.getName())) {
                return new TileActor(DesertTiles.Tree01, new GreenfootImage(name + FILE_SUFFIX));
            } else if (name.equals(DesertTiles.Tree02.getName())) {
                return new TileActor(DesertTiles.Tree02, new GreenfootImage(name + FILE_SUFFIX));
            } else if (name.equals(HellGround01.getName())) {
                return new TileActor(HellGround01, new GreenfootImage(name + FILE_SUFFIX));
            } else if (name.equals(HellGround02.getName())) {
                return new TileActor(HellGround02, new GreenfootImage(name + FILE_SUFFIX));
            } else if (name.equals(HellTiles.HellTree01.getName())) {
                return new TileActor(HellTiles.HellTree01, new GreenfootImage(name + FILE_SUFFIX));
            } else if (name.equals(HellTiles.Lava.getName())) {
                return new LavaActor();
            }
        }
        return null;
    }
}