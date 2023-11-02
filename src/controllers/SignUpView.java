package controllers;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SignUpView {
    Main main;

    private ModelFactoryController singleton = ModelFactoryController.getInstance();


    @FXML
    private Button backButton;

    @FXML
    private TextField idField;

    @FXML
    private TextField nameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField userField;

    public void setMain(Main main) {
        this.main = main;
    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
        main.inicializarLogin();

    }

    @FXML
    void signUpAction(ActionEvent event) {
        String name = "";
        String id = "";
        String user = "";
        String password = "";
        name = nameField.getText();
        id = idField.getText();
        user = userField.getText();
        password = passwordField.getText();
        if(!verifyFields(name , id, user, password)){
            if(!singleton.verifyUser(id, user)){
                if(singleton.createUser(name, id, user, password)){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Listo");
                    alert.setContentText("Usuario creado con éxito");
                    alert.showAndWait();
                    cleanFields();
                }else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("Hubo un error en la creación del usuario");
                    alert.showAndWait();
                }
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Atención");
                alert.setContentText("El usuario ya existe");
                alert.showAndWait();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atención");
            alert.setContentText("Rellena todos los campos");
            alert.showAndWait();
        }


    }

    private void cleanFields() {
        nameField.setText("");
        idField.setText("");
        userField.setText("");
        passwordField.setText("");
    }

    private boolean verifyFields(String name, String id, String user, String password) {
        if (name.equals("")){
            return true;
        }
         if (id.equals("")){
            return true;
        }
         if (user.equals("")){
            return true;
        }
         if (password.equals("")){
            return true;
        }
         return false;

    }
}