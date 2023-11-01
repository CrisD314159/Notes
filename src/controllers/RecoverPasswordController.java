package controllers;

import application.Main;

public class RecoverPasswordController {
    Main main;
    private ModelFactoryController singleton = ModelFactoryController.getInstance();
    public void setMain(Main main) {
        this.main = main;
    }
}
