package io.lerk.soultraps.components;

import greenfoot.Actor;

public abstract class BaseMob extends Actor {

    private final long UPDATE_INTERVAL = 100L;

    protected abstract void doAct();

    private long lastActTimeMillis = 0;

    @Override
    public void act() {
        if(System.currentTimeMillis() - UPDATE_INTERVAL >= lastActTimeMillis) {
            doAct();
            lastActTimeMillis = System.currentTimeMillis();
        }
    }

    public enum Direction {
        NORTH(270),
        EAST(0),
        SOUTH(90, true, false),
        WEST(180, true, false);

        private final int rotation;
        private final boolean mirroredH;
        private final boolean mirrorredV;

        Direction(int rotation) {
            this(rotation, false, false);
        }

        Direction(int rotation, boolean mirrorredV, boolean mirroredH) {
            this.rotation = rotation;
            this.mirroredH = mirroredH;
            this.mirrorredV = mirrorredV;
        }

        public boolean isMirroredH() {
            return mirroredH;
        }

        public boolean isMirrorredV() {
            return mirrorredV;
        }

        public int getRotation() {
            return rotation;
        }
    }
}
