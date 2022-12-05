
package com.codecool.dungeoncrawl.logic.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Helmet extends Item {
    int protectionPower = 1;


    public int getHelmet() {
        return protectionPower;
    }
    public void setHelmet(int helmet) {
        this.protectionPower = helmet;
    }

    public Helmet(Cell cell, String name, boolean canTake) {
        super(cell, name, canTake);
    }

    @Override
    public String getTileName() {
        return "helmet";
    }



}
