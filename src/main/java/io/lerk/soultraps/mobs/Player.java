package io.lerk.soultraps.mobs;

import greenfoot.Greenfoot;

public class Player extends BaseMob {

    private static Player self = null;

    private int seqIdx = 0;

    public static Player getSelf() {
        if (self == null) {
            self = new Player();
        }
        return self;
    }

    @Override
    protected void updateWalkingState() {
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

    @Override
    protected void doAct() {

    }

    @Override
    protected void animateWalking() {
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
}
