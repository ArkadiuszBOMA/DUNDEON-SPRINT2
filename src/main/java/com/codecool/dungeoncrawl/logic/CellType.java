package com.codecool.dungeoncrawl.logic;

public enum CellType {
    WALL("wall"),
    EMPTY("empty"),
    FLOOR("floor"),
    EXIT("exit"),
    DOOR("door"),
    OPEN_DOOR("open door"),

    BUSH("bush"),
    GRASS("grass"),
    CANDLES("candles"),
    DECOR("decor"),
    TREE("tree"),

    HEALER("healer"),

    DIAMOND("diamond"),
    HEART("heart"),
    KEY("key"),
    POTION("potion"),
    MEDICINE("MEDICINE"),
    SHIELD("shield"),
    SWORD("sword"),
    HELMET("helmet");



    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
