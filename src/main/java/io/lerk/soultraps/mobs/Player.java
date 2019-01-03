package io.lerk.soultraps.mobs;

import greenfoot.Greenfoot;
import io.lerk.soultraps.items.Item;
import io.lerk.soultraps.mobs.Enemies.Enemy;
import io.lerk.soultraps.sys.DialogManager;
import io.lerk.soultraps.sys.Handler;

import java.util.ArrayList;
import java.util.List;

/**
 * Player class.
 *
 * @author Lukas Fülling (lukas@k40s.net)
 */
public class Player extends DialogMob {

    /**
     * Player instance.
     */
    private static Player self = null;

    /**
     * Player items (eg. inventory).
     */
    private final ArrayList<Item> items;

    /**
     * Index used for the animation.
     */
    private int seqIdx = 0;

    /**
     * Constructor.
     */
    private Player() {
        items = new ArrayList<>();
    }

    /**
     * Singleton getter for player.
     *
     * @return the player instance.
     */
    public static Player getSelf() {
        if (self == null) {
            self = new Player();
        }
        return self;
    }

    /**
     * Updates {@link #walking} and {@link #direction} according to currently pressed buttons.
     */
    @Override
    protected void updateWalkingStateNotTalking() {
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

    /**
     * Dummy method, the player has no dialog.
     *
     * @return a new {@link ArrayList<String>}.
     */
    @Override
    protected ArrayList<String> getDialog() {
        return new ArrayList<>();
    }

    /**
     * Returns the max possible player health.
     *
     * @return max possible health
     */
    @Override
    protected int maxHealth() {
        return 100;
    }

    /**
     * Throttled acting method that is used for anything other than walking.
     */
    @Override
    protected void doAct() {
    }

    /**
     * Animates the walking of the player.
     */
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

    /**
     * Method used to start a dialog.
     *
     * @param dialog     the dialogs content.
     * @param doneAction the action to be done after the dialog is complete
     * @param mob        the mob the player is talking with
     */
    public void startDialog(List<String> dialog, Handler doneAction, DialogMob mob) {
        dialog.forEach(m -> DialogManager.displayMessage(m, getWorld(), mob));
        doneAction.handle();
    }

    /**
     * Method used to add an {@link Item} to the players inventory.
     *
     * @param item the item to add
     */
    public void addItem(Item item) {
        items.add(item);
        DialogManager.displayMessage("You received: " + item.getName(), getWorld(), null);
    }

    public void startAttack(Enemy enemy) {
        //TODO: implement
    }

    public ArrayList<Item> getItems() {
        return items;
    }
}
