package org.example;

public abstract class Potion extends Item{
    protected int mana;
    protected int health;
    protected int stamina;

    protected Potion(int mana, int health, int stamina, String name) {
        super(1, name);
        this.mana = mana;
        this.health = health;
        this.stamina = stamina;
    }
}
