package io.lerk.soultraps.components;

import greenfoot.Actor;
import greenfoot.Color;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import io.lerk.soultraps.items.Effect;
import io.lerk.soultraps.items.Item;
import io.lerk.soultraps.levels.Level;
import io.lerk.soultraps.levels.types.HellLevel;
import io.lerk.soultraps.mobs.player.Player;
import io.lerk.soultraps.sys.Fonts;
import io.lerk.soultraps.sys.console.ConsoleUtil;

import java.util.ArrayList;

import static io.lerk.soultraps.levels.Level.CELL_SIZE;
import static io.lerk.soultraps.sys.Soultraps.Controls.*;

/**
 * A HUD showing the player's current health and other stuff.
 *
 * @author Lukas Fülling (lukas@k40s.net)
 * @see Player
 */
public class HUD extends Actor {

    private static final int WIDTH = 9 * CELL_SIZE;

    private static final int HEIGHT = 7 * CELL_SIZE;

    /**
     * Counter value used for throttling.
     */
    private long lastUpdateMillis;

    private int lastHealth;

    private int selectedItem = 0;

    /**
     * Constructor.
     */
    public HUD() {
        updateImage();
    }

    /**
     * Updates the HUD's image if necessary.
     */
    @Override
    public void act() {
        if (System.currentTimeMillis() - lastUpdateMillis >= 100) {
            updateImage();
            handleItemSelection();
            lastUpdateMillis = System.currentTimeMillis();
        }
    }

    private void handleItemSelection() {
        if (!ConsoleUtil.isConsoleOpen((Level) getWorld())) {
            ArrayList<Item> items = Player.getSelf().getItems();
            if (Greenfoot.isKeyDown(USE_ITEM)) {
                if (items.size() > selectedItem) {
                    items.get(selectedItem).getEffects().forEach(Effect::handle);
                }
            } else if (Greenfoot.isKeyDown(SEL_ITEM_DN) && items.size() > 0) {
                if (selectedItem > 0) {
                    selectedItem--;
                } else if (selectedItem < 0) {
                    selectedItem = 0;
                }
            } else if (Greenfoot.isKeyDown(SEL_ITEM_UP) && items.size() > 0) {
                if (selectedItem >= items.size()) {
                    selectedItem = items.size() - 1;
                } else {
                    selectedItem++;
                }
            } else if (items.size() <= 1) {
                selectedItem = 0;
            } else if (items.size() <= selectedItem) {
                selectedItem = items.size() - 1;
            }
        }
    }

    /**
     * Internal update method that is throttled.
     * This should call {@link #setImage(GreenfootImage)}.
     */
    private void updateImage() {
        GreenfootImage textImage = new GreenfootImage(WIDTH, HEIGHT);
        Integer health = Player.getSelf().getHealth();
        if (health > lastHealth) {
            textImage.setColor(Color.GREEN);
        } else if (health < lastHealth) {
            textImage.setColor(Color.RED);
        } else {
            textImage.setColor(Color.WHITE);
        }
        textImage.setFont(Fonts.getFont(Fonts.Types.SKYRIM, 32f));
        textImage.drawString(health + " HP", 0, 24);
        lastHealth = health;
        textImage.setFont(Fonts.getFont(Fonts.Types.SKYRIM, 20f));
        textImage.drawString(getSelectedItemString(), 0, 52);
        setImage(textImage);
    }

    private String getSelectedItemString() {
        if (Player.getSelf().getItems().size() == 0) {
            return "Inventory Empty";
        }
        String res = "";
        if (Player.getSelf().getItems().size() >= (selectedItem + 1)) {
            if (selectedItem > 0) {
                res += "< ";
            }
            res += Player.getSelf().getItems().get(selectedItem).getName();
            if (selectedItem + 1 < Player.getSelf().getItems().size()) {
                res += " >";
            }
        }
        return res;
    }
}
