package controllers;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Admin;
import model.Permissions;
import model.User;

import java.io.IOException;

public class EditUserController {
    Main main;
    Admin signedAdmin;
    User selecteedUser;

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
    void goBack(ActionEvent event) throws IOException {
        main.openAdministratorView(signedAdmin);

    }

    @FXML
    void updateUser(ActionEvent event) throws IOException {
        String name ="";
        String id ="";
        String password ="";
        String user ="";
        name = nameField.getText();
        id = idField.getText();
        password = passwordField.getText();
        user = userField.getText();
        if ((!verifyFields(name, id, password, user))&& permissionChoice.getValue() != null){
            boolean created = singleton.updateUser(selecteedUser, name, id, user, password, permissionChoice.getValue());
            if (created) main.openAdministratorView(signedAdmin);

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

    public void setSelectedUser(User selecteduser) {
        this.selecteedUser = selecteduser;
    }

    public void setSignedAdmin(Admin signedAdmin) {
        this.signedAdmin = signedAdmin;
    }

    public void setMain(Main main) {
        this.main = main;
        nameField.setText(selecteedUser.getName());
        idField.setText(selecteedUser.getId());
        userField.setText(selecteedUser.getAccount().getUser());
        permissionChoice.getItems().clear();
        permissionChoice.getItems().add(Permissions.VIEW);
        permissionChoice.getItems().add(Permissions.EDIT);
        permissionChoice.setValue(selecteedUser.getPermission());
    }
}
