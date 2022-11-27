
package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Helmet extends Item {
    int protectionPower = 1;


    public int getShield() {
        return protectionPower;
    }

    public void setShield(int shield) {
        this.protectionPower = shield;
    }

    public Helmet(Cell cell, String name, boolean canTake) {
        super(cell, name, canTake);
    }

    @Override
    public String getTileName() {
        return "helmet";
    }



}
