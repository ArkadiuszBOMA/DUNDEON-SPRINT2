package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Ghost extends Enemies {
    public Ghost(Cell cell) {

        super(cell);
        health = 8;
        power = 5;
    }

    @Override
    public String getTileName() {
        return "ghost";
    }
}
