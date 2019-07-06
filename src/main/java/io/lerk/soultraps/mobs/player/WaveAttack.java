package io.lerk.soultraps.mobs.player;

import greenfoot.GreenfootImage;
import io.lerk.soultraps.mobs.Enemies.Enemy;

import static io.lerk.soultraps.tiles.Tiles.FILE_SUFFIX;

public class WaveAttack extends Attack {

    private static final GreenfootImage EMPTY = new GreenfootImage(16, 16);

    /**
     * Index used for the animation.
     */
    private int seqIdx = 0;

    public WaveAttack() {
        setImage(EMPTY);
    }

    @Override
    public int maxHealth() {
        return Integer.MAX_VALUE;
    }

    @Override
    protected void animateWalking() {
        if (visible) {
            cycleAnimation();
            handleRotation();
        } else {
            if (seqIdx != 0) seqIdx = 0; // reset index

            setImage(EMPTY);
        }
    }

    private void handleRotation() {
        switch (direction) {
            case NORTH:
                setLocation(Player.getSelf().getX(), Player.getSelf().getY() - 1);
                break;
            case EAST:
                setLocation(Player.getSelf().getX() + 1, Player.getSelf().getY());
                break;
            case SOUTH:
                setLocation(Player.getSelf().getX(), Player.getSelf().getY() + 1);
                break;
            case WEST:
            default:
                setLocation(Player.getSelf().getX() - 1, Player.getSelf().getY());
                break;
        }
        setRotation(direction.getRotation());
    }

    private void cycleAnimation() {
        if (seqIdx > 5) {
            seqIdx = 0;
        }
        if (seqIdx < 0) {
            seqIdx = 0;
        }
        setImage("images/player/attack/attack-wave" + (seqIdx + 1) + FILE_SUFFIX);
        seqIdx++;
    }

    @Override
    protected void updateWalkingState() {
    }

    @Override
    public int getDamage(Enemy enemy) {
        switch (enemy.getType()) {
            case HUMANOID:
                return 20;
            case UNDEAD:
                return 40;
            case STATIC:
                return 0;
            case ANIMAL:
            default:
                return 10;
        }
    }

}
