package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.controllers.SoundEffect;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.actors.Enemies;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Random;


public class Main extends Application {
    //10 poziomów gry
    int level = 1;

    Button exitButton;
    Button playButton;

    GameMap map = MapLoader.loadMap(level);

    Canvas canvas = new Canvas(
            25 * Tiles.TILE_WIDTH,
            21 * Tiles.TILE_WIDTH);

    GraphicsContext context = canvas.getGraphicsContext2D();

    Label nameLabel = new Label();
    Label gameLevel = new Label();
    Label healthLabel = new Label();
    Label Weapon = new Label();
    Label Healer = new Label();
    Label Shield = new Label();
    Label Key = new Label();
    Label Info = new Label();
    ImageView keyIcon = new ImageView();

    Stage stage;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        setLevel(1);
        this.stage = primaryStage;
        gameParameters(primaryStage);
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void gameParameters(Stage primaryStage) throws IOException {
            // klawisze
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/fxml/logged-in.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 500, 400);
            stage.setTitle("AREK!");
            stage.setScene(scene);
            stage.show();
//
//
//        // klawisze
//        Button startGameButton = new Button("Start");
//        startGameButton.setId("play");
//        Button exitGame = new Button("Exit");
//
//        exitGame.addEventFilter(MouseEvent.MOUSE_CLICKED, (e) -> {
//                try{
//                    gameQuit(primaryStage);
//                } catch (Exception exception) {
//                    exception.printStackTrace();
//                }
//            });
//        exitGame.setId("quit");
//
//        HBox buttons = new HBox(exitGame, startGameButton);
//        buttons.setSpacing(10);
//
//        Text name = new Text(" Tell me your name Witcher ");
//        name.setFont(Font.font("Helvetica", FontWeight.BLACK, 30));
//        name.setFill(Color.WHITE);
//
//
//        TextField textField = new TextField();
//        textField.setId("name");
//
//        VBox parameters = new VBox(name, textField, buttons);
//        parameters.setAlignment(Pos.TOP_RIGHT);
//
//        startGameButton.addEventFilter(MouseEvent.MOUSE_CLICKED, (e) -> {
//            try{
//                map = MapLoader.loadMap(1);
//                map.getPlayer().setName(textField.getText());
//                if(map.getPlayer().getName().isBlank()){
//                    map.getPlayer().setName("Anonymous");
//                }
//                nameLabel.setText("" + map.getPlayer().getName());
//                nameLabel.setFont(Font.font("Thoma", FontWeight.BOLD,15));
//                gameStart(primaryStage);
//            } catch (Exception exception) {
//                exception.printStackTrace();
//            }
//        });
//
//        BorderPane startLayout = new BorderPane();
//        startLayout.setRight(parameters);
//        startLayout.setPrefHeight(350);
//        startLayout.setPrefWidth(1100);
//
//        buttons.setAlignment(Pos.CENTER);
//        buttons.setPadding(new Insets(50,5,15,5));
//        buttons.setSpacing(10);
//
//        Scene scene = new Scene(startLayout);
//        scene.getStylesheets().add("css/start.css");
//
//        primaryStage.setScene(scene);
//        primaryStage.setTitle("Game");
//        primaryStage.show();

    }

    public void gameQuit(Stage primaryStage) throws IOException {
        // klawisze
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/fxml/goodbye.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 500, 400);
            stage.setTitle("AREK!");
            stage.setScene(scene);
            stage.show();

//        Button exitGame = new Button("Exit");
//        exitGame.addEventFilter(MouseEvent.MOUSE_CLICKED, (e) -> System.exit(0));
//        exitGame.setId("quit");
//        HBox buttons = new HBox(exitGame);
//        buttons.setSpacing(10);
//
//        VBox parameters = new VBox(buttons);
//        parameters.setAlignment(Pos.TOP_RIGHT);
//
//        BorderPane startLayout = new BorderPane();
//        startLayout.setRight(parameters);
//        startLayout.setPrefHeight(850);
//        startLayout.setPrefWidth(1000);
//
//        buttons.setAlignment(Pos.CENTER);
//        buttons.setPadding(new Insets(50,5,15,5));
//        buttons.setSpacing(10);
//
//        Scene scene = new Scene(startLayout);
//        scene.getStylesheets().add("css/end.css");
//
//        primaryStage.setScene(scene);
//        primaryStage.setTitle("Game");
//        primaryStage.show();

    }

    public void gameStart(Stage primaryStage) {

        SplitPane mainBoard = new SplitPane();
        mainBoard.setPrefHeight(350);
        mainBoard.setPrefWidth(1100);
        mainBoard.setOrientation(Orientation.VERTICAL);
        mainBoard.setDividerPosition(0,0.7);

        SplitPane topPart = new SplitPane();
        SplitPane.isResizableWithParent(mainBoard);
        topPart.setOrientation(Orientation.HORIZONTAL);

        SplitPane topRight = new SplitPane();
        topRight.setOrientation(Orientation.VERTICAL);

        Info.setText(" PLAY MODE ");
        Info.setFont(Font.font("Thoma", FontWeight.BOLD,15));
        exitButton =  new Button("Quit");
        playButton = new Button("Start Again");
        exitButton.setId("quit");
        playButton.setId("play");
        exitButton.addEventFilter(MouseEvent.MOUSE_CLICKED, (e) -> {
                    try{
                        gameQuit(primaryStage);
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                });
        playButton.addEventFilter(MouseEvent.MOUSE_CLICKED, (e) -> {
            try{
                start(primaryStage);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        hideExit();
        hidePlay();

        HBox buttons = new HBox(exitButton, playButton);
        buttons.setSpacing(20);
        buttons.setFillHeight(true);
        buttons.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY , Insets.EMPTY)));
        buttons.setAlignment(Pos.CENTER);

        Image playerIcon = new Image("/icones/player.png");
        Image heartIcon = new Image("/icones/heart.png");
        Image swordIcon = new Image("/icones/sword.png");
        Image shieldIcon = new Image("/icones/shield.png");
        Image drinkIcon = new Image("/icones/drink.png");

        ImageView playerIconView = new ImageView();
        ImageView heartIconView = new ImageView();
        ImageView swordIconView = new ImageView();
        ImageView shieldIconView = new ImageView();
        ImageView drinkIconView = new ImageView();

        SplitPane player = new SplitPane();
        player.setOrientation(Orientation.VERTICAL);
        player.setDividerPositions(0.5, 0.5);
        playerIconView.setImage(playerIcon);
        player.getItems().add(0, playerIconView);
        player.getItems().add(1, nameLabel);
        nameLabel.setFont(Font.font("Arial", FontWeight.BLACK, 20));
        nameLabel.setTextFill(Color.RED);


        SplitPane live = new SplitPane();
        live.setOrientation(Orientation.HORIZONTAL);
        live.setDividerPositions(0.4, 0.4, 0.2);
        live.getItems().add(0,new Text("lives       "));
        heartIconView.setImage(heartIcon);
        live.getItems().add(1, heartIconView);
        live.getItems().add(2, healthLabel);

        Text breakText = new Text("Inventory");
        breakText.setFont(Font.font("Verdana", FontWeight.BLACK, 20));
        breakText.setFill(Color.RED);

        SplitPane sword = new SplitPane();
        sword.setOrientation(Orientation.HORIZONTAL);
        sword.setDividerPositions(0.4, 0.4, 0.2);
        sword.getItems().add(0,new Text("sword     "));
        swordIconView.setImage(swordIcon);
        sword.getItems().add(1, swordIconView);
        sword.getItems().add(2, Weapon);

        SplitPane shield = new SplitPane();
        shield.setOrientation(Orientation.HORIZONTAL);
        shield.setDividerPositions(0.4, 0.4, 0.2);
        shield.getItems().add(0,new Text("shield     "));
        shieldIconView.setImage(shieldIcon);
        shield.getItems().add(1, shieldIconView);
        shield.getItems().add(2, Shield);

        SplitPane drinks = new SplitPane();
        drinks.setOrientation(Orientation.HORIZONTAL);
        drinks.setDividerPositions(0.4, 0.4, 0.2);
        drinks.getItems().add(0,new Text("drinks     "));
        drinkIconView.setImage(drinkIcon);
        drinks.getItems().add(1, drinkIconView);
        drinks.getItems().add(2, Healer);

        SplitPane keyDisplay = new SplitPane();
        keyDisplay.setOrientation(Orientation.HORIZONTAL);
        keyDisplay.setDividerPositions(0.4, 0.4, 0.2);
        keyDisplay.getItems().add(0,new Text("Key status"));
        keyDisplay.getItems().add(1, keyIcon);
        keyDisplay.getItems().add(2, Key);

        SplitPane gameLevelA = new SplitPane();
        gameLevelA.setOrientation(Orientation.HORIZONTAL);
        gameLevelA.setDividerPositions(0.5, 0.5);
        gameLevelA.getItems().add(0,new Text("Game Level"));
        gameLevelA.getItems().add(1, gameLevel);

        Pane InfoText = new Pane();
        InfoText.getChildren().add(Info);
        InfoText.setId("text");

        topRight.getItems().add(0,player);
        topRight.getItems().add(1,live);
        topRight.getItems().add(2,breakText);
        topRight.getItems().add(3,sword);
        topRight.getItems().add(4,shield);
        topRight.getItems().add(5,drinks);
        topRight.getItems().add(6,new Text(" "));
        topRight.getItems().add(7,keyDisplay);
        topRight.getItems().add(8,gameLevelA);
        topRight.getItems().add(9,InfoText);

        topPart.getItems().add(0,canvas);
        topPart.getItems().add(1,topRight);

        mainBoard.getItems().add(0, topPart);
        mainBoard.getItems().add(1, buttons);

        Scene scene = new Scene(mainBoard);

        primaryStage.setScene(scene);
        scene.getStylesheets().add("css/start.css");

        refresh();

        scene.setOnKeyPressed(this::onKeyPressed);
        primaryStage.setTitle("Game");
        primaryStage.show();
    }

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

    private void hideExit() {
        exitButton.setVisible(false);
    }
    private void hidePlay() {
        playButton.setVisible(false);
    }

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
                Info.setText(" !!! You are DEAD !!! ");
                SoundEffect dead = new SoundEffect();
                SoundEffect hit = new SoundEffect();
                hit.soundStop(SoundEffect.getHit());
                dead.soundPlay(SoundEffect.getDead());

                exitButton.setVisible(true);
                playButton.setVisible(true);
        }

        if(level == 10 && coutEnemies == 0){
            Info.setText(" !!! You WIN !!! ");
            SoundEffect win = new SoundEffect();
            win.soundPlay(SoundEffect.getWin());

            exitButton.setVisible(true);
            playButton.setVisible(true);
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

    private void refreshLabels() {
        Image keyIconYes = new Image("/icones/key.png");
        Image keyIconNo = new Image("/icones/key_no.png");
        gameLevel.setText("" + this.level);
        healthLabel.setText("" + map.getPlayer().getHealth());
        Healer.setText("" + Array.get(map.getPlayer().getInventory(), 0));
        if(map.getPlayer().getInventory()[1] == 0) {
            Weapon.setText(" You don't have sword ");
        } else {
            Weapon.setText(" You have sword ");
        }

        if(map.getPlayer().getInventory()[2] == 0) {
            Shield.setText(" You don't have shield");
        } else {
            Shield.setText(" You have shield");
        }

        if (!map.getPlayer().isKey()) {
            Key.setText("You need key ");
            keyIcon.setImage(keyIconNo);
        } else {
            Key.setText("You have THE key");
            keyIcon.setImage(keyIconYes);
        }
        if (map.nextLevel() && map.getPlayer().isKey() && level <10) {
            setLevel(level+1);
            map = MapLoader.loadMap(level);
            canvas.setWidth(25 * Tiles.TILE_WIDTH + 12);
            canvas.setHeight(21 * Tiles.TILE_WIDTH + 12);
            Info.setText(" PLAY MODE ");
            Info.setFont(Font.font("Thoma", FontWeight.BOLD,15));
        }
    }
}
