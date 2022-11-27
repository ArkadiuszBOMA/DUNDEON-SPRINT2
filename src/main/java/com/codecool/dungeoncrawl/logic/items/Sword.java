
package com.codecool.dungeoncrawl.logic.items;
import com.codecool.dungeoncrawl.logic.Cell;

public class Sword extends Item {
    int hitPower = 1;
    public int getHitPower() {
        return hitPower;
    }

    public void setHitPower(int hitPower) {
        this.hitPower = hitPower;
    }

    public Sword(Cell cell, String name, boolean canTake) {
        super(cell, name, canTake);
    }


    @Override
    public String getTileName() {
        return "weapon";
    }

}
