package controllers;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import model.Activity;
import model.Process;
import model.User;

import java.io.IOException;

public class EditActivityController {
    private Activity selectedActivity;
    private Process selectedProcess;
    private User signeduser;
    private Main main;

    ModelFactoryController singleton = ModelFactoryController.getInstance();

    @FXML
    private Button backButton;

    @FXML
    private TextField descriptionField;

    @FXML
    private CheckBox mustCheck;

    @FXML
    private TextField nameField;

    @FXML
    private Button updateButton;

    @FXML
    void goBack(ActionEvent event) throws IOException {
        main.openActivitiesView(selectedProcess, signeduser);
    }

    /**
     * Calls the singleton to update the activity selected
     * @param event
     * @throws IOException
     */
    @FXML
    void updateActivity(ActionEvent event) throws IOException {
        String name ="";
        String description ="";

        boolean mustDo = mustCheck.isSelected();
        name = nameField.getText();
        description = descriptionField.getText();
        if (!verifyFields(name, description)){
            boolean activity = singleton.updateActivity(selectedActivity, name, description, mustDo);
            if (activity) main.openActivitiesView(selectedProcess, signeduser);
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atención");
            alert.setContentText("Rellene todos los campos");
            alert.showAndWait();
        }

    }

    private boolean verifyFields(String name, String description) {
        if (name.equals(""))return true;
        if (description.equals(""))return true;
        return false;
    }

    public void setSelectedProcess(Process selectedProcess) {
        this.selectedProcess = selectedProcess;
    }

    public void setSignedUser(User signedUser) {
        this.signeduser = signedUser;
    }

    public void setSelectedActivity(Activity selectedActivity) {
        this.selectedActivity = selectedActivity;
    }

    public void setMain(Main main) {
        this.main= main;
        nameField.setText(selectedActivity.getName());
        descriptionField.setText(selectedActivity.getDescription());
        if (selectedActivity.isMustDo()) mustCheck.fire();
    }
}
