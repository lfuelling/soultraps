package io.lerk.soultraps.mobs.player;

import greenfoot.GreenfootImage;
import io.lerk.soultraps.mobs.Direction;
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
            if (direction == Direction.NORTH) {
                setLocation(Player.getSelf().getX(), Player.getSelf().getY() - 1);
            } else if (direction == Direction.EAST) {
                setLocation(Player.getSelf().getX() + 1, Player.getSelf().getY());
            } else if (direction == Direction.SOUTH) {
                setLocation(Player.getSelf().getX(), Player.getSelf().getY() + 1);
            } else {
                setLocation(Player.getSelf().getX() - 1, Player.getSelf().getY());
            }
            setRotation(direction.getRotation());
        } else {
            if (seqIdx != 0) seqIdx = 0; // reset index

            setImage(EMPTY);
        }
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
        if (Player.getSelf().drankGoldenDistillate()) {
            return 100;
        } else {
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

}
