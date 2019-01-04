package io.lerk.soultraps.tiles;

import greenfoot.GreenfootImage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.lerk.soultraps.tiles.DesertTiles.Ground01;
import static io.lerk.soultraps.tiles.GrasslandTiles.Grass01;
import static io.lerk.soultraps.tiles.GrasslandTiles.Tree01;
import static io.lerk.soultraps.tiles.MiscTiles.Empty;

class TileActorTest {

    /**
     * A grass tile.
     */
    private TileActor grassTile = new TileActor(Grass01, new GreenfootImage(Grass01.getName() + Tiles.FILE_SUFFIX));

    /**
     * A desert grass tile.
     */
    private TileActor desertTile = new TileActor(Ground01, new GreenfootImage(Ground01.getName() + Tiles.FILE_SUFFIX));

    /**
     * An empty tile.
     */
    private TileActor emptyTile = new TileActor(Empty, new GreenfootImage(Empty.getName() + Tiles.FILE_SUFFIX));

    /**
     * A tree tile.
     */
    private TileActor treeTile = new TileActor(Tree01, new GreenfootImage(Tree01.getName() + Tiles.FILE_SUFFIX));

    /**
     * A desert tree tile.
     */
    private TileActor desertTreeTile = new TileActor(DesertTiles.Tree01, new GreenfootImage(DesertTiles.Tree01.getName() + Tiles.FILE_SUFFIX));


    @Test
    void getTileType() {
        Assertions.assertEquals(grassTile.getTileType(), Grass01);
        Assertions.assertEquals(desertTile.getTileType(), Ground01);
        Assertions.assertEquals(emptyTile.getTileType(), Empty);
        Assertions.assertEquals(treeTile.getTileType(), Tree01);
        Assertions.assertEquals(desertTreeTile.getTileType(), DesertTiles.Tree01);
    }

    @Test
    void isOverlappingTileOtherThanEmptyOrGround() {

    }

    @Test
    void isTileOtherThanGround() {
    }
}