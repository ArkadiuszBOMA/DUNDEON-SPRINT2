package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;

public class Enemies extends Actor {
    public Enemies(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "enemies";
    }
}
