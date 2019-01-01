package io.lerk.soultraps;

import greenfoot.Greenfoot;
import io.lerk.soultraps.components.BaseMob;

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

    @Override
    public void act() {
        updateWalkingState();
        animateWalking();
        orientPlayer();
    }

    private void updateWalkingState() {
        if(Greenfoot.isKeyDown("w")) {
            walking = true;
            direction = Direction.NORTH;
        } else if(Greenfoot.isKeyDown("a")) {
            walking = true;
            direction = Direction.EAST;
        } else if(Greenfoot.isKeyDown("s")) {
            walking = true;
            direction = Direction.SOUTH;
        } else if(Greenfoot.isKeyDown("d")) {
            walking = true;
            direction = Direction.WEST;
        } else {
            walking = false;
        }
    }

    private void orientPlayer() {
        this.setRotation(direction.getRotation());
        if (direction.isMirrorred()) {
            this.getImage().mirrorHorizontally();
        }
    }

    private void animateWalking() {
        if(seqIdx>4) {
            seqIdx = 0;
        }
        if (walking) {
            this.setImage("images/player/player_walking" + (seqIdx + 1) + ".png");
        } else {
            this.setImage("images/player/player_walking1.png");
        }
        seqIdx++;
    }
}
