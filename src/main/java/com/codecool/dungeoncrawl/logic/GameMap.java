package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.*;

public class GameMap {
    private int width;
    private int height;
    private Cell[][] cells;

    private Player player;
    private Bat bat;
    private Ghost ghost;
    private Gnome gnome;
    private Scorpio scorpio;
    private Skeleton skeleton;
    private Spider spider;


    public GameMap(int width, int height, CellType defaultCellType) {
        this.width = width;
        this.height = height;
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(this, x, y, defaultCellType);
            }
        }
    }

    public Cell getCell(int x, int y) {return cells[x][y];}
    public void setPlayer(Player player) {this.player = player;}
    public void setGhost(Ghost ghost) {this.ghost = ghost;}
    public void setBat(Bat bat) {this.bat = bat;}
    public void setGnome(Gnome gnome) {this.gnome = gnome;}
    public void setScorpio(Scorpio scorpio) {this.scorpio = scorpio;}
    public void setSkeleton(Skeleton skeleton) {this.skeleton = skeleton;}
    public void setSpider(Spider spider) {this.spider = spider;}


    public Player getPlayer() {return player;}

    public int getWidth() {return width;}
    public int getHeight() {return height;}

    public boolean nextLevel() {
        Cell playerCell = player.getCell();
        return playerCell.getType() == CellType.EXIT;
    }
}
