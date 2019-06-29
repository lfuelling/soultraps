package io.lerk.soultraps.mobs;

import greenfoot.Greenfoot;
import io.lerk.soultraps.items.Item;
import io.lerk.soultraps.mobs.Enemies.Enemy;
import io.lerk.soultraps.mobs.friendly.DialogMob;
import io.lerk.soultraps.sys.Handler;
import io.lerk.soultraps.sys.Soultraps;
import io.lerk.soultraps.sys.dialog.Dialog;
import io.lerk.soultraps.sys.dialog.DialogManager;
import io.lerk.soultraps.sys.dialog.Message;
import io.lerk.soultraps.sys.savegame.PlayerDTO;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Player class.
 *
 * @author Lukas Fülling (lukas@k40s.net)
 */
public class Player extends DialogMob {

    /**
     * Logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(Player.class);

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
     * Last saved X position.
     */
    private int savedXPos;

    /**
     * Last saved Y position.
     */
    private int savedYPos;

    private long lastAttack = 0;

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
     * Restores state from a {@link PlayerDTO} loaded from a file.
     *
     * @param player the player to load state from
     */
    public static void restore(PlayerDTO player) {
        if (self == null) {
            self = new Player();
        }
        self.items.clear();

        Reflections reflections = new Reflections("io.lerk.soultraps.items");
        Set<Class<? extends Item>> classes = reflections.getSubTypesOf(Item.class);
        classes.forEach(c -> player.getItems().forEach(i -> {
            try {
                Item item = c.newInstance();
                if(item.getName().equals(i)) {
                    self.items.add(item);
                }
            } catch (InstantiationException | IllegalAccessException e) {
                logger.error("Unable to instantiate item with name: '" + i + "'", e);
            }
        }));

        self.savedXPos = player.getPosX();
        self.savedYPos = player.getPosY();
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
     * @return a new {@link ArrayList<Message>}
     */
    @Override
    protected List<Message> getDialogMessages() {
        return new ArrayList<>();
    }

    /**
     * Dummy method, the player has no dialog.
     *
     * @return a {@link Handler} that returns null
     */
    @Override
    protected Handler<Void> getDialogDoneAction() {
        return () -> null;
    }

    /**
     * Dummy method, the player has no dialog.
     *
     * @return false
     */
    @Override
    protected boolean shouldStartConversation() {
        return false;
    }

    /**
     * Returns the max possible player health.
     *
     * @return max possible health
     */
    @Override
    public int maxHealth() {
        return 200;
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
     * Method used to add an {@link Item} to the players inventory.
     *
     * @param item the item to add
     */
    public void addItem(Item item) {
        Dialog dialog = new Dialog();
        ArrayList<Message> messageList = new ArrayList<>();
        messageList.add(new Message("You received: " + item.getName(), dialog));
        dialog.setMessages(messageList);
        dialog.setDoneAction(() -> {
            items.add(item);
            return null;
        });
        DialogManager.startDialog(dialog);
    }

    /**
     * Starts a fight with a given enemy.
     *
     * @param enemy the enemy to start the fight with
     */
    public void startAttack(Enemy enemy) {
        if(System.currentTimeMillis() - lastAttack >= 500) {
            setHealth(getHealth() - enemy.attack());
            if(getHealth() <= 0) {
                Soultraps.gameOver();
            }
            lastAttack = System.currentTimeMillis();
        }
    }

    /**
     * Getter for the player's {@link #items}.
     *
     * @return the player's items
     */
    public ArrayList<Item> getItems() {
        return items;
    }

    /**
     * Getter for last saved X position.
     * @return the last saved X position
     */
    public int getSavedXPos()
    {
      return savedXPos;
    }

    /**
     * Getter for last saved Y position.
     * @return the last saved Y position
     */
    public int getSavedYPos()
    {
      return savedYPos;
    }

    /**
     * Method used to reset the player once the game is over.
     */
    public void reset() {
        setHealth(maxHealth());
        items.clear();
        lastAttack = 0;
        seqIdx = 0;
    }
}
