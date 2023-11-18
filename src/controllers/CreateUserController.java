package controllers;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Admin;
import model.Permissions;

import java.io.IOException;

public class CreateUserController {
    Admin admin;
    Main main;
    ModelFactoryController singleton = ModelFactoryController.getInstance();

    @FXML
    private Button backButton;

    @FXML
    private Button createButton;

    @FXML
    private TextField idField;

    @FXML
    private TextField nameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private ChoiceBox<Permissions> permissionChoice;

    @FXML
    private TextField userField;

    @FXML
    void createUser(ActionEvent event) throws IOException {
        String name ="";
        String id ="";
        String password ="";
        String user ="";
        name = nameField.getText();
        id = idField.getText();
        password = passwordField.getText();
        user = userField.getText();
        if ((!verifyFields(name, id, password, user))&& permissionChoice.getValue() != null){
            boolean created = singleton.createUser(name, id, user, password, permissionChoice.getValue());
            if (created) main.openAdministratorView(admin);

        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atencion");
            alert.setContentText("Rellene los campos antes de continuar");
            alert.showAndWait();
        }

    }

    private boolean verifyFields(String name, String id, String password, String user) {
        if (name.equals(""))return true;
        if (user.equals(""))return true;
        if (id.equals(""))return true;
        if (password.equals(""))return true;
        return false;
    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
        main.openAdministratorView(admin);

    }

    public void setMain(Main main) {
        this.main = main;
        permissionChoice.getItems().clear();
        permissionChoice.getItems().add(Permissions.EDIT);
        permissionChoice.getItems().add(Permissions.VIEW);
    }

    public void setSignedAdmin(Admin signedAdmin) {
        this.admin = signedAdmin;

    }
}
