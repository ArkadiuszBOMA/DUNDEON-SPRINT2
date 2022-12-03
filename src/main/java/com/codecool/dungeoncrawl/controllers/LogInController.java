package com.codecool.dungeoncrawl.controllers;
import com.codecool.dungeoncrawl.utils.DBUtils;
import com.codecool.dungeoncrawl.utils.SoundEffect;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import java.io.IOException;

public class LogInController {
    private MainController mainController;

    @FXML
    private Button button_login;
    @FXML
    private TextField tu_username;
    @FXML
    private TextField tu_password;

    @FXML
    void initialize(){
        button_login.setOnAction(event -> {
            boolean logIn;
            logIn =  DBUtils.logInUser(tu_username.getText(), tu_password.getText());
            if(logIn){
            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/fxml/WelcomeScreen.fxml"));
            BorderPane borderPane;
            try{
                borderPane = fxmlLoader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            WelcomeController welcomeController = fxmlLoader.getController();
            SoundEffect intro = new SoundEffect();
            intro.soundPlay(SoundEffect.getIntro());
            welcomeController.label_username.setText(tu_username.getText());
            welcomeController.setMainController(mainController);
            mainController.setScreen(borderPane);
        } else {
                FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/fxml/LogInScreen.fxml"));
                BorderPane borderPane;
                try{
                    borderPane = fxmlLoader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                LogInController logInController = fxmlLoader.getController();
                logInController.setMainController(mainController);
                mainController.setScreen(borderPane);
            }

            }

        );
    };

    @FXML
    public void signUpScreen(){
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/fxml/SignUpScreen.fxml"));
        BorderPane borderPane;
        try{
            borderPane = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SigneUpController signeUpController = fxmlLoader.getController();
        signeUpController.setMainController(mainController);
        mainController.setScreen(borderPane);
    }

    @FXML
    public void exitScreen(){
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/fxml/ExitScreen.fxml"));
        BorderPane borderPane;
        try{
            borderPane = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ExitController exitController = fxmlLoader.getController();
        exitController.setMainController(mainController);
        exitController.setPlayerName(tu_username.getText());
        mainController.setScreen(borderPane);
    }


    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

}