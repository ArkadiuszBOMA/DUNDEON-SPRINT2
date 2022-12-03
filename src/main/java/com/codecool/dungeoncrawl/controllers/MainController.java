package com.codecool.dungeoncrawl.controllers;
import com.codecool.dungeoncrawl.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MainController {

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    public void initialize() {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/fxml/LogInScreen.fxml"));
        BorderPane borderPane = null;
        try {
            borderPane = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        LogInController logInController = fxmlLoader.getController();
        logInController.setMainController(this);
        setScreen(borderPane);
    }
    public void setScreen(BorderPane borderPane) {
        mainBorderPane.getChildren().clear();
        mainBorderPane.getChildren().add(borderPane);
    }
}