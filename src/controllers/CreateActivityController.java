package controllers;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import model.Process;
import model.User;

import java.io.IOException;

public class CreateActivityController {
    Main main;
    private Process process;
    private User signedUser;

    ModelFactoryController singleton = ModelFactoryController.getInstance();
    @FXML
    private Button createButton;

    @FXML
    private TextField descriptionField;

    @FXML
    private Button goBackButton;

    @FXML
    private CheckBox mustCheckBox;

    @FXML
    private TextField nameField;

    @FXML
    void createActivity(ActionEvent event) throws IOException {
        String name = "";
        String description = "";
        boolean mustDo = mustCheckBox.isSelected();
        name = nameField.getText();
        description = descriptionField.getText();
        if(!verifyFields(name, description)){
            boolean activity = singleton.createActivity(process, name, description, mustDo);
            if (activity) main.openActivitiesView(process, signedUser);

        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atención");
            alert.setContentText("Rellena todos los campos");
            alert.showAndWait();
        }

    }

    private boolean verifyFields(String name, String description) {
        if(name.equals("")) return true;
        if(description.equals(""))return true;
        return false;
    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
        main.openActivitiesView(process, signedUser);

    }

    public void setSelectedProcess(Process selectedProcess) {
        this.process = selectedProcess;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void setSignedUser(User signedUser) {
        this.signedUser = signedUser;
    }
}
