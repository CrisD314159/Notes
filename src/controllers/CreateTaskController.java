package controllers;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Activity;
import model.Process;
import model.User;

import java.io.IOException;
import java.util.ArrayList;

public class CreateTaskController {
    private Process selectedProcess;
    private Activity selectedActivity;
    private User signedUser;

    Main main;

    ModelFactoryController singleton = ModelFactoryController.getInstance();


    @FXML
    private Button backButton;

    @FXML
    private ChoiceBox<String> choiceActivity;

    @FXML
    private Button createTaskButton;

    @FXML
    private TextField descriptionField;

    @FXML
    private CheckBox mustCheckBox;

    @FXML
    private TextField tipeField;

    @FXML
    void createTask(ActionEvent event) throws IOException {
        String description = "";
        String time = "";
        description = descriptionField.getText();
        time = tipeField.getText();
        Activity activity = searchActivity(choiceActivity.getValue());
        System.out.println(activity);
        boolean mustDo = mustCheckBox.isSelected();
        if(!verifyFields(description, time, activity)){
            boolean task = singleton.createTask(activity, description, time, mustDo);
            if(task) main.openActivitiesView(selectedProcess, signedUser);

        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atención");
            alert.setContentText("Rellene todos los campos");
            alert.showAndWait();
        }

    }

    private boolean verifyFields(String description, String time, Activity activity) {
        if(description.equals("")) return true;
        if (time.equals("")) return true;
        if(activity==null) return true;
        return false;
    }

    private Activity searchActivity(String name) {
        return singleton.searchActivityByName(selectedProcess,name);
    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
        main.openActivitiesView(selectedProcess, signedUser);

    }

    public void setSelectedProcess(Process selectedProcess) {
        this.selectedProcess = selectedProcess;
    }

    public void setSignedUser(User signedUser) {
        this.signedUser = signedUser;
    }

    public void setSelectedActivity(Activity selectedActivity) {
        this.selectedActivity = selectedActivity;
    }

    public void setMain(Main main) {
        choiceActivity.getItems().clear();
        choiceActivity.getItems().addAll(getProcessActivities());
        this.main = main;
    }

    private ArrayList<String> getProcessActivities() {
        ArrayList<Activity> activities = singleton.getProcessActivities(selectedProcess);
        ArrayList<String> activitiesString = new ArrayList<String>();
        for (Activity aux: activities) {
            activitiesString.add(aux.getName());

        }
        return activitiesString;
    }
}
