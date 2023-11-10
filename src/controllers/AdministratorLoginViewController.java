package controllers;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Admin;

import java.io.IOException;

public class AdministratorLoginViewController {

    Main main;

    private ModelFactoryController singleton = ModelFactoryController.getInstance();

    @FXML
    private TextField administratorUserField;

    @FXML
    private Button backButton;

    @FXML
    private Button signInAdministratorButton;

    @FXML
    private PasswordField passwordAdminsitratorField;

    @FXML
    void getBackAction(ActionEvent event) throws IOException {
        main.inicializarLogin();
    }

    @FXML
    void signInAdministratorAction(ActionEvent event) throws IOException {
        String admin= "";
        String passwordAdmin = "";
        admin = administratorUserField.getText();
        passwordAdmin = passwordAdminsitratorField.getText();
        if (verifyFields(admin, passwordAdmin)){
            if (singleton.verifyAdminAccount(admin, passwordAdmin)){
                Admin signedAdmin = singleton.getAdminByAccount(admin, passwordAdmin);
                main.openAdministratorView(signedAdmin);
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

    private boolean verifyFields(String user, String password) {
        if (user.equals("")){
            return false;
        }
        if (password.equals("")){
            return false;
        }
        return true;
    }

    @FXML
    void forgotAdministratorAction(ActionEvent event) {

    }

    public void setMain(Main main) {
        this.main = main;
    }
}
