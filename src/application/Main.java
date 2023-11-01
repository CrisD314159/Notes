package application;

import controllers.LoginController;
import controllers.ProcessViewController;
import controllers.RecoverPasswordController;
import controllers.SignUpView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Notes;
import model.User;

import java.io.IOException;
import java.util.Objects;

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
}