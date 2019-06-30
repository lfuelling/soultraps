package io.lerk.soultraps.mobs;

import greenfoot.Actor;
import io.lerk.soultraps.tiles.TileActor;

import java.util.Random;
import java.util.stream.Collectors;

/**
 * Basic mob class that contains stuff shared by all mobs.
 *
 * @author Lukas Fülling (lukas@k40s.net)
 */
public abstract class BaseMob extends Actor {

    /**
     * Update interval in milliseconds.
     */
    public static final long UPDATE_INTERVAL = 100L;

    /**
     * If mob is walking.
     */
    protected boolean walking = false;

    /**
     * Direction of mob,
     */
    protected Direction direction = Direction.NORTH;

    /**
     * Mob health.
     */
    private Integer health;

    /**
     * Constructor.
     */
    public BaseMob() {
        health = maxHealth();
    }

    /**
     * Get the mobs max possible health.
     *
     * @return max possible health of mob.
     */
    public abstract int maxHealth();

    /**
     * Throttled method used for stuff other than walking.
     */
    protected abstract void doAct();

    /**
     * Last time the mob was refreshed.
     */
    private long lastActTimeMillis = 0;

    /**
     * Method that throttles the mobs behavior.
     */
    @Override
    public void act() {
        if (System.currentTimeMillis() - UPDATE_INTERVAL >= lastActTimeMillis) {
            updateWalkingState();
            animateWalking();
            orientMob();
            walk();
            doAct();
            lastActTimeMillis = System.currentTimeMillis();
        }
    }

    /**
     * Moves the mob if possible. This is called in {@link #act()}.
     */
    protected void walk() {
        if (canWalk()) {
            this.move(1);
        }
    }

    /**
     * Animate mob. This is called in {@link #act()}.
     */
    protected abstract void animateWalking();

    /**
     * This method should update {@link #walking} and {@link #direction} according to mob state.
     * This is called in {@link #act()}.
     */
    protected abstract void updateWalkingState();

    /**
     * Orients (rotates) the mob.
     * This is called in {@link #act()}.
     */
    protected void orientMob() {
        this.setRotation(direction.getRotation());
        if (direction.isMirroredV()) {
            this.getImage().mirrorVertically();
        }
        if (direction.isMirroredH()) {
            this.getImage().mirrorHorizontally();
        }
    }

    /**
     * Decides if the mob can walk (ie. not standing in front of anything).
     * This is called in {@link #walk()}.
     *
     * @return true if the mob can walk in the {@link #direction} it's facing.
     */
    private boolean canWalk() {
        if (walking) {
            if (direction.equals(Direction.NORTH)) {
                return this.getObjectsAtOffset(0, -1, TileActor.class).stream()
                        .noneMatch(TileActor::isTileOtherThanGround);
            } else if (direction.equals(Direction.EAST)) {
                return this.getObjectsAtOffset(1, 0, TileActor.class).stream()
                        .noneMatch(TileActor::isTileOtherThanGround);
            } else if (direction.equals(Direction.SOUTH)) {
                return this.getObjectsAtOffset(0, 1, TileActor.class).stream()
                        .noneMatch(TileActor::isTileOtherThanGround);
            } else if (direction.equals(Direction.WEST)) {
                return this.getObjectsAtOffset(-1, 0, TileActor.class).stream()
                        .noneMatch(TileActor::isTileOtherThanGround);
            }
        }
        return false;
    }

    /**
     * Getter for health.
     *
     * @return mob health.
     */
    public Integer getHealth() {
        return health;
    }

    /**
     * Setter for health.
     *
     * @param health mob health
     */
    public void setHealth(Integer health) {
        this.health = health;
    }

    public Direction getDirection() {
        return direction;
    }
}
