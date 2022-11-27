package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Heart extends Item {
    int healerPower =5;

    public int getHeart() {
        return healerPower;
    }


    public void setHeart(int healerPower) {
        this.healerPower = healerPower;
    }

    public Heart(Cell cell, String name, boolean canTake) {
        super(cell, name, canTake);
    }


    @Override
    public String getTileName() {return "healer";}

}
