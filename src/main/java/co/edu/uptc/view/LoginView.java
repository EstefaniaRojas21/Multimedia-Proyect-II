package co.edu.uptc.view;

import co.edu.uptc.controllerFx.LoginController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoginView extends Application {
    

    private Stage primaryStage;
    private LoginController loginController;

    public LoginView(Stage primaryStage, LoginController loginController) {
        this.primaryStage = primaryStage;
        this.loginController = loginController;
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(5);

        TextField userField = new TextField();
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("LogIn");

        gridPane.add(new Label("User: "), 0, 0);
        gridPane.add(userField, 1, 0);
        gridPane.add(new Label("Password: "), 0, 1);
        gridPane.add(passwordField, 1, 1);
        gridPane.add(loginButton, 0, 2);

        loginButton.setOnAction(event -> {
            if (loginController.login(userField, passwordField)) {
                // Cambiar a la escena del menú principal
                loginController.showMainMenu(); // Llamar al método showMainMenu() del LoginController
            }
        });

        Scene scene = new Scene(gridPane, 300, 150);
        primaryStage.setScene(scene);
        primaryStage.setTitle("LogIn Menu");
        primaryStage.show();
    }
}