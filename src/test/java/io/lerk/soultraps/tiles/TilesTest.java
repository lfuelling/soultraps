package io.lerk.soultraps.tiles;

import greenfoot.GreenfootImage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.lerk.soultraps.tiles.DesertTiles.Ground01;
import static io.lerk.soultraps.tiles.GrasslandTiles.Grass01;
import static io.lerk.soultraps.tiles.GrasslandTiles.Tree01;
import static io.lerk.soultraps.tiles.MiscTiles.Empty;

/**
 * Tests for the tiles utility.
 */
class TilesTest {

    /**
     * A grass tile. A valid spawn.
     */
    private TileActor grassTile = new TileActor(Grass01, new GreenfootImage(Grass01.getName() + Tiles.FILE_SUFFIX));

    /**
     * A desert grass tile. A valid spawn.
     */
    private TileActor desertTile = new TileActor(Ground01, new GreenfootImage(Ground01.getName() + Tiles.FILE_SUFFIX));

    /**
     * An empty tile. A valid spawn.
     */
    private TileActor emptyTile = new TileActor(Empty, new GreenfootImage(Empty.getName() + Tiles.FILE_SUFFIX));

    /**
     * A tree tile. Not a valid spawn.
     */
    private TileActor treeTile = new TileActor(Tree01, new GreenfootImage(Tree01.getName() + Tiles.FILE_SUFFIX));

    /**
     * A desert tree tile. Not a valid spawn.
     */
    private TileActor desertTreeTile = new TileActor(DesertTiles.Tree01, new GreenfootImage(DesertTiles.Tree01.getName() + Tiles.FILE_SUFFIX));

    /**
     * Test method for {@link Tiles#evaluateSpawn(TileActor)}.
     */
    @Test
    void evaluateSpawn() {
        Assertions.assertTrue(Tiles.evaluateSpawn(grassTile));
        Assertions.assertTrue(Tiles.evaluateSpawn(desertTile));
        Assertions.assertTrue(Tiles.evaluateSpawn(emptyTile));
        Assertions.assertTrue(!Tiles.evaluateSpawn(treeTile));
        Assertions.assertTrue(!Tiles.evaluateSpawn(desertTreeTile));
    }

    /**
     * Test method for {@link Tiles#byName(String)}.
     */
    @Test
    void byName() {
        Assertions.assertEquals(grassTile.getTileType(), Tiles.byName(Grass01.getName()).getTileType());
        Assertions.assertEquals(desertTile.getTileType(), Tiles.byName(Ground01.getName()).getTileType());
        Assertions.assertEquals(emptyTile.getTileType(), Tiles.byName(Empty.getName()).getTileType());
        Assertions.assertEquals(treeTile.getTileType(), Tiles.byName(Tree01.getName()).getTileType());
        Assertions.assertEquals(desertTreeTile.getTileType(), Tiles.byName(DesertTiles.Tree01.getName()).getTileType());
    }
}