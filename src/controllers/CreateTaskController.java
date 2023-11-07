package controllers;

import application.Main;
import model.Activity;
import model.Process;
import model.User;

public class CreateTaskController {
    private Process selectedProcess;
    private Activity selectedActivity;
    private User signedUser;

    Main main;

    ModelFactoryController singleton = ModelFactoryController.getInstance();



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
        this.main = main;
    }
}
