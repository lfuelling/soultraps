package io.lerk.soultraps.tiles;

import greenfoot.GreenfootImage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.lerk.soultraps.tiles.DesertTiles.Ground01;
import static io.lerk.soultraps.tiles.GrasslandTiles.Grass01;
import static io.lerk.soultraps.tiles.GrasslandTiles.Tree01;
import static io.lerk.soultraps.tiles.HellTiles.HellGround01;
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
     * A desert ground tile. A valid spawn.
     */
    private TileActor desertTile = new TileActor(Ground01, new GreenfootImage(Ground01.getName() + Tiles.FILE_SUFFIX));

    /**
     * A hell ground tile. A valid spawn.
     */
    private TileActor hellTile = new TileActor(HellGround01, new GreenfootImage(HellGround01.getName() + Tiles.FILE_SUFFIX));

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
        Assertions.assertTrue(Tiles.evaluateSpawn(hellTile));
        Assertions.assertTrue(Tiles.evaluateSpawn(emptyTile));
        Assertions.assertFalse(Tiles.evaluateSpawn(treeTile));
        Assertions.assertFalse(Tiles.evaluateSpawn(desertTreeTile));
    }

    /**
     * Test method for {@link Tiles#byName(String)}.
     */
    @Test
    void byName() {
        Assertions.assertEquals(grassTile.getTile(), Tiles.byName(Grass01.getName()).getTile());
        Assertions.assertEquals(desertTile.getTile(), Tiles.byName(Ground01.getName()).getTile());
        Assertions.assertEquals(hellTile.getTile(), Tiles.byName(HellGround01.getName()).getTile());
        Assertions.assertEquals(emptyTile.getTile(), Tiles.byName(Empty.getName()).getTile());
        Assertions.assertEquals(treeTile.getTile(), Tiles.byName(Tree01.getName()).getTile());
        Assertions.assertEquals(desertTreeTile.getTile(), Tiles.byName(DesertTiles.Tree01.getName()).getTile());
    }
}