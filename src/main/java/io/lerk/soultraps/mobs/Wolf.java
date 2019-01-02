package io.lerk.soultraps.mobs;

import java.util.Random;

/**
 * This is one of the easier mobs to deal with.
 * It walks around randomly ond follows the player up to a range of three blocks.
 *
 * @author Lukas Fülling (lukas@k40s.net)
 */
public class Wolf extends BaseMob implements Enemy {

    /**
     * Index used for the walking animation.
     */
    private int seqIdx = 0;

    /**
     * Updates the walking state according to trees in range or if the player is currently overlapping.
     */
    @Override
    protected void updateWalkingState() {
        if (getIntersectingObjects(Player.class).size() > 0) {
            walking = false;
            Player.getSelf().startAttck(this);
            return;
        }
        if (new Random().nextBoolean()) {
            if (isPlayerInRangeNorth()) {
                walking = true;
                direction = BaseMob.Direction.NORTH;
            } else if (isPlayerInRangeEast()) {
                walking = true;
                direction = BaseMob.Direction.EAST;
            } else if (isPlayerInRangeSouth()) {
                walking = true;
                direction = BaseMob.Direction.SOUTH;
            } else if (isPlayerInRangeWest()) {
                walking = true;
                direction = BaseMob.Direction.WEST;
            } else {
                randomWalkingState();
            }
        } else {
            randomWalkingState();
        }
    }

    /**
     * Sets {@link #walking} and {@link #direction} to a random value.
     */
    private void randomWalkingState() {
        walking = new Random().nextBoolean();
        direction = (new Random().nextBoolean()) ? BaseMob.Direction.random() : direction;
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

    /**
     * Animates walking of the lumberjack.
     */
    @Override
    protected void animateWalking() {
        if (seqIdx > 4) {
            seqIdx = 0;
        }
        if (walking) {
            this.setImage("images/wolf/wolf" + (seqIdx + 1) + ".png");
        } else {
            this.setImage("images/wolf/wolf1.png");
        }
        seqIdx++;
    }

    /**
     * Method that returns the max health of the lumberjack.
     *
     * @return max possible health
     */
    @Override
    protected int maxHealth() {
        return 50;
    }

    /**
     * Throttled act method that is used for anything else than walking.
     */
    @Override
    protected void doAct() {
    }

    /**
     * Determines the damage dealt to the player.
     *
     * @return the damage dealt to the player
     */
    @Override
    public int attack() {
        return new Random().nextInt(15);
    }

    /**
     * Determines if the wolf should block the player's attack.
     *
     * @return false, wolf not blocc, just attacc
     */
    @Override
    public boolean block() {
        return false;
    }

    /**
     * Determines if the wolf should try to run.
     *
     * @return false, wolf not run, wolf just attacc
     */
    @Override
    public boolean run() {
        return false;
    }
}
