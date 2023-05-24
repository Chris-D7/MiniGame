package org.example;

public class StaminaPotion extends Potion{
    public StaminaPotion(int stamina) {
        super(0, 0, stamina, "Stamina Potion");
    }
    @Override
    public int useItem(Player player) {
        Integer[] stats={0,stamina,0,0,0,0,0};
        player.applyStats(stats);
        return super.useItem(player);
    }
}
