package com.codecool.dungeoncrawl.controllers;
import com.codecool.dungeoncrawl.utils.DBUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

public class SigneUpController {
    private MainController mainController;

    @FXML
    public TextField tf_username;
    @FXML
    public TextField tf_lastname;
    @FXML
    public TextField tf_firstname;
    @FXML
    public TextField tf_email;
    @FXML
    public TextField tf_age;
    @FXML
    public PasswordField tf_password;
    @FXML
    public PasswordField tf_repassword;
    @FXML
    private Button button_sign_in;

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
        exitController.setPlayerName(tf_username.getText());
        mainController.setScreen(borderPane);
    }
    @FXML
    public void logInScreen(){
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

    @FXML
    void initialize(){
        button_sign_in.setOnAction(event -> {
            boolean username;
            try {
                username = DBUtils.signeUpUser(
                        tf_firstname.getText(), tf_lastname.getText(),
                        tf_email.getText(), tf_age.getText(),
                        tf_password.getText(),
                        tf_repassword.getText(),
                        tf_username.getText());
            } catch (SQLException | NoSuchAlgorithmException | InvalidKeySpecException e) {
                throw new RuntimeException(e);
            }

            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/fxml/LogInScreen.fxml"));
            if(username) {
                        BorderPane borderPane;
                try{
                    borderPane = fxmlLoader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                LogInController logInController = fxmlLoader.getController();
                logInController.setMainController(mainController);
                mainController.setScreen(borderPane);
            } else {
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
        });
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
