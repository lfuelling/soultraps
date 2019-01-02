package io.lerk.soultraps;

import greenfoot.Greenfoot;
import io.lerk.soultraps.components.BaseMob;
import io.lerk.soultraps.sys.Tiles;

import java.util.stream.Collectors;

public class Player extends BaseMob {

    private static Player self = null;

    private boolean walking = false;
    private Direction direction = Direction.NORTH;
    private int seqIdx = 0;

    private Player() {

    }

    public static Player getSelf() {
        if (self == null) {
            self = new Player();
        }
        return self;
    }

    private void walk() {
        if (canWalk()) {
            this.move(1);
        }
    }

    private boolean canWalk() {
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

    private void updateWalkingState() {
        if (Greenfoot.isKeyDown("w")) {
            walking = true;
            direction = Direction.NORTH;
        } else if (Greenfoot.isKeyDown("a")) {
            walking = true;
            direction = Direction.WEST;
        } else if (Greenfoot.isKeyDown("s")) {
            walking = true;
            direction = Direction.SOUTH;
        } else if (Greenfoot.isKeyDown("d")) {
            walking = true;
            direction = Direction.EAST;
        } else {
            walking = false;
        }
    }

    private void orientPlayer() {
        this.setRotation(direction.getRotation());
        if (direction.isMirrorredV()) {
            this.getImage().mirrorVertically();
        }
        if (direction.isMirroredH()) {
            this.getImage().mirrorHorizontally();
        }
    }

    private void animateWalking() {
        if (seqIdx > 4) {
            seqIdx = 0;
        }
        if (walking) {
            this.setImage("images/player/player_walking" + (seqIdx + 1) + ".png");
        } else {
            this.setImage("images/player/player_walking1.png");
        }
        seqIdx++;
    }

    @Override
    protected void doAct() {
        updateWalkingState();
        animateWalking();
        orientPlayer();
        walk();
    }
}
