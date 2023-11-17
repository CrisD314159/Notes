package application;

import controllers.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;
import model.Process;

import java.io.IOException;

public class Main extends Application {
    Notes notes = new Notes();
    private Stage stage;


    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;

        inicializarLogin();

    }


    public void inicializarLogin() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../views/LoginView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());


        LoginController controller = fxmlLoader.getController();
        controller.setMain(this);
        //scene.getStylesheets().clear();
       // scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../Stylesheets/Style.css")).toExternalForm());
        //scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
    }

    public void openProcessView(User signedUser) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../views/processView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        ProcessViewController controller = fxmlLoader.getController();
        controller.setLoggedUser(signedUser);
        controller.setMain(this);
        //scene.getStylesheets().clear();
        // scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../Stylesheets/Style.css")).toExternalForm());
        //scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }


    public void openSignUpView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../views/signUpView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        SignUpView controller = fxmlLoader.getController();
        controller.setMain(this);
        //scene.getStylesheets().clear();
        // scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../Stylesheets/Style.css")).toExternalForm());
        //scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
    }

    public void openRecoverPassword() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../views/recoverPasswordView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        RecoverPasswordController controller = fxmlLoader.getController();
        controller.setMain(this);
        //scene.getStylesheets().clear();
        // scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../Stylesheets/Style.css")).toExternalForm());
        //scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();

    }

    public void openActivitiesView(Process selectedProcess, User signedUser) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../views/ActivitiesView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());


        ActivitiesController controller = fxmlLoader.getController();
        controller.setSelectedProcess(selectedProcess);
        controller.setSignedUser(signedUser);
        controller.setMain(this);
        //scene.getStylesheets().clear();
        // scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../Stylesheets/Style.css")).toExternalForm());
        //scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
    }

    public void openCreateActivity(Process selectedProcess, User signedUser) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../views/CreateActivityView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());


        CreateActivityController controller = fxmlLoader.getController();
        controller.setSelectedProcess(selectedProcess);
        controller.setSignedUser(signedUser);
        controller.setMain(this);
        //scene.getStylesheets().clear();
        // scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../Stylesheets/Style.css")).toExternalForm());
        //scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
    }


    public void openEditActivity(Activity selectedActivity, Process selectedProcess, User signedUser) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../views/EditActivityView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());


        EditActivityController controller = fxmlLoader.getController();
        controller.setSelectedProcess(selectedProcess);
        controller.setSignedUser(signedUser);
        controller.setSelectedActivity(selectedActivity);
        controller.setMain(this);
        //scene.getStylesheets().clear();
        // scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../Stylesheets/Style.css")).toExternalForm());
        //scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
    }

    public void openCreateTask(User signedUser, Process selectedProcess, Activity selectedActivity) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../views/CreateTaskView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());


        CreateTaskController controller = fxmlLoader.getController();
        controller.setSelectedProcess(selectedProcess);
        controller.setSignedUser(signedUser);
        controller.setSelectedActivity(selectedActivity);
        controller.setMain(this);
        //scene.getStylesheets().clear();
        // scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../Stylesheets/Style.css")).toExternalForm());
        //scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
    }

    public void openAdministratorLoginView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../views/AdministratorLoginView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        AdministratorLoginViewController controller = fxmlLoader.getController();
        controller.setMain(this);
        //scene.getStylesheets().clear();
        // scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../Stylesheets/Style.css")).toExternalForm());
        //scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
    }

    public void openAdministratorView(Admin signedAdmin) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../views/AdministratorView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        AdministratorViewController controller = fxmlLoader.getController();
        controller.setLoggedAdmin(signedAdmin);
        controller.setMain(this);
        //scene.getStylesheets().clear();
        // scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../Stylesheets/Style.css")).toExternalForm());
        //scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
    }

    public void openEditTask(Task selectedTask, Process selectedProcess, User signedUser) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../views/EditTaskView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());


        EditTaskController controller = fxmlLoader.getController();
        controller.setSelectedProcess(selectedProcess);
        controller.setSignedUser(signedUser);
        controller.setSelectedTask(selectedTask);
        controller.setMain(this);
        //scene.getStylesheets().clear();
        // scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../Stylesheets/Style.css")).toExternalForm());
        //scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
    }

    public void administratorUserView(User selectedUser, Admin signedAdmin) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("../views/AdministratorUserView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        AdministratorUserViewController controller = fxmlLoader.getController();
        controller.setMain(this);
        controller.setSelectedUser(selectedUser);
        controller.setSelectedAdmin(signedAdmin);
        //scene.getStylesheets().clear();
        // scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("../Stylesheets/Style.css")).toExternalForm());
        //scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
    }
}