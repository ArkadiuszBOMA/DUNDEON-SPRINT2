package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Gnome extends Enemies {
    public Gnome(Cell cell) {
        super(cell);
        health = 5;
        power = 4;
    }

    @Override
    public String getTileName() {
        return "gnome";
    }
}
