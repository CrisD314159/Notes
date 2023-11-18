package controllers;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Admin;
import model.Process;

import java.io.IOException;

public class EditProcessController {

    Main main;
    Admin signedAdmin;
    Process selectedProcess;

    ModelFactoryController singleton = ModelFactoryController.getInstance();

    @FXML
    private Button gBack;

    @FXML
    private TextField idField;

    @FXML
    private TextField nameField;

    @FXML
    private Button updateButton;


    @FXML
    void goBack(ActionEvent event) throws IOException {
        main.openAdministratorView(signedAdmin);

    }
    @FXML
    void updateProcess(ActionEvent event) throws IOException {
        String name = "";
        String id = "";
        name = nameField.getText();
        id = idField.getText();
        if (!(name.equals("")&&id.equals(""))){
            boolean process = singleton.updateProcess(selectedProcess, name, id);
            if (process)main.openAdministratorView(signedAdmin);
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atencion");
            alert.setContentText("Rellene los campos antes de continuar");
            alert.showAndWait();
        }


    }

    public void setMain(Main main) {
        this.main = main;
        nameField.setText(selectedProcess.getName());
        idField.setText(selectedProcess.getId());
    }

    public void setSignedAdmin(Admin signedAdmin) {
        this.signedAdmin = signedAdmin;
    }

    public void setSelectedProcess(Process selectedProcess) {
        this.selectedProcess = selectedProcess;
    }
}
