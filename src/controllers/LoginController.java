package controllers;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    Main main;
    @FXML
    private Button createAccountButton;

    @FXML
    private Button forgotButton;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField userField;

    @FXML
    void createAccountAction(ActionEvent event) {

    }

    @FXML
    void forgotAction(ActionEvent event) {

    }

    @FXML
    void signInAction(ActionEvent event) {

    }

    public void setMain(Main main) {
        this.main = main;

    }
}
