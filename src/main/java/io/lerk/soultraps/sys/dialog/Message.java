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
public class Message extends Actor
{

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
  public Message(String message, Dialog dialog)
  {
    this.dialog = dialog;

    int imageHeight = 128;

    GreenfootImage background = new GreenfootImage(Level.LEVEL_WIDTH * Level.CELL_SIZE, imageHeight);
    background.setColor(Color.BLACK);
    background.fill();

    GreenfootImage textImage = new GreenfootImage(Level.LEVEL_WIDTH * Level.CELL_SIZE, imageHeight);
    textImage.setColor(Color.WHITE);
    textImage.setFont(Fonts.getFont(Fonts.Types.SKYRIM, 24));
    textImage.drawString(message, 4, (textImage.getHeight() / 2));

    GreenfootImage hintImage = new GreenfootImage(Level.LEVEL_WIDTH * Level.CELL_SIZE, imageHeight);
    hintImage.setColor(Color.WHITE);
    hintImage.setFont(Fonts.getFont(Fonts.Types.SKYRIM, 18));
    hintImage.drawString("- press space to continue -", 4, (textImage.getHeight() / 2));

    GreenfootImage image = new GreenfootImage(Level.LEVEL_WIDTH * Level.CELL_SIZE, imageHeight);
    image.drawImage(background, 0, 0);
    image.drawImage(textImage, (Level.LEVEL_WIDTH * Level.CELL_SIZE) / 2, 45);
    image.drawImage(hintImage, 1820, 56);
    setImage(image);
  }

  /**
   * This method checks if the spacebar is pressed and removes the message if that's the case.
   */
  @Override
  public void act()
  {
    if (Greenfoot.isKeyDown("space")) {
      DialogManager.dismiss(this);
    }
  }

  /**
   * Getter for the dialog this message belongs to.
   *
   * @return the dialog
   */
  public Dialog getDialog()
  {
    return dialog;
  }
}