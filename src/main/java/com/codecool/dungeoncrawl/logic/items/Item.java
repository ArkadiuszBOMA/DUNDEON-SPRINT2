package com.codecool.dungeoncrawl.logic.items;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.Drawable;

public abstract class Item implements Drawable {
    private final boolean canTake;
    private Cell cell;
    private final String name;


    protected Item(Cell cell, String name, boolean canTake) {
        this.cell = cell;
        this.cell.setItem(this);
        this.name = name;
        this.canTake = canTake;
    }

    public Cell getCell() {
        return cell;
    }
    public boolean canTake() {return canTake;}
    public String getName() {return name;}
    public void setCell(Cell cell) {
        this.cell = cell;
    }

}
