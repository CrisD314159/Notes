package controllers;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Activity;
import model.Admin;
import model.Process;
import model.Task;
import model.User;

public class AdministratorUserViewController {

    Main main;

    User selectedUser;

    Task selectedTask;

    Activity selectedActivity;

    Admin signedAdmin;

    Process selectedProcess;

    ModelFactoryController singleton = ModelFactoryController.getInstance();

    ObservableList<Task> listaTaskData = FXCollections.observableArrayList();

    ObservableList<Activity> listaActivityData = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Activity, String> columnNameActivity;

    @FXML
    private TableColumn<Activity, String> columnObligatoriaActivities;

    @FXML
    private TableView<Activity> tblActivityUser;

    @FXML
    private TableColumn<Task, String> columnObligatoriaTask;

    @FXML
    private TableColumn<Task, String> columnDuracionTask;

    @FXML
    private Button ExportarExcelAdmin;

    @FXML
    private TableView<Task> tblTaskUser;

    @FXML
    private TableColumn<Task, String> columnDescripcionTask;

    @FXML
    private TableColumn<Activity, String> columnDescriptionActivity;

    @FXML
    void ExportExcelAdmin(ActionEvent event) {

    }


    @FXML
    void initialize(){
        this.columnNameActivity.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.columnDescriptionActivity.setCellValueFactory(new PropertyValueFactory<>("description"));
        this.columnObligatoriaActivities.setCellValueFactory(new PropertyValueFactory<>("obligatoria"));

        this.columnDescripcionTask.setCellValueFactory(new PropertyValueFactory<>("description"));
        this.columnObligatoriaTask.setCellValueFactory(new PropertyValueFactory<>("obligatoria"));
        this.columnDuracionTask.setCellValueFactory(new PropertyValueFactory<>("duration"));


        tblActivityUser.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection, newSelection) -> {
            selectedActivity = newSelection;
            showTasks(selectedActivity);

        });
        tblTaskUser.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection, newSelection) -> {
            selectedTask = newSelection;

        });
    }

    private void showTasks(Activity selectedActivity) {
       tblTaskUser.getItems().clear();
        listaTaskData.clear();
        listaTaskData.addAll(singleton.getActivityTasks(selectedActivity));
        tblTaskUser.setItems(listaTaskData);
    }

    private ObservableList<Activity> getActivitiesList() {
        listaActivityData.addAll(singleton.getProcessActivities(selectedProcess));
        return listaActivityData;
    }



    public void setMain(Main main) {
        this.main = main;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
        tblActivityUser.getItems().clear();
        tblActivityUser.setItems(getActivitiesList());
        tblTaskUser.getItems().clear();
    }

    public void setSelectedAdmin(Admin signedAdmin) {
        this.signedAdmin = signedAdmin;
    }
}
