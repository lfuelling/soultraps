package io.lerk.soultraps.tiles;

import greenfoot.GreenfootImage;
import io.lerk.soultraps.mobs.BaseMob;
import io.lerk.soultraps.mobs.Enemies.Enemy;

import static io.lerk.soultraps.tiles.Tiles.FILE_SUFFIX;

public class LavaActor extends TileActor implements Enemy {

    /**
     * Index used for the animation.
     */
    private int seqIdx = 0;

    private long lastUpdateTime = 0L;

    /**
     * Constructor.
     */
    LavaActor() {
        super(HellTiles.Lava, new GreenfootImage(HellTiles.Lava.getName() + FILE_SUFFIX));
    }

    @Override
    public void act() {
        super.act();

        if(System.currentTimeMillis() - lastUpdateTime > BaseMob.UPDATE_INTERVAL) {
            if (seqIdx > 3) {
                seqIdx = 0;
            }
            this.setImage("images/hell/lava/lava0" + (seqIdx + 1) + FILE_SUFFIX);
            seqIdx++;
            lastUpdateTime = System.currentTimeMillis();
        }
    }

    @Override
    public Type getType() {
        return Type.STATIC;
    }

    @Override
    public int attack() {
        return 100; // ow
    }

    @Override
    public boolean block() {
        return false; // lava no protecc only attacc
    }

    @Override
    public boolean run() {
        return false; // lava doesn't run
    }

    @Override
    public void dealDamage(int damage) {
        // nope
    }
}
