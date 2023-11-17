package controllers;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Callback;
import lists.ListaSimple;
import model.Admin;
import model.Process;
import model.User;

import java.io.IOException;

public class AdministratorViewController {

    Main main;

    Admin signedAdmin;

    User selectedUser;

    Process selectedProcess;

    ObservableList<User> listaUsersData = FXCollections.observableArrayList();

    ObservableList<Process> listaProcessData = FXCollections.observableArrayList();

    private ModelFactoryController singleton = ModelFactoryController.getInstance();
    @FXML
    private Label lblAdministrator;

    @FXML
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
            selectedProcess = newSelection;

        });

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


    public void setMain(Main main) {
        this.main = main;
        tblUsersAdmin.getItems().clear();
        tblUsersAdmin.setItems(getUsersList());
        lblAdministrator.setText(signedAdmin.getName());

    }
}
