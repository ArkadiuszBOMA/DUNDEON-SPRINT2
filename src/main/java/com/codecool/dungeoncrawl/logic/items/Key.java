package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Key extends Item {
    boolean isTaken = false;

    public boolean isTaken() {return isTaken;}

    public void setTaken(boolean taken) {
        this.isTaken = taken;
    }

    public Key(Cell cell, String name, boolean canTake) {
        super(cell, name, canTake);
    }

    @Override
    public String getTileName() {
        return "key";
    }
}
