package controllers;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
<<<<<<< HEAD
import model.Admin;
import model.Permissions;
=======
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Callback;
import lists.ListaSimple;
import model.Admin;
>>>>>>> 2f1e6718c0e09ca2127ecea4c9e023c155ef4c6d
import model.Process;
import model.User;

import java.io.IOException;

public class AdministratorViewController {

    Main main;

    Admin signedAdmin;

<<<<<<< HEAD
    ModelFactoryController singleton = ModelFactoryController.getInstance();

    ObservableList<User> usersList = FXCollections.observableArrayList();
    ObservableList<Process> processList = FXCollections.observableArrayList();

    private User selecteduser;
    private Process selectedProcess;
    @FXML
    private TableColumn<Process, Integer> activitiesNumberColumn;

    @FXML
    private Button createProcessButton;

    @FXML
    private Button createUserButton;

    @FXML
    private Button deleteProcessButton;
    @FXML
    private Button gobackButton;

    @FXML
    private Button deleteUserButton;

    @FXML
    private Button editProcessButton;

    @FXML
    private Button editUserButton;

=======
    User selectedUser;

    Process selectedProcess;

    ObservableList<User> listaUsersData = FXCollections.observableArrayList();

    ObservableList<Process> listaProcessData = FXCollections.observableArrayList();

    private ModelFactoryController singleton = ModelFactoryController.getInstance();
>>>>>>> 2f1e6718c0e09ca2127ecea4c9e023c155ef4c6d
    @FXML
    private Label lblAdministrator;

    @FXML
<<<<<<< HEAD
    private TableColumn<User, String> nameColumn;

    @FXML
    private TableColumn<User, Permissions> permissionColumn;

    @FXML
    private TableColumn<Process, String> processIdColumn;

    @FXML
    private TableColumn<Process, String> processNameColumn;

    @FXML
    private TableColumn<User, Integer> processNumberColumn;

    @FXML
    private TableView<Process> processTable;

    @FXML
    private TableView<User> userTable;

    @FXML
    void initialize(){
        this.nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.permissionColumn.setCellValueFactory(new PropertyValueFactory<>("permission"));
        this.processNumberColumn.setCellValueFactory(new PropertyValueFactory<>("size"));

        this.processNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.processIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.activitiesNumberColumn.setCellValueFactory(new PropertyValueFactory<>("size"));


        userTable.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection, newSelection) -> {
            selecteduser = newSelection;
            showProcesses(selecteduser);

        });
        processTable.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection, newSelection) -> {
=======
    private TableColumn<User, String> columnNameUser;

    @FXML
    private TableColumn<Process, ListaSimple> columnActividadesUser;

    @FXML
    private TableColumn<User, String> columnIDUser;

    @FXML
    private TableColumn<Process, String> columnProcesosUser;

    @FXML
    private Label lblAdministrator1;

    @FXML
    private TextField txtIDUserAdmin;

    @FXML
    private Button backButton;

    @FXML
    private TableView<User> tblUsersAdmin;
    @FXML
    private TableView<Process> tblProcessUser;

    @FXML
    private TextField txtUserAdmin;




    @FXML
    void initialize(){
        this.columnNameUser.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.columnIDUser.setCellValueFactory(new PropertyValueFactory<>("id"));

        this.columnProcesosUser.setCellValueFactory(new PropertyValueFactory<>("name"));

        this.columnActividadesUser.setCellValueFactory(new PropertyValueFactory<>("activitiesList"));
        this.columnActividadesUser.setCellFactory(new Callback<TableColumn<Process, ListaSimple>, TableCell<Process, ListaSimple>>() {
            @Override
            public TableCell<Process, ListaSimple> call(TableColumn<Process, ListaSimple> processListaSimpleTableColumn) {
                return new TableCell<>() {
                    @Override
                    protected void updateItem(ListaSimple listaSimple, boolean b) {
                        super.updateItem(listaSimple, b);
                        if(!b && listaSimple != null){
                            Text texto= new Text(String.valueOf(listaSimple.getSize()));
                            texto.setStyle("-fx-background-color: Red");
                            texto.setTextAlignment(TextAlignment.CENTER);
                            setGraphic(texto);
                        }
                        else{
                            setGraphic(null);
                        }
                    }
                };
            }
        });



        tblProcessUser.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection, newSelection) -> {
>>>>>>> 2f1e6718c0e09ca2127ecea4c9e023c155ef4c6d
            selectedProcess = newSelection;

        });

<<<<<<< HEAD

    }

    private void showProcesses(User selecteduser) {
        if (selecteduser!= null){
            processTable.getItems().clear();
            processList.clear();
            processList.addAll(singleton.getUserProcessList(selecteduser));
            processTable.setItems(processList);
        }

    }

    @FXML
    void createProcess(ActionEvent event) throws IOException {
       if (selecteduser !=null )main.openCreateProcess(selecteduser, signedAdmin);
    }

    @FXML
    void createUser(ActionEvent event) throws IOException {
        main.openCreateUser(signedAdmin);

    }

    @FXML
    void deleteProcess(ActionEvent event) {
        if (selecteduser != null && selectedProcess != null){
            singleton.deleteUserProcess(selecteduser, selectedProcess);
            processTable.getItems().clear();
            processTable.getItems().addAll(singleton.getUserProcessList(selecteduser));
            processTable.refresh();
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atención");
            alert.setContentText("Para eliminar un proceso seleccione al menos uno");
            alert.showAndWait();
        }

    }

    @FXML
    void deleteUser(ActionEvent event) {
        if (selecteduser!=null){
            singleton.deleteUser(selecteduser);
            userTable.getItems().clear();
            userTable.getItems().addAll(singleton.getUsersList());
            userTable.refresh();
        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Atención");
            alert.setContentText("Para eliminar un usuario seleccione al menos uno");
            alert.showAndWait();
        }

    }

    @FXML
    void editProcess(ActionEvent event) throws IOException {
        if (selectedProcess!=null) main.openEditProcess(selectedProcess, signedAdmin);


    }

    @FXML
    void editUser(ActionEvent event) throws IOException {
        if (selecteduser!=null) main.openEditUser(selecteduser, signedAdmin);

    }
    public void setLoggedAdmin(Admin signedAdmin) {
        this.signedAdmin = signedAdmin;
        lblAdministrator.setText(signedAdmin.getName());
        userTable.getItems().clear();
        userTable.getItems().addAll(getUsersList());
        processTable.getItems().clear();
    }

    private ObservableList<User> getUsersList() {
        usersList.addAll(singleton.getUsersList());
        return usersList;
    }
    @FXML
    void goBack(ActionEvent event) throws IOException {
        main.inicializarLogin();

    }

=======
        tblUsersAdmin.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection, newSelection) -> {
            selectedUser = newSelection;
            showProcess(selectedUser);
        });
    }

    private ObservableList<User> getUsersList() {
        listaUsersData.addAll(singleton.getUsersList());
        return listaUsersData;
    }

    public void setLoggedAdmin(Admin signedAdmin) {
        this.signedAdmin = signedAdmin;

    }

    private void showProcess(User signedUser) {
        tblProcessUser.getItems().clear();
        listaProcessData.clear();
        listaProcessData.addAll(singleton.getUserProcessList(signedUser));
        tblProcessUser.setItems(listaProcessData);
    }

    @FXML
    void BackAction1(ActionEvent event) throws IOException {
        main.openAdministratorLoginView();
    }

    @FXML
    void seeUserAction(ActionEvent event) throws IOException {
        if(selectedUser != null){
            main.administratorUserView(selectedUser, signedAdmin);
        }
    }

    @FXML
    void searchUserAction(ActionEvent event){

    }


>>>>>>> 2f1e6718c0e09ca2127ecea4c9e023c155ef4c6d
    public void setMain(Main main) {
        this.main = main;
        tblUsersAdmin.getItems().clear();
        tblUsersAdmin.setItems(getUsersList());
        lblAdministrator.setText(signedAdmin.getName());

    }
}
