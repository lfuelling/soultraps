package io.lerk.soultraps.items;

import java.util.List;

public class Item {
    private String name;
    private String description;
    private int value;
    private int weight;
    private int damage;
    private List<Effect> effects;

    public Item(String name, String description, int value, int weight, int damage, List<Effect> effects) {
        this.name = name;
        this.description = description;
        this.value = value;
        this.weight = weight;
        this.damage = damage;
        this.effects = effects;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public List<Effect> getEffects() {
        return effects;
    }

    public void setEffects(List<Effect> effects) {
        this.effects = effects;
    }
}
