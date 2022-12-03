package com.codecool.dungeoncrawl.controllers;

import com.codecool.dungeoncrawl.utils.UtilsTextHandling;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class ExitController {

    private MainController mainController;
    @FXML
    private Button button_logout;
    @FXML
    private Text playerName;
    @FXML
    void initialize() {
        button_logout.setOnAction(event -> System.exit(0));
    }
    public void setMainController(MainController mainController) {}
    @FXML
    public void setPlayerName(String playerName) {this.playerName.setText(UtilsTextHandling.capitalized(playerName));}}
