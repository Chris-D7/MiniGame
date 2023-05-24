package org.example;

public abstract class Item implements Comparable<Item> {
    String name;
    protected int durability;
    protected Item(int durability, String name) {
        this.durability = durability;
        this.name=name;
    }
    public Item(){
        name="";
        durability=1;
    }
    public int useItem(Player player){
        durability-=1;
        return (durability);
    }

    @Override
    public int compareTo(Item o) {
        return this.name.compareTo(o.name);
    }
}
