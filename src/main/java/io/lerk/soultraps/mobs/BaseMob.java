package io.lerk.soultraps.mobs;

import greenfoot.Actor;
import io.lerk.soultraps.sys.Tiles;

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
    private final long UPDATE_INTERVAL = 100L;

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
    protected abstract int maxHealth();

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
    protected boolean canWalk() {
        if (walking) {
            if (direction.equals(Direction.NORTH)) {
                return this.getObjectsAtOffset(0, -1, Tiles.Tile.class).stream()
                        .filter(tile -> !(tile.getTileType().equals(Tiles.Grass01) ||
                                tile.getTileType().equals(Tiles.Grass02) ||
                                tile.getTileType().equals(Tiles.Empty)))
                        .collect(Collectors.toList()).size() == 0;
            } else if (direction.equals(Direction.EAST)) {
                return this.getObjectsAtOffset(1, 0, Tiles.Tile.class).stream()
                        .filter(tile -> !(tile.getTileType().equals(Tiles.Grass01) ||
                                tile.getTileType().equals(Tiles.Grass02) ||
                                tile.getTileType().equals(Tiles.Empty)))
                        .collect(Collectors.toList()).size() == 0;
            } else if (direction.equals(Direction.SOUTH)) {
                return this.getObjectsAtOffset(0, 1, Tiles.Tile.class).stream()
                        .filter(tile -> !(tile.getTileType().equals(Tiles.Grass01) ||
                                tile.getTileType().equals(Tiles.Grass02) ||
                                tile.getTileType().equals(Tiles.Empty)))
                        .collect(Collectors.toList()).size() == 0;
            } else if (direction.equals(Direction.WEST)) {
                return this.getObjectsAtOffset(-1, 0, Tiles.Tile.class).stream()
                        .filter(tile -> !(tile.getTileType().equals(Tiles.Grass01) ||
                                tile.getTileType().equals(Tiles.Grass02) ||
                                tile.getTileType().equals(Tiles.Empty)))
                        .collect(Collectors.toList()).size() == 0;
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

    /**
     * Direction enum.
     *
     * @author Lukas Fülling (lukas@k40s.net)
     */
    public enum Direction {
        /**
         * North (up)
         */
        NORTH(270),
        /**
         * East (right)
         */
        EAST(0),
        /**
         * South (down)
         */
        SOUTH(90, true, false),
        /**
         * West (left)
         */
        WEST(180, true, false);

        /**
         * Image rotation for this direction.
         */
        private final int rotation;

        /**
         * Should image be mirrored horizontally.
         */
        private final boolean mirroredH;

        /**
         * Should image be mirrored vertically.
         */
        private final boolean mirroredV;

        /**
         * Constructor.
         *
         * @param rotation image rotation.
         */
        Direction(int rotation) {
            this(rotation, false, false);
        }

        /**
         * Constructor.
         *
         * @param rotation  image rotation
         * @param mirroredV mirror image horizontally
         * @param mirroredH mirror image vertically
         */
        Direction(int rotation, boolean mirroredV, boolean mirroredH) {
            this.rotation = rotation;
            this.mirroredH = mirroredH;
            this.mirroredV = mirroredV;
        }

        /**
         * Generates a random {@link Direction}.
         *
         * @return random direction
         */
        public static Direction random() {
            switch (new Random().nextInt(3)) {
                case 0:
                    return Direction.NORTH;
                case 1:
                    return Direction.WEST;
                case 2:
                    return Direction.SOUTH;
                default: // 3 can be omitted
                    return Direction.EAST;
            }
        }

        /**
         * Getter for mirror image horizontally.
         *
         * @return {@link #mirroredH}
         */
        public boolean isMirroredH() {
            return mirroredH;
        }

        /**
         * Getter for mirror image vertically.
         *
         * @return {@link #mirroredV}
         */
        public boolean isMirroredV() {
            return mirroredV;
        }

        /**
         * Getter for image rotation.
         *
         * @return image rotation
         */
        public int getRotation() {
            return rotation;
        }
    }
}
