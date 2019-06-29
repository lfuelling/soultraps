package io.lerk.soultraps.mobs;

import java.util.Random;

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
