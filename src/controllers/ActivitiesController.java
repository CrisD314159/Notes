package controllers;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Activity;
import model.Process;
import model.Task;
import model.User;

import java.io.IOException;

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
    private Button createBeginingActivityButton;

    @FXML
    private Button createEndActivityButton;


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
    private TableColumn<Task, String> durationColumn;

    @FXML
    private Button editActivityButton;

    @FXML
    private Button editTaskButton;

    @FXML
    private Button markAsDoneButton;

    @FXML
    private TableColumn<Task, Boolean> mustTaskColumn;
    @FXML
    private TableColumn<Activity, Boolean> mustActivityColum;


    @FXML
    private TableColumn<Activity, String> nombreActividadColum;

    @FXML
    private TableView<Task> taskTable;

    /**
     * Returns to the previous view
     * @param event
     * @throws IOException
     */
    @FXML
    void goBack(ActionEvent event) throws IOException {
        main.openProcessView(signedUser);

    }


    /**
     * This method opens the create activity view to create a activity at the beginning
     * @param event
     * @throws IOException
     */
    @FXML
    void createActivityBegining(ActionEvent event) throws IOException {
        if (selectedProcess != null) {
            main.openCreateActivity(selectedProcess, signedUser, 1);
        }

    }

    /**
     * This method opens the create activity view to create a activity at the end
     * @param event
     */
    @FXML
    void createActivityEnd(ActionEvent event) throws IOException {
        if (selectedProcess != null) {
            main.openCreateActivity(selectedProcess, signedUser, 0);
        }

    }


    /**
     * This method opens the create task view
     * @param event
     * @throws IOException
     */
    @FXML
    void createTaks(ActionEvent event) throws IOException {
        main.openCreateTask(signedUser, selectedProcess, selectedActivity);

    }

    /**
     * Calls the singleton class for delete a user
     * @param event
     */
    @FXML
    void deleteActivity(ActionEvent event) {
        if(selectedActivity.getTasksList().size() == 0){
            boolean deleteActivity = singleton.deleteActivity(selectedProcess, selectedActivity);
            if (deleteActivity) listaActividades.remove(selectedActivity);
            activityTable.refresh();
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atención");
            alert.setContentText("Para marcar como completada la actividad no debe tener ninguna tarea");
            alert.showAndWait();
        }

    }

    /**
     * Opens edit activity view
     * @param event
     * @throws IOException
     */
    @FXML
    void editActivity(ActionEvent event) throws IOException {
        if (selectedActivity!= null){
            main.openEditActivity(selectedActivity, selectedProcess, signedUser);
        }

    }

    @FXML
    void editTask(ActionEvent event) throws IOException {
        if (selectedTask !=null){
            main.openEditTask(selectedTask, selectedProcess, signedUser);

        }


    }

    @FXML
    void markAsDone(ActionEvent event) {
        if (singleton.isNextTask(selectedActivity, selectedTask)){
            boolean task = singleton.markTaskAsDone(selectedActivity, selectedTask);
            if (task) listaTareas.remove(selectedTask);
            taskTable.refresh();
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atención");
            alert.setContentText("Para marcar como completada una tarea debe estar completada la que la precede");
            alert.showAndWait();
        }

    }

    @FXML
    void initialize(){
        this.nombreActividadColum.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.descriptionActivityColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        this.mustActivityColum.setCellValueFactory(new PropertyValueFactory<>("mustDo"));

        this.descriptionTaskColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        this.mustTaskColumn.setCellValueFactory(new PropertyValueFactory<>("mustDo"));
        this.durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));


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
