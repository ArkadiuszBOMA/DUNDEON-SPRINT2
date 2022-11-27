package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Skeleton extends Enemies {
    public Skeleton(Cell cell) {

        super(cell);
        health = 2;
        power = 1;
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }
}
