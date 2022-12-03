package com.codecool.dungeoncrawl.controllers;

import com.codecool.dungeoncrawl.Tiles;
import com.codecool.dungeoncrawl.utils.SoundEffect;
import com.codecool.dungeoncrawl.utils.UtilsTextHandling;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.actors.Enemies;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Random;


public class GameController{

    private MainController mainController;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    int level = 1;
    @FXML
    TextField player;
    @FXML
    TextField c_heart;
    @FXML
    Text infoText;
    @FXML
    TextField c_potion;
    @FXML
    TextField c_diamond;
    @FXML
    ImageView c_shield;
    @FXML
    ImageView c_helmet;
    @FXML
    ImageView c_sword;
    @FXML
    ImageView key;
    @FXML
    TextField game_level;
    @FXML
    Button button_save_g;
    @FXML
    Button button_quit_g;

    @FXML
    public void setCanvas(Canvas canvas) {this.canvas = canvas;}

    @FXML
    GameMap map = MapLoader.loadMap(1);
    @FXML
    Canvas canvas = new Canvas(
            25 * Tiles.TILE_WIDTH,
            20 * Tiles.TILE_WIDTH);
    @FXML
    GraphicsContext context = canvas.getGraphicsContext2D();

    @FXML
    public void start(String name) throws Exception {
        setLevel(1);
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/fxml/GameScreen.fxml"));
        BorderPane borderPane;
        try{
            borderPane = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        name = UtilsTextHandling.capitalized(name);
        player.setText(name);
        game_level.setText("Game level 1");


        map.getPlayer().setName(player.getText());

        infoText.setText("Find the key");

        c_heart.setText("" + map.getPlayer().getHealth());
        c_potion.setText("" + Array.get(map.getPlayer().getInventory(), 5));
        c_diamond.setText("" + Array.get(map.getPlayer().getInventory(), 4));

        mainController.setScreen(borderPane);

        refresh();
    }

    @FXML
    public void setLevel(int level){this.level = level;}
    @FXML
    public void welcomeScreen() {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/fxml/WelcomeScreen.fxml"));
        BorderPane borderPane;
        try{
            borderPane = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        WelcomeController welcomeController = fxmlLoader.getController();
        welcomeController.label_username.setText(this.player.getText());
        welcomeController.setMainController(mainController);
        mainController.setScreen(borderPane);
    }
    @FXML
    public void exitScreen() {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/fxml/ExitScreen.fxml"));
        BorderPane borderPane;
        try{
            borderPane = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ExitController exitController = fxmlLoader.getController();
        exitController.setPlayerName(this.player.getText());
        exitController.setMainController(mainController);
        mainController.setScreen(borderPane);
    }


    @FXML
    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP:
                map.getPlayer().move(0, -1);
                enemyMove();
                refresh();
                break;
            case DOWN:
                map.getPlayer().move(0, 1);
                enemyMove();
                refresh();
                break;
            case LEFT:
                map.getPlayer().move(-1, 0);
                enemyMove();
                refresh();
                break;
            case RIGHT:
                map.getPlayer().move(1,0);
                enemyMove();
                refresh();
                break;
        }
    }

    @FXML
    private void enemyMove(String direction, Cell cell) {
        switch (direction) {
            case "UP":
                if (!map.getCell(cell.getX(), cell.getY()-1).isEnemy()
                        && !map.getCell(cell.getX(), cell.getY()-1).getType().equals(CellType.WALL)
                        && !map.getCell(cell.getX(), cell.getY()-1).getType().equals(CellType.DOOR)
                        && !map.getCell(cell.getX(), cell.getY()-1).getType().equals(CellType.TREE)
                        && !map.getCell(cell.getX(), cell.getY()-1).getType().equals(CellType.CANDLES)
                        && !map.getCell(cell.getX(), cell.getY()-1).getType().equals(CellType.BUSH)
                        && (map.getCell(cell.getX(), cell.getY()-1).getType().equals(CellType.FLOOR)
                        || map.getCell(cell.getX(), cell.getY()-1).getType().equals(CellType.GRASS))) {
                    if(!map.getCell(cell.getX(), cell.getY()-1).isPlayer()){
                        map.getCell(cell.getX(), cell.getY()-1).setActor(cell.getActor());
                        cell.setActor(null);
                        cell.setType(CellType.FLOOR);
                        break;}
                    else {
                        cell.getActor().attackPlayer(map.getCell(cell.getX(), cell.getY()-1).getActor(), cell);
                        break;
                    }

                }
            case "DOWN":
                if (!map.getCell(cell.getX(), cell.getY()+1).isEnemy()
                        && !map.getCell(cell.getX(), cell.getY()+1).getType().equals(CellType.WALL)
                        && !map.getCell(cell.getX(), cell.getY()+1).getType().equals(CellType.DOOR)
                        && !map.getCell(cell.getX(), cell.getY()+1).getType().equals(CellType.TREE)
                        && !map.getCell(cell.getX(), cell.getY()-1).getType().equals(CellType.CANDLES)
                        && !map.getCell(cell.getX(), cell.getY()+1).getType().equals(CellType.BUSH)
                        && (map.getCell(cell.getX(), cell.getY()+1).getType().equals(CellType.FLOOR)
                        || map.getCell(cell.getX(), cell.getY()+1).getType().equals(CellType.GRASS))) {
                    if(!map.getCell(cell.getX(), cell.getY()+1).isPlayer()){
                        map.getCell(cell.getX(), cell.getY()+1).setActor(cell.getActor());
                        cell.setActor(null);
                        cell.setType(CellType.FLOOR);
                        break;
                    }
                    else {
                        cell.getActor().attackPlayer(map.getCell(cell.getX(), cell.getY()+1).getActor(), cell);
                        break;
                    }
                }
            case "LEFT":
                if (!map.getCell(cell.getX()-1, cell.getY()).isEnemy()
                        && !map.getCell(cell.getX()-1, cell.getY()).getType().equals(CellType.WALL)
                        && !map.getCell(cell.getX()-1, cell.getY()).getType().equals(CellType.DOOR)
                        && !map.getCell(cell.getX()-1, cell.getY()).getType().equals(CellType.TREE)
                        && !map.getCell(cell.getX(), cell.getY()-1).getType().equals(CellType.CANDLES)
                        && !map.getCell(cell.getX()-1, cell.getY()).getType().equals(CellType.BUSH)
                        && (map.getCell(cell.getX()-1, cell.getY()).getType().equals(CellType.FLOOR)
                        || !map.getCell(cell.getX()-1, cell.getY()).getType().equals(CellType.GRASS))) {
                    if (!map.getCell(cell.getX() - 1, cell.getY()).isPlayer()) {
                        map.getCell(cell.getX() - 1, cell.getY()).setActor(cell.getActor());
                        cell.setActor(null);
                        cell.setType(CellType.FLOOR);
                        break;
                    } else {
                        cell.getActor().attackPlayer(map.getCell(cell.getX() - 1, cell.getY()).getActor(), cell);
                        break;
                    }
                }
            case "RIGHT":
                if (!map.getCell(cell.getX()+1, cell.getY()).isEnemy()
                        && !map.getCell(cell.getX()+1, cell.getY()).getType().equals(CellType.WALL)
                        && !map.getCell(cell.getX()+1, cell.getY()).getType().equals(CellType.DOOR)
                        && (map.getCell(cell.getX()+1, cell.getY()).getType().equals(CellType.FLOOR)
                        && !map.getCell(cell.getX()-1, cell.getY()).getType().equals(CellType.TREE)
                        && !map.getCell(cell.getX(), cell.getY()-1).getType().equals(CellType.CANDLES)
                        && !map.getCell(cell.getX()-1, cell.getY()).getType().equals(CellType.BUSH)
                        || map.getCell(cell.getX()+1, cell.getY()).getType().equals(CellType.GRASS))) {
                    if (!map.getCell(cell.getX() + 1, cell.getY()).isPlayer()) {
                        map.getCell(cell.getX() + 1, cell.getY()).setActor(cell.getActor());
                        cell.setActor(null);
                        cell.setType(CellType.FLOOR);
                        break;
                    } else {
                        cell.getActor().attackPlayer(map.getCell(cell.getX() + 1, cell.getY()).getActor(), cell);
                        break;
                    }
                }

        }
    }

    @FXML
    private void enemyMove() {
        String[] directions = {"UP", "DOWN", "LEFT", "RIGHT"};
        Random random = new Random();
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() instanceof Enemies) {
                    String direction = directions[random.nextInt(4)];
                    enemyMove(direction, cell);
                }
            }
        }
    }

    @FXML
    private void refresh() {
        int coutEnemies = 0;
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++){
                if(map.getCell(x,y).isEnemy()){
                    coutEnemies +=1;
                }
            }
        }
        if(map.getPlayer().getHealth()<=0){
            SoundEffect dead = new SoundEffect();
            SoundEffect hit = new SoundEffect();
            hit.soundStop(SoundEffect.getHit());
            dead.soundPlay(SoundEffect.getDead());
        }
        if(level == 10 && coutEnemies == 0){
            SoundEffect win = new SoundEffect();
            win.soundPlay(SoundEffect.getWin());
        }

        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for (int x = map.getPlayer().getX() - 100; x < map.getPlayer().getX() + 100; x++) {
            for (int y = map.getPlayer().getY() - 100; y < map.getPlayer().getY() + 100; y++) {
                Cell cell;
                try {
                    cell = map.getCell((x + map.getPlayer().getX()) - 12, y + map.getPlayer().getY() - 12);
                } catch (IndexOutOfBoundsException e) {
                    cell = new Cell(map, 1, 1, CellType.EMPTY);
                }
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x, y);
                } else {
                    Tiles.drawTile(context, cell, x, y);
                }
            }
        }
        refreshLabels();
    }

    @FXML
    private void refreshLabels() {
        game_level.setText("Game Level " + this.level);
        c_heart.setText("" + map.getPlayer().getHealth());
        c_potion.setText("" + Array.get(map.getPlayer().getInventory(), 5));
        c_diamond.setText("" + Array.get(map.getPlayer().getInventory(), 4));
        if(map.getPlayer().getInventory()[1] != 0) {c_sword.setVisible(true);}
        if(map.getPlayer().getInventory()[2] != 0) {c_shield.setVisible(true);}
        if(map.getPlayer().getInventory()[3] != 0) {c_helmet.setVisible(true);}
        if(map.getPlayer().getInventory()[7] != 0) {
            key.setVisible(true);
            infoText.setText("You have got the key, find the exit to move to next level");
            ;}
        if (map.nextLevel() && map.getPlayer().isKey() && level <10) {
            infoText.setText("Find the key ");
            setLevel(level+1);
            map = MapLoader.loadMap(level);
            canvas.setWidth(25 * Tiles.TILE_WIDTH + 12);
            canvas.setHeight(21 * Tiles.TILE_WIDTH + 12);

        }
    }
}
