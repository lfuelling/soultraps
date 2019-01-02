package io.lerk.soultraps.mobs;

import greenfoot.Greenfoot;
import io.lerk.soultraps.items.Item;
import io.lerk.soultraps.sys.DialogManager;
import io.lerk.soultraps.sys.Handler;

import java.util.ArrayList;
import java.util.List;

public class Player extends BaseMob {

    private static Player self = null;
    private final ArrayList<Item> items;
    private int seqIdx = 0;
    private boolean talking;

    private Player() {
        items = null;
    }

    public static Player getSelf() {
        if (self == null) {
            self = new Player();
        }
        return self;
    }

    @Override
    protected void updateWalkingState() {
        if(!talking) {
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
    }

    @Override
    protected int maxHealth() {
        return 100;
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

    public void startDialog(List<String> dialog, Handler doneAction) {
        talking = true;
        dialog.forEach(DialogManager::displayMessage);
        doneAction.handle();
        talking = false;
    }

    public void addItem(Item item) {
        items.add(item);
        DialogManager.displayMessage("You received: " + item.getName());
    }
}
