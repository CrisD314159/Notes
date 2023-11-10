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
import model.Task;
import model.User;

import java.io.IOException;

public class EditTaskController {

    Main main;
    ModelFactoryController singleton = ModelFactoryController.getInstance();

    User signedUser;
    Process selectedProcess;

    Task selectedTask;


    @FXML
    private Button backButton;

    @FXML
    private TextField descriptionField;

    @FXML
    private CheckBox mustCheckBox;

    @FXML
    private TextField tipeField;

    @FXML
    private Button updateTaskButton;

    @FXML
    void goBack(ActionEvent event) throws IOException {
        main.openActivitiesView(selectedProcess, signedUser);

    }

    @FXML
    void updateTask(ActionEvent event) throws IOException {
        String description = "";
        String time = "";
        description = descriptionField.getText();
        time = tipeField.getText();
        boolean mustDo = mustCheckBox.isSelected();
        if(!verifyFields(description, time)){
            boolean task = singleton.updateTask(selectedTask, description, time, mustDo);
            if(task) main.openActivitiesView(selectedProcess, signedUser);

        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atención");
            alert.setContentText("Rellene todos los campos");
            alert.showAndWait();
        }


    }

    private boolean verifyFields(String description, String time) {
        if(description.equals("")) return true;
        if (time.equals("")) return true;
        return false;
    }

    public void setSelectedProcess(Process selectedProcess) {
        this.selectedProcess=selectedProcess;
    }

    public void setSignedUser(User signedUser) {
        this.signedUser = signedUser;
    }

    public void setSelectedTask(Task selectedTask) {
        this.selectedTask = selectedTask;
    }

    public void setMain(Main main) {
        this.main = main;
        descriptionField.setText(selectedTask.getDescription());
        tipeField.setText(selectedTask.getDuration());
        if (selectedTask.isMustDo()) mustCheckBox.fire();
    }
}
