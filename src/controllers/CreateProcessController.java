package controllers;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Admin;
import model.Process;
import model.User;

import java.io.IOException;

public class CreateProcessController {
    ModelFactoryController singleton = ModelFactoryController.getInstance();
    User selectedUser;
    Main main;

    Admin signedAdmin;
    @FXML
    private Button createButton;

    @FXML
    private Button gBack;

    @FXML
    private TextField idField;

    @FXML
    private TextField nameField;

    @FXML
    void createProcess(ActionEvent event) throws IOException {
        String name = "";
        String id = "";
        name = nameField.getText();
        id = idField.getText();
        System.out.println(selectedUser.getName());
        if (!(name.equals("")&&id.equals(""))){
            Process process = singleton.createProcess(selectedUser, id, name);
            if (process!=null)main.openAdministratorView(signedAdmin);
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atencion");
            alert.setContentText("Rellene los campos antes de continuar");
            alert.showAndWait();
        }
    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
        main.openAdministratorView(signedAdmin);
    }
    public void setMain(Main main) {
        this.main = main;

    }

    public void setSelectedUser(User selecteduser) {
        this.selectedUser = selecteduser;
    }

    public void setSignedAdmin(Admin signedAdmin) {
        this.signedAdmin = signedAdmin;
    }
}
