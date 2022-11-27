package com.codecool.dungeoncrawl.logic.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.items.Item;

public class Player extends Actor {
    public Player(Cell cell) {
        super(cell);
    }

    @Override
    public String getName() {return super.getName();}
    @Override
    public void setName(String name) {super.setName(name);}


    @Override
    public void setHealth(int health) {super.setHealth(health);}
    @Override
    public void setPower(int power) {super.setPower(power);}
    @Override
    public void move(int dx, int dy) {super.move(dx, dy);}


    @Override
    public int getX() {
        return super.getX();
    }
    @Override
    public int getY() {
        return super.getY();
    }
    @Override
    public Cell getCell() {
        return super.getCell();
    }
    @Override
    public int getHealth() {
        return super.getHealth();
    }
    @Override
    public int getPower() {return super.getPower();}
    @Override
    public int getShield() {
        return super.getShield();
    }
    @Override
    public boolean isKey() {
        return super.isKey();
    }
    @Override
    public int[] getInventory() {
        return super.getInventory();
    }
    @Override
    public void takeItem(Item item) {
        super.takeItem(item);
    }
    @Override
    public String getTileName() {
        return "player";
    }
}
