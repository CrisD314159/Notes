package controllers;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.Admin;
import model.User;

public class AdministratorViewController {

    Main main;

    Admin signedAdmin;
    @FXML
    private Label lblAdministrator;
    public void setLoggedAdmin(Admin signedAdmin) {
        this.signedAdmin = signedAdmin;
        lblAdministrator.setText(signedAdmin.getName());
    }
    public void setMain(Main main) {
        this.main = main;
    }
}
