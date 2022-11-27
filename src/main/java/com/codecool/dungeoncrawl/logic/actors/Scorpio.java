package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Scorpio extends Enemies {
    public Scorpio(Cell cell) {

        super(cell);
        health = 2;
        power = 3;
    }

    @Override
    public String getTileName() {
        return "scorpio";
    }
}
