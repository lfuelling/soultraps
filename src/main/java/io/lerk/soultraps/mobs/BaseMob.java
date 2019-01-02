package io.lerk.soultraps.mobs;

import greenfoot.Actor;
import io.lerk.soultraps.sys.Tiles;

import java.util.Random;
import java.util.stream.Collectors;

public abstract class BaseMob extends Actor {

    private final long UPDATE_INTERVAL = 100L;

    protected boolean walking = false;
    protected Direction direction = Direction.NORTH;

    protected abstract void doAct();

    private long lastActTimeMillis = 0;

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

    protected void walk() {
        if (canWalk()) {
            this.move(1);
        }
    }

    protected abstract void animateWalking();

    protected abstract void updateWalkingState();

    protected void orientMob() {
        this.setRotation(direction.getRotation());
        if (direction.isMirrorredV()) {
            this.getImage().mirrorVertically();
        }
        if (direction.isMirroredH()) {
            this.getImage().mirrorHorizontally();
        }
    }

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
