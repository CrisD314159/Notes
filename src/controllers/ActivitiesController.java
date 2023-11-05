package controllers;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import model.Activity;
import model.Process;
import model.Task;
import model.User;

import java.io.IOException;
import java.util.ArrayList;

public class ActivitiesController {
    private Process selectedProcess;

    private Activity selectedActivity;
    private Task selectedTask;

    private User signedUser;
    Main main;

    ModelFactoryController singleton = ModelFactoryController.getInstance();

    ObservableList<Activity> listaActividades = FXCollections.observableArrayList();
    ObservableList<Task> listaTareas = FXCollections.observableArrayList();

    @FXML
    private TableView<Activity> activityTable;

    @FXML
    private Button createActivityButton;

    @FXML
    private Button createTaskButton;

    @FXML
    private Button deleteActivityButton;
    @FXML
    private Button goBackButton;

    @FXML
    private TableColumn<Activity, String> descriptionActivityColumn;

    @FXML
    private TableColumn<Task, String> descriptionTaskColumn;

    @FXML
    private Button editActivityButton;

    @FXML
    private Button editTaskButton;

    @FXML
    private Button markAsDoneButton;

    @FXML
    private TableColumn<Task, Boolean> mustTaskColumn;

    @FXML
    private TableColumn<Activity, String> nombreActividadColum;

    @FXML
    private TableView<Task> taskTable;

    @FXML
    void goBack(ActionEvent event) throws IOException {
        main.openProcessView(signedUser);

    }

    @FXML
    void createActivity(ActionEvent event) throws IOException {
        if (selectedProcess != null) {
            main.openCreateActivity(selectedProcess, signedUser);
        }

    }

    @FXML
    void createTaks(ActionEvent event) {

    }

    @FXML
    void deleteActivity(ActionEvent event) {
        if(listaTareas.size() == 0){
            boolean deleteActivity = singleton.deleteActivity(selectedProcess, selectedActivity);
            if (deleteActivity) listaActividades.remove(selectedActivity);
            activityTable.refresh();
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atención");
            alert.setContentText("para marcar como completada la actividad no debe tener ninguna tarea");
            alert.showAndWait();
        }

    }

    @FXML
    void editActivity(ActionEvent event) {

    }

    @FXML
    void editTask(ActionEvent event) {

    }

    @FXML
    void markAsDone(ActionEvent event) {

    }

    @FXML
    void initialize(){
        this.nombreActividadColum.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.descriptionActivityColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        this.descriptionTaskColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        this.mustTaskColumn.setCellValueFactory(new PropertyValueFactory<>("mustDo"));


        activityTable.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection, newSelection) -> {
            selectedActivity = newSelection;
            showTasks(selectedActivity);

        });
        taskTable.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection, newSelection) -> {
            selectedTask = newSelection;

        });


    }

    private void showTasks(Activity selectedActivity) {
        taskTable.getItems().clear();
        listaTareas.clear();
        listaTareas.addAll(singleton.getActivityTasks(selectedActivity));
        taskTable.setItems(listaTareas);
    }

    public void setMain(Main main) {
        this.main = main;
        activityTable.getItems().clear();
        activityTable.setItems(getActivitiesList());
        taskTable.getItems().clear();


    }

    private ObservableList<Activity> getActivitiesList() {
        listaActividades.addAll(singleton.getProcessActivities(selectedProcess));
        return listaActividades;
    }


    public void setSelectedProcess(Process selectedProcess) {
        this.selectedProcess = selectedProcess;

    }


    public void setSignedUser(User signedUser) {
        this.signedUser = signedUser;
    }
}
