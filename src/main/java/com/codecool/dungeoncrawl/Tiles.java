package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Tiles {
    public static int TILE_WIDTH = 32;

    private static final Image tileset = new Image("/images/tiles.png", 543 * 2, 543 * 2, true, false);
    private static final Map<String, Tile> tileMap = new HashMap<>();
    public static class Tile {
        public final int x, y, w, h;
        Tile(int i, int j) {
            x = i * (TILE_WIDTH + 2);
            y = j * (TILE_WIDTH + 2);
            w = TILE_WIDTH;
            h = TILE_WIDTH;
        }
    }

    static {
        //walls and doors
        tileMap.put("empty", new Tile(0, 0));
        tileMap.put("wall", new Tile(10, 17));
        tileMap.put("door", new Tile(3,3));
        tileMap.put("open door", new Tile(4,3));
        tileMap.put("exit", new Tile(13,17));
        tileMap.put("floor", new Tile(2, 0));
        //actors
        tileMap.put("bat", new Tile(26,8));
        tileMap.put("ghost", new Tile(27, 6));
        tileMap.put("player", new Tile(24, 1));
        tileMap.put("gnome", new Tile(26,9));
        tileMap.put("scorpio", new Tile(24,5));
        tileMap.put("skeleton", new Tile(29, 6));
        tileMap.put("spider", new Tile(28,5));
        //decors
        tileMap.put("tree", new Tile(3, 2));
        tileMap.put("bush", new Tile(6, 2));
        tileMap.put("decor", new Tile(27, 7));
        tileMap.put("grass", new Tile(5, 0));
        tileMap.put("candles", new Tile(5, 15));
        //items
        tileMap.put("diamond", new Tile(23,4));
        tileMap.put("heart", new Tile(23,22));
        tileMap.put("key", new Tile(16, 23));
        tileMap.put("potion", new Tile(16,25));
        tileMap.put("sword", new Tile(3,29));
        tileMap.put("shield", new Tile(7,26));
        tileMap.put("helmet", new Tile(23,2));

    }

    public static void drawTile(GraphicsContext context, Drawable d, int x, int y) {
        Tile tile = tileMap.get(d.getTileName());
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                x * TILE_WIDTH, y * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
    }
}
