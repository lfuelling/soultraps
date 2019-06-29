package io.lerk.soultraps.mobs.Enemies;

import io.lerk.soultraps.mobs.BaseMob;
import io.lerk.soultraps.mobs.Direction;
import io.lerk.soultraps.mobs.Player;

import java.util.Random;

public abstract class EnemyMob extends BaseMob implements Enemy {

    /**
     * Updates the walking state according to trees in range or if the player is currently overlapping.
     */
    @Override
    protected void updateWalkingState() {
        if (isTouching(Player.class)) {
            walking = false;
            return;
        }
        if (new Random().nextBoolean()) {
            if (isPlayerInRangeNorth()) {
                walking = true;
                direction = Direction.NORTH;
            } else if (isPlayerInRangeEast()) {
                walking = true;
                direction = Direction.EAST;
            } else if (isPlayerInRangeSouth()) {
                walking = true;
                direction = Direction.SOUTH;
            } else if (isPlayerInRangeWest()) {
                walking = true;
                direction = Direction.WEST;
            } else {
                randomWalkingState();
            }
        } else {
            randomWalkingState();
        }
    }

    @Override
    public void act() {
        super.act();
    }

    /**
     * Sets {@link #walking} and {@link #direction} to a random value.
     */
    private void randomWalkingState() {
        walking = new Random().nextBoolean();
        direction = (new Random().nextBoolean()) ? Direction.random() : direction;
    }

    /**
     * Checks up to 3 blocks in north direction for trees in range.
     *
     * @return true if there are trees in range.
     */
    private boolean isPlayerInRangeNorth() {
        return getObjectsAtOffset(0, -1, Player.class).size() >= 1 ||
                getObjectsAtOffset(0, -2, Player.class).size() >= 1 || //TODO: maybe add something like #isAccessible()
                getObjectsAtOffset(0, -3, Player.class).size() >= 1;
    }

    /**
     * Checks up to 3 blocks in east direction for trees in range.
     *
     * @return true if there are trees in range.
     */
    private boolean isPlayerInRangeEast() {
        return getObjectsAtOffset(1, 0, Player.class).size() >= 1 ||
                getObjectsAtOffset(2, 0, Player.class).size() >= 1 || //TODO: maybe add something like #isAccessible()
                getObjectsAtOffset(3, 0, Player.class).size() >= 1;
    }

    /**
     * Checks up to 3 blocks in south direction for trees in range.
     *
     * @return true if there are trees in range.
     */
    private boolean isPlayerInRangeSouth() {
        return getObjectsAtOffset(0, 1, Player.class).size() >= 1 ||
                getObjectsAtOffset(0, 2, Player.class).size() >= 1 || //TODO: maybe add something like #isAccessible()
                getObjectsAtOffset(0, 3, Player.class).size() >= 1;
    }

    /**
     * Checks up to 3 blocks in west direction for trees in range.
     *
     * @return true if there are trees in range.
     */
    private boolean isPlayerInRangeWest() {
        return getObjectsAtOffset(-1, 0, Player.class).size() >= 1 ||
                getObjectsAtOffset(-2, 0, Player.class).size() >= 1 || //TODO: maybe add something like #isAccessible()
                getObjectsAtOffset(-3, 0, Player.class).size() >= 1;
    }

}
