package controllers;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
    private TextField idField;

    @FXML
    private TextField nameField;

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
        nameLabel.setText(signedUser.getName());
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
        String id = "";
        String name = "";
        id = idField.getText();
        name = nameField.getText();
        if(!verifyFields(id, name)){
            if (!singleton.verifyProcess(signedUser, id)){
                Process process = singleton.createProcess(signedUser, id, name);
                if (process != null){
                    processTable.getItems().clear();
                    listaProcesosData.addAll(singleton.getUserProcessList(signedUser));
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Listo");
                    alert.setContentText("Proceso creado");
                    alert.showAndWait();
                }else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("Ocurrio un error a la hora de crear el proceso");
                    alert.showAndWait();
                }

            }else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Atención");
                alert.setContentText("El proceso ya existe");
                alert.showAndWait();
            }
            processTable.refresh();
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atención");
            alert.setContentText("Rellena todos los campos");
            alert.showAndWait();
        }



    }

    private boolean verifyFields(String id, String name) {
        if (name.equals(""))return true;
        if (id.equals(""))return true;
        return false;

    }

    @FXML
    void deleteProcess(ActionEvent event) {
        if(selectedProcess.getSize() == 0){
            if (singleton.deleteUserProcess(signedUser, selectedProcess)){
                listaProcesosData.remove(selectedProcess);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Listo");
                alert.setContentText("Proceso eliminado");
                alert.showAndWait();
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Ocurrio un error a la hora de eliminar el proceso");
                alert.showAndWait();
            }
            processTable.refresh();
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atención");
            alert.setContentText("para eliminar un proceso, este no debe tener actividades");
            alert.showAndWait();
        }


    }

    @FXML
    void goBack(ActionEvent event) throws IOException {
        main.inicializarLogin();

    }

    @FXML
    void openProcess(ActionEvent event) throws IOException {
        if(selectedProcess != null){
            main.openActivitiesView(selectedProcess, signedUser);
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atención");
            alert.setContentText("Seleccione algún proceso antes de continuar");
            alert.showAndWait();
        }

    }


}
