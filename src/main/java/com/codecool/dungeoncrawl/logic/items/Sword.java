
package com.codecool.dungeoncrawl.logic.items;
import com.codecool.dungeoncrawl.logic.Cell;

public class Sword extends Item {
    int hitPower = 1;

    public int getSword() {
        return hitPower;
    }
    public void setSword(int sword) {
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
