package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Diamond extends Item {
    int healerPower =20;

    public int getDiamond() {
        return healerPower;
    }

    public void setDiamond(int healerPower) {
        this.healerPower = healerPower;
    }

    public Diamond(Cell cell, String name, boolean canTake) {
        super(cell, name, canTake);
    }

    public String getTileName() {
        return "diamond";
    }

}
