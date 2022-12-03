package com.codecool.dungeoncrawl.controllers;

import com.codecool.dungeoncrawl.utils.SoundEffect;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class WelcomeController{
    private MainController mainController;
    @FXML Button button_logout_w;
    @FXML
    Label label_username;
    @FXML ImageView avatar_w;
    @FXML Button button_loud_w;
    @FXML Button button_play;


    @FXML
    public void exitScreen(){
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/fxml/ExitScreen.fxml"));
        BorderPane borderPane;
        try{
            borderPane = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SoundEffect intro = new SoundEffect();
        intro.soundPlay(SoundEffect.getIntro());
        ExitController exitController = fxmlLoader.getController();
        exitController.setMainController(mainController);
        mainController.setScreen(borderPane);
    }

    @FXML
    public void gameScreen() throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/fxml/GameScreen.fxml"));
        BorderPane borderPane;
        try{
            borderPane = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        GameController gameController = fxmlLoader.getController();
        gameController.setMainController(mainController);
        gameController.start(label_username.getText());
        mainController.setScreen(borderPane);

    }


    public void setMainController(MainController mainController) {this.mainController = mainController;}
}