package controllers;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Process;
import model.User;

import java.io.IOException;

public class ProcessViewController {
    Main main;
    ObservableList<Process> listaProcesosData = FXCollections.observableArrayList();
    private ModelFactoryController singleton = ModelFactoryController.getInstance();

    Process selectedProcess;

    User signedUser;


    @FXML
    private TableColumn<Process, String> activityNumberColumn;

    @FXML
    private Button backButton;

    @FXML
    private Button createButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Label nameLabel;

    @FXML
    private Button openProcess;

    @FXML
    private TableColumn<Process, Integer> processNameColumn;

    @FXML
    private TableView<Process> processTable;

    @FXML
    void initialize(){
        this.processNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.activityNumberColumn.setCellValueFactory(new PropertyValueFactory<>("size"));


        processTable.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection, newSelection) -> {
            selectedProcess = newSelection;
        });


    }

    public void setMain(Main main) {
        this.main = main;
        processTable.getItems().clear();
        processTable.setItems(getProcessList());
    }
    public void setLoggedUser(User signedUser) {
        this.signedUser = signedUser;
    }

    private ObservableList<Process> getProcessList() {
        listaProcesosData.addAll(singleton.getUserProcessList(signedUser));
        return listaProcesosData;
    }

    @FXML
    void createProcess(ActionEvent event) {

    }

    @FXML
    void deleteProcess(ActionEvent event) {

    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
        main.inicializarLogin();

    }

    @FXML
    void openProcess(ActionEvent event) {

    }


}
