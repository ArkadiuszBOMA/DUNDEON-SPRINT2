package com.codecool.dungeoncrawl.logic;
import com.codecool.dungeoncrawl.logic.actors.*;
import com.codecool.dungeoncrawl.logic.items.*;

import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {
    public static GameMap loadMap(int level) {
        String[] levelMap = new String[]{
                "/map/map_level1.txt", "/map/map_level2.txt", "/map/map_level3.txt","/map/map_level4.txt",
                "/map/map_level5.txt", "/map/map_level6.txt", "/map/map_level7.txt", "/map/map_level8.txt",
                "/map/map_level9.txt", "/map/map_level10.txt"};
        InputStream is = MapLoader.class.getResourceAsStream(levelMap[level-1]);
        assert is != null;
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, CellType.EMPTY);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case '=':
                            cell.setType(CellType.BUSH);
                            break;
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
                        case '^':
                            cell.setType(CellType.CANDLES);
                            break;
                        case ',':
                            cell.setType(CellType.GRASS);
                            break;
                        case '|':
                            cell.setType(CellType.TREE);
                            break;
                        case '%':
                            cell.setType(CellType.DECOR);
                            break;
                        case 'd':
                            cell.setType(CellType.DOOR);
                            break;
                        case 'o':
                            cell.setType(CellType.OPEN_DOOR);
                            break;
                        case 'x':
                            cell.setType(CellType.EXIT);
                            break;
                        case 's':
                            cell.setType(CellType.FLOOR);
                            map.setSkeleton(new Skeleton(cell));
                            break;
                        case 'b':
                            cell.setType(CellType.FLOOR);
                            map.setBat(new Bat(cell));
                            break;
                        case 'c':
                            cell.setType(CellType.FLOOR);
                            map.setScorpio(new Scorpio(cell));
                            break;
                        case 'r':
                            cell.setType(CellType.FLOOR);
                            map.setSpider(new Spider(cell));
                            break;
                        case 'g':
                            cell.setType(CellType.FLOOR);
                            map.setGnome(new Gnome(cell));
                            break;
                        case 't':
                            cell.setType(CellType.FLOOR);
                            map.setGhost(new Ghost(cell));
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            map.setPlayer(new Player(cell));
                            break;
                        case 'k':
                            cell.setType(CellType.KEY);
                            new Key(cell, "key", true);
                            break;
                        case 'p':
                            cell.setType(CellType.POTION);
                            new Potion(cell, "potion", true);
                            break;
                        case 'h':
                            cell.setType(CellType.HEART);
                            new Heart(cell, "heart", true);
                            break;
                        case '*':
                            cell.setType(CellType.DIAMOND);
                            new Diamond(cell, "diamond", true);
                            break;
                        case 'i':
                            cell.setType(CellType.SWORD);
                            new Sword(cell, "sword", true);
                            break;
                        case 'j':
                            cell.setType(CellType.SHIELD);
                            new Shield(cell, "shield", true);
                            break;
                        case 'w':
                            cell.setType(CellType.HELMET);
                            new Shield(cell, "helmet", true);
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }

}
