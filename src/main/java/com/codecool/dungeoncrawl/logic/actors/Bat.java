package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Bat extends Enemies {
    public Bat(Cell cell) {
        super(cell);
        health = 6;
        power = 3;
        shield = 0;
    }

    @Override
    public String getTileName() {
        return "bat";
    }
}
