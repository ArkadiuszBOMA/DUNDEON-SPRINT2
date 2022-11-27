package com.codecool.dungeoncrawl.controllers;

import com.codecool.dungeoncrawl.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SigneUpController implements Initializable {

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
    private Button button_log_in;
    @FXML
    private Button button_sign_in;
    @FXML
    private Button button_exit;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String title = "LOGOUT";
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/fxml/goodbye.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 500, 400);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.setTitle(title);
                    stage.show();

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        button_log_in.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String title = "LOGIN";
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/fxml/logged-in.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 500, 400);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.setTitle(title);
                    stage.show();

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    public void setUserInformations(String username) {
        button_sign_in.setText("Welcome "+ username + " !");
    }
}
