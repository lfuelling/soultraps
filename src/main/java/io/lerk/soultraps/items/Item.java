package io.lerk.soultraps.items;

import greenfoot.Actor;
import io.lerk.soultraps.mobs.player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Base item class.
 *
 * @author Lukas Fülling (lukas@k40s.net)
 */
public abstract class Item extends Actor {

    /**
     * Item name.
     */
    private String name;

    /**
     * Item description.
     */
    private String description;

    /**
     * Item value.
     */
    private int value;

    /**
     * Item weight.
     */
    private int weight;

    /**
     * Item effects.
     */
    protected List<Effect> effects;

    /**
     * Constructor.
     *
     * @param name        the name
     * @param description the description
     * @param value       the value
     * @param weight      the weight
     * @param effects     the item effects
     */
    public Item(String name, String description, int value, int weight, List<Effect> effects) {
        this.name = name;
        this.description = description;
        this.value = value;
        this.weight = weight;
        this.effects = effects;
    }

    public Item(String name, String description, int value, int weight) {
        this(name, description, value, weight, new ArrayList<>());
    }

    @Override
    public void act() {
        super.act();
        if (isTouching(Player.class)) {
            getWorld().removeObject(this);
            Player.getSelf().addItem(this);
        }
    }

    /**
     * Getter for item name.
     *
     * @return item name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for item name.
     *
     * @param name item name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for item description.
     *
     * @return item description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for description.
     *
     * @param description item description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter for item value.
     *
     * @return item value
     */
    public int getValue() {
        return value;
    }

    /**
     * Setter for item value.
     *
     * @param value item value
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Getter for item weight.
     *
     * @return item weight
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Setter for item weight.
     *
     * @param weight item weight
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * Getter for item effects.
     *
     * @return item effects.
     */
    public List<Effect> getEffects() {
        return effects;
    }

    public static boolean addNextToPlayer(Item item) {
        return addNextToPlayer(item, 1);
    }

    public static boolean addNextToPlayer(Item item, int distance) {
        switch (Player.getSelf().getDirection()) {
            case NORTH:
                Player.getSelf().getWorld().addObject(item,
                        Player.getSelf().getX(), Player.getSelf().getY() - distance);
                break;
            case SOUTH:
                Player.getSelf().getWorld().addObject(item,
                        Player.getSelf().getX(), Player.getSelf().getY() + distance);
                break;
            case WEST:
                Player.getSelf().getWorld().addObject(item,
                        Player.getSelf().getX() - distance, Player.getSelf().getY());
                break;
            case EAST:
                Player.getSelf().getWorld().addObject(item,
                        Player.getSelf().getX() + distance, Player.getSelf().getY());
                break;
            default:
                return false;
        }
        return true;
    }
}
