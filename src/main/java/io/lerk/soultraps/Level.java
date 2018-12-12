package io.lerk.soultraps;

import greenfoot.Actor;
import greenfoot.GreenfootImage;
import greenfoot.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

import static io.lerk.soultraps.Launcher.BASE_HEIGHT;
import static io.lerk.soultraps.Launcher.BASE_WIDTH;

public class Level extends World {

    public static final int LEVEL_WIDTH = 128;
    public static final int LEVEL_HEIGHT = 128;

    private static final Logger log = LoggerFactory.getLogger(Level.class);
    private final String[][] levelTiles = new String[LEVEL_WIDTH][LEVEL_HEIGHT];
    private final PlayerRef player;

    public Level(PlayerRef player) {
        super(BASE_WIDTH, BASE_HEIGHT, 1);
        this.player = player;
        fillTiles();
        renderViewport(getInitialPosition());
    }

    @Override
    public void act() {
        renderViewport(player.getPositionInWorld());
    }

    private void renderViewport(int position) {
        //TODO use position
        int tileWidth = 16;
        int tileHeight = 16;
        for (int widthCount = 0; widthCount < getWidth() / tileWidth; widthCount++) {
            for (int heightCount = 0; heightCount < getHeight() / tileHeight; heightCount++) {
                Tiles.Tile tile = Tiles.byName(levelTiles[widthCount][heightCount]);
                int posX = (getWidth() / tileWidth) * widthCount;
                int posY = (getHeight() / tileHeight) * heightCount;
                if (tile != null) {
                    addObject(tile, posX, posY);
                } else {
                    log.error("No tile available for levelTiles[" + widthCount + "][" + heightCount + "]!");
                }
            }
        }
    }

    protected int getInitialPosition() {
        return 0;
    }

    private void fillTiles() {
        for (int widthCount = 0; widthCount < LEVEL_WIDTH; widthCount++) {
            String[] tmp = new String[LEVEL_HEIGHT];
            for (int heightCount = 0; heightCount < LEVEL_HEIGHT; heightCount++) {
                tmp[heightCount] = getRandomTile();
            }
            levelTiles[widthCount] = tmp;
        }
    }

    private String getRandomTile() {
        Tiles[] values = Tiles.values();
        String res = null;
        while (res == null) {
            for (Tiles value : values) {
                if (new Random().nextInt(100) % 2 == 0) {
                    res = value.getTile().getName();
                }
            }
        }
        return res;
    }

    enum Tiles {
        Grass01(new Tile("grass01", new GreenfootImage("grass01.png"))),
        Grass02(new Tile("grass02", new GreenfootImage("grass02.png"))),
        Tree01(new Tile("tree01", new GreenfootImage("tree01.png"))),
        Tree02(new Tile("tree02", new GreenfootImage("tree02.png"))),
        Tree03(new Tile("tree03", new GreenfootImage("tree03.png"))),
        Stones01(new Tile("stones01", new GreenfootImage("stones01.png"))),
        Stones02(new Tile("stones02", new GreenfootImage("stones02.png"))),
        Stones03(new Tile("stones03", new GreenfootImage("stones03.png"))),
        Bush01(new Tile("bush01", new GreenfootImage("bush01.png"))),
        Bush02(new Tile("bush02", new GreenfootImage("bush02.png"))),
        Fern01(new Tile("fern01", new GreenfootImage("fern01.png"))),
        Fern02(new Tile("fern02", new GreenfootImage("fern02.png"))),
        Fern03(new Tile("fern03", new GreenfootImage("fern03.png"))),
        Fern04(new Tile("fern04", new GreenfootImage("fern04.png"))),
        TreeStump01(new Tile("treestump01", new GreenfootImage("treestump01.png"))),
        TreeStump02(new Tile("treestump02", new GreenfootImage("treestump02.png"))),
        TreeStump03(new Tile("treestump03", new GreenfootImage("treestump03.png")));

        private final Tile tile;

        Tiles(Tile tile) {
            this.tile = tile;
        }

        public Tile getTile() {
            return tile;
        }

        public static Tile byName(String name) {
            for (int i = 0; i < values().length; i++) {
                Tiles t = values()[i];
                if (t.tile.getName().equals(name)) {
                    return t.tile;
                }
            }
            return null;
        }

        static class Tile extends Actor {
            private final String name;

            Tile(String name, GreenfootImage image) {
                this.name = name;
                setImage(image);
            }

            public String getName() {
                return name;
            }
        }
    }
}
