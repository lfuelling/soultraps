package io.lerk.soultraps.components;

import greenfoot.Actor;

public abstract class BaseMob extends Actor {
    public static enum Direction {

        NORTH(270),
        EAST(0),
        SOUTH(90, true),
        WEST(180, true);

        private final int rotation;
        private final boolean mirrorred;

        Direction(int rotation) {
            this(rotation, false);
        }

        Direction(int rotation, boolean mirrorred) {
            this.rotation = rotation;

            this.mirrorred = mirrorred;
        }

        public boolean isMirrorred() {
            return mirrorred;
        }

        public int getRotation() {
            return rotation;
        }
    }
}
