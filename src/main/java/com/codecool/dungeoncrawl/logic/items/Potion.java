package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Potion extends Item {
    int healerPower =4;

    public int getHealer() {
        return healerPower;
    }

    public void setHealer(int healerPower) {
        this.healerPower = healerPower;
    }

    public Potion(Cell cell, String name, boolean canTake) {
        super(cell, name, canTake);
    }



    @Override
    public String getTileName() {return "potion";}

}
