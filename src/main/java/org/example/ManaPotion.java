package org.example;

public class ManaPotion extends Potion{
    public ManaPotion(int mana) {
        super(mana, 0, 0, "Mana Potion");
    }
    @Override
    public int useItem(Player player) {
        Integer[] stats={0,0,0,mana,0,0,0};
        player.applyStats(stats);
        return super.useItem(player);
    }
}
