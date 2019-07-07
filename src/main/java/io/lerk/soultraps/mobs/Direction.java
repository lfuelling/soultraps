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
    NORTH(270, 33),
    /**
     * East (right)
     */
    EAST(0, 168),
    /**
     * South (down)
     */
    SOUTH(90, 270, true, false),
    /**
     * West (left)
     */
    WEST(180, 330, true, false);

    /**
     * Image rotation for this direction.
     */
    private final int rotation;

    /**
     * Max value in degrees for this direction.
     */
    private final int bound;

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
     * @param rotation image rotation
     * @param bound    max value in degrees
     */
    Direction(int rotation, int bound) {
        this(rotation, bound, false, false);
    }

    /**
     * Constructor.
     *
     * @param rotation  image rotation
     * @param bound     max value in degrees
     * @param mirroredV mirror image horizontally
     * @param mirroredH mirror image vertically
     */
    Direction(int rotation, int bound, boolean mirroredV, boolean mirroredH) {
        this.rotation = rotation;
        this.bound = bound;
        this.mirroredH = mirroredH;
        this.mirroredV = mirroredV;
    }

    /**
     * Generates a random {@link Direction}.
     *
     * @return random direction
     */
    public static Direction random() {
        switch (new Random().nextInt(4)) {
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

    public static Direction fromDegrees(int degrees) {
        degrees = normalizeDegrees(degrees) + 90;

        for (Direction d : Direction.values()) {
            if (degrees <= d.bound) {
                return d;
            }
        }
        return NORTH;
    }

    /**
     * Normalize an angle so that it is between 0 and 360.
     *
     * @param angle Angle in degrees to normalize
     * @return Normalized angle.
     * @author http://www.java2s.com/example/android/java.lang/normalize-an-angle-so-that-it-is-between-0-and-360.html
     */
    private static int normalizeDegrees(final float angle) {
        return (int) ((angle >= 0 ? angle : (360 - ((-angle) % 360))) % 360);
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
