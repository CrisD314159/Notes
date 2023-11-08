package controllers;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.User;

import java.io.IOException;

public class LoginController {
    Main main;
    private ModelFactoryController singleton = ModelFactoryController.getInstance();
    @FXML
    private Button createAccountButton;

    @FXML
    private Button AdministratorButton1;

    @FXML
    private Button forgotButton;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField userField;

    @FXML
    void signInAdministratorAction(ActionEvent event) throws IOException {
        main.openAdministratorLoginView();
    }

    @FXML
    void createAccountAction(ActionEvent event) throws IOException {
        main.openSignUpView();
    }

    @FXML
    void forgotAction(ActionEvent event) throws IOException {
        main.openRecoverPassword();
    }

    /**
     * Calls the singleton to verify the account
     * @param event
     * @throws IOException
     */
    @FXML
    void signInAction(ActionEvent event) throws IOException {
        String user = "";
        String password = "";
        user = userField.getText();
        password = passwordField.getText();
        if (verifyFields(user, password)){
            if (singleton.verifyAccount(user, password)){
                User signedUser = singleton.getUserByAccount(user, password);
                main.openProcessView(signedUser);
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("La cuenta no existe");
                alert.showAndWait();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Rellena los campos requeridos");
            alert.showAndWait();
        }

    }

    /**
     * Verifies the empty fields
     * @param user
     * @param password
     * @return
     */
    private boolean verifyFields(String user, String password) {
        if (user.equals("")){
            return false;
        }
        if (password.equals("")){
            return false;
        }
        return true;
    }

    public void setMain(Main main) {
        this.main = main;

    }
}
