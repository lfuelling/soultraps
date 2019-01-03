package io.lerk.soultraps.sys.dialog;

import greenfoot.Actor;
import greenfoot.Color;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import io.lerk.soultraps.levels.Level;
import io.lerk.soultraps.sys.Fonts;

/**
 * An {@link Actor} implementation that represents a message.
 * This class draws the message box, frame and text and holds a reference to it's dialog.
 *
 * @author Lukas Fülling (lukas@k40s.net)
 */
public class Message extends Actor {

    /**
     * The {@link Dialog} this message belongs to.
     */
    private final Dialog dialog;

    /**
     * Constructor.
     *
     * @param message the content of this message
     * @param dialog  the dialog this message belongs to
     */
    public Message(String message, Dialog dialog) {
        this.dialog = dialog;
        GreenfootImage image = new GreenfootImage(Level.LEVEL_WIDTH * Level.CELL_SIZE, 512);
        image.setColor(Color.BLACK);
        image.fill();
        image.setColor(Color.WHITE);
        image.drawRect(0, 0, image.getWidth(), image.getHeight());
        image.setFont(Fonts.getFont(Fonts.Types.SKYRIM, 24));
        image.drawString(message, 4, (image.getHeight() / 2));
        setImage(image);
    }

    /**
     * This method checks if the spacebar is pressed and removes the message if that's the case.
     */
    @Override
    public void act() {
        if (Greenfoot.isKeyDown("space")) {
            DialogManager.dismiss(this);
        }
    }

    /**
     * Getter for the dialog this message belongs to.
     *
     * @return the dialog
     */
    public Dialog getDialog() {
        return dialog;
    }
}