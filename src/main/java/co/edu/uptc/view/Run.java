package co.edu.uptc.view;

import co.edu.uptc.controllerFx.LoginController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Run extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Crear una instancia de LoginController
        LoginController loginController = new LoginController(primaryStage);

        // Crear una instancia de LoginView y pasarle el primaryStage y el LoginController
        LoginView loginView = new LoginView(primaryStage, loginController);
        loginView.start(primaryStage);
        loginView.start(primaryStage); 
    }

    public static void main(String[] args) {
        launch(args);
    }
}
