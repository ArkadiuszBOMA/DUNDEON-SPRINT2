package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Spider extends Enemies {
    public Spider(Cell cell) {

        super(cell);
        health = 1;
        power = 2;
    }

    @Override
    public String getTileName() {
        return "spider";
    }
}
