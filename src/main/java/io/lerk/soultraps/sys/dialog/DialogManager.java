package io.lerk.soultraps.sys.dialog;

import greenfoot.Actor;
import greenfoot.GreenfootImage;
import io.lerk.soultraps.mobs.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

/**
 * Class used to display dialogQueue.
 *
 * @author Lukas Fülling (lukas@k40s.net)
 */
public class DialogManager extends Actor {

    /**
     * Logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(DialogManager.class);

    /**
     * DialogManager instance.
     */
    private static DialogManager instance = null;

    /**
     * Queue of dialogQueue to show.
     */
    private ArrayList<Dialog> dialogQueue;

    /**
     * Currently active dialog.
     */
    private static Dialog current;

    /**
     * Constructor.
     */
    private DialogManager() {
        setImage(new GreenfootImage(1, 1));
        dialogQueue = new ArrayList<>();
    }

    /**
     * Displays a message to the user.
     *
     * @param dialog the dialog
     */
    public static void startDialog(Dialog dialog) {
        if (instance == null) {
            instance = new DialogManager();
        }
        instance.dialogQueue.add(dialog);
    }

    /**
     * Removes a message from the world and let's the player and mobs walk around again.
     *
     * @param message the message to dismiss
     */
    static void dismiss(Message message) {
        if (instance == null) {
            throw new IllegalStateException("DialogManager instance is null!");
        } else {
            instance.getWorld().removeObject(message);
        }
    }

    /**
     * Singleton getter. Initializes the instance if it's null.
     *
     * @return the instance
     */
    public static DialogManager get() {
        if (instance == null) {
            instance = new DialogManager();
        }
        return instance;
    }

    /**
     * In this method, the {@link DialogManager} checks for available dialogs and displays their messages accordingly.
     * This way the game in the background still runs (ie. seeing a message is not blocking).
     */
    @Override
    public void act() {
        if (getWorld().getObjects(Message.class).size() <= 0) {
            if (current != null && current.getMessages().size() > 0) {
                Message m = current.getMessages().get(0);
                getWorld().addObject(m, 0, 0);
                current.getMessages().remove(m);
                quickSleep(); // wait for debouncing
            } else if (current != null && current.getMessages().size() == 0) {
                if (current.getMob() != null) {
                    current.getMob().setTalking(false);
                }
                Player.getSelf().setTalking(false);
                current.getDoneAction().handle();
                current = null;
            } else {
                if (dialogQueue.size() > 0) {
                    current = dialogQueue.get(0);
                    dialogQueue.remove(current);
                    if (current.getMob() != null) {
                        current.getMob().setTalking(true);
                    }
                    Player.getSelf().setTalking(true);
                }
            }
        }
    }

    /**
     * Sleeps the current {@link Thread} for 100ms.
     */
    private void quickSleep() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            logger.error("Interrupted!", e);
        }
    }
}
