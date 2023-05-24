package org.example;

public class HealthPotion extends Potion{
    public HealthPotion(int health) {
        super(0, health, 0, "Health Potion");
    }
    @Override
    public int useItem(Player player) {
        Integer[] stats={0,0,0,0,0,health,0};
        player.applyStats(stats);
        return super.useItem(player);
    }
}
